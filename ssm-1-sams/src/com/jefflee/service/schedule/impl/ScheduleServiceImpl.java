package com.jefflee.service.schedule.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.jefflee.mapper.schedule.ScheduleMapper;
import com.jefflee.po.information.PeriodPo;
import com.jefflee.po.schedule.ArrangementPo;
import com.jefflee.po.schedule.PlanPo;
import com.jefflee.po.schedule.SchedulePo;
import com.jefflee.service.information.CourseService;
import com.jefflee.service.information.PeriodService;
import com.jefflee.service.information.TclassService;
import com.jefflee.service.information.TeacherService;
import com.jefflee.service.schedule.ArrangementService;
import com.jefflee.service.schedule.PlanService;
import com.jefflee.service.schedule.ScheduleService;
import com.jefflee.view.ScheduleView;
import com.jefflee.view.WeekView;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Resource(name = "scheduleMapper")
	private ScheduleMapper scheduleMapper;

	@Resource(name = "planService")
	private PlanService planService;
	@Resource(name = "arrangementService")
	private ArrangementService arrangementService;

	@Resource(name = "periodService")
	private PeriodService periodService;
	@Resource(name = "courseService")
	private CourseService courseService;
	@Resource(name = "tclassService")
	private TclassService tclassService;
	@Resource(name = "teacherService")
	private TeacherService teacherService;

	@Override
	public Integer insert(SchedulePo schedulePo) {
		if (scheduleMapper.insert(schedulePo) == 1) {
			return schedulePo.getScheduleId();
		} else {
			return null;
		}
	}

	@Override
	public List<SchedulePo> selectAll() {
		return scheduleMapper.selectAll();
	}

	@Override
	public SchedulePo selectById(Integer scheduleId) {
		return scheduleMapper.selectByPrimaryKey(scheduleId);
	}

	@Override
	public Integer updateById(SchedulePo schedulePo) {
		if (scheduleMapper.updateByPrimaryKey(schedulePo) == 1) {
			return schedulePo.getScheduleId();
		} else {
			return null;
		}
	}

	@Override
	public Integer deleteById(Integer scheduleId) {
		if (scheduleMapper.deleteByPrimaryKey(scheduleId) == 1) {
			return scheduleId;
		} else {
			return null;
		}
	}

	@Override
	public ScheduleView gnrScheduleView(Integer scheduleId) {
		return arrangementService.gnrScheduleView(scheduleMapper.selectEntityById(scheduleId));
	}

	@Override
	public void gnrEmptyArrangementList(Integer scheduleId) {
		// TODO 根据课表上午、下午、晚上课时数生成课时列表
		List<PeriodPo> periodPoList = periodService.selectAll();
		List<PlanPo> planPoList = planService.selectAll();
		for (PlanPo planPo : planPoList) {
			for (PeriodPo periodPo : periodPoList) {
				ArrangementPo arrangementPo = new ArrangementPo();
				arrangementPo.setScheduleId(scheduleId);
				arrangementPo.setPeriodId(periodPo.getPeriodId());
				arrangementPo.setCourseId(planPo.getCourseId());
				arrangementPo.setRoomId(planPo.getRoomId());
				arrangementPo.setTclassId(planPo.getTclassId());
				arrangementPo.setTeacherId(planPo.getTeacherId());
				arrangementPo.setArranged(0);
				arrangementPo.setPriority(2);
				arrangementService.insert(arrangementPo);
			}
		}
	}

	@Override
	public void gnrSchedule(Integer scheduleId) {
		arrangementService.gnrArrangementList(scheduleId);
	}

	@Override
	public void gnrScheduleViewExcel(Integer scheduleId) throws FileNotFoundException, IOException {
		ScheduleView scheduleView = gnrScheduleView(scheduleId);
		List<WeekView> tclassWeekViewList = (List<WeekView>) scheduleView.getTclassWeekViewMap().values();
		List<WeekView> teacherWeekViewList = (List<WeekView>) scheduleView.getTeacherWeekViewMap().values();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet tclassSheet = workbook.createSheet("班级课表");
		HSSFSheet teacherSheet = workbook.createSheet("教师课表");
		HSSFRow tclassRow = null;
		HSSFRow teacherRow = null;
		HSSFCell tclassCell = null;
		HSSFCell teacherCell = null;

		int tclassNum = tclassWeekViewList.size();
		int teacherNum = teacherWeekViewList.size();
		int tclassRowNum = tclassNum % 3 == 0 ? tclassNum / 3 : tclassNum / 3 + 1;
		int teacherRowNum = teacherNum % 3 == 0 ? teacherNum / 3 : teacherNum / 3 + 1;
		WeekView tclassWeekView = new WeekView();
		WeekView teacherWeekView = new WeekView();

		int blockRowIdx = -1;
		int blockColIdx = -1;
		int rowIdx = -1;
		int colIdx = -1;
		int weekViewIdx = -1;
		for (int i = 0; i < tclassRowNum * 9 - 1; i++) {
			blockRowIdx = i / 9;
			rowIdx = i % 9;
			tclassRow = tclassSheet.createRow(i);
			for (int j = 0; j < 29; j++) {
				blockColIdx = j / 10;
				colIdx = j % 10;
				weekViewIdx = blockRowIdx * 3 + blockColIdx;
				if (weekViewIdx >= tclassNum) {
					break;
				}
				tclassWeekView = tclassWeekViewList.get(weekViewIdx);
				tclassCell = tclassRow.createCell(j);
				if (rowIdx == 0) {
					if (colIdx == 0) {
						tclassCell.setCellValue(tclassWeekView.getTitleView().getTclassName());
						continue;
					}
					if (colIdx == 3) {
						tclassCell.setCellValue(tclassWeekView.getTitleView().getTeacherName());
						continue;
					}
					if (colIdx == 6) {
						tclassCell.setCellValue(tclassWeekView.getTitleView().getStartDate());
						continue;
					}
				}
				if (rowIdx == 1) {
					if (colIdx == 1) {
						tclassCell.setCellValue("上午");
						continue;
					}
					if (colIdx == 6) {
						tclassCell.setCellValue("下午");
						continue;
					}
				}
				if (rowIdx == 2) {
					if (colIdx == 1) {
						tclassCell.setCellValue("早");
						continue;
					} else if (colIdx >= 2 && colIdx <= 8) {
						tclassCell.setCellValue(String.valueOf(colIdx - 1));
						continue;
					}
				}
				if (rowIdx >= 3 && rowIdx <= 7) {
					if (colIdx == 0) {
						tclassCell.setCellValue(String.valueOf(rowIdx - 2));
						continue;
					} else if (colIdx >= 1 && colIdx <= 8) {
						tclassCell.setCellValue(
								tclassWeekView.getDayViewList().get(rowIdx - 3).getArrangedPeriodViewList()
										.get(colIdx - 1).getArrangementList().get(0).getCourse().getName());
						continue;
					}
				}
			}
		}
		for (int i = 0; i < teacherRowNum * 9 - 1; i++) {
			blockRowIdx = i / 9;
			rowIdx = i % 9;
			teacherRow = teacherSheet.createRow(i);
			for (int j = 0; j < 29; j++) {
				blockColIdx = j / 10;
				colIdx = j % 10;
				weekViewIdx = blockRowIdx * 3 + blockColIdx;
				if (weekViewIdx >= teacherNum) {
					break;
				}
				teacherWeekView = teacherWeekViewList.get(weekViewIdx);
				teacherCell = teacherRow.createCell(j);
				if (rowIdx == 0) {
					if (colIdx == 0) {
						teacherCell.setCellValue(teacherWeekView.getTitleView().getCourseName());
						continue;
					}
					if (colIdx == 1) {
						teacherCell.setCellValue(teacherWeekView.getTitleView().getGradeName());
					}
					if (colIdx == 3) {
						teacherCell.setCellValue(teacherWeekView.getTitleView().getTeacherName());
						continue;
					}
					if (colIdx == 6) {
						teacherCell.setCellValue(teacherWeekView.getTitleView().getStartDate());
						continue;
					}
				}
				if (rowIdx == 1) {
					if (colIdx == 1) {
						teacherCell.setCellValue("上午");
						continue;
					}
					if (colIdx == 6) {
						teacherCell.setCellValue("下午");
						continue;
					}
				}
				if (rowIdx == 2) {
					if (colIdx == 1) {
						teacherCell.setCellValue("早");
						continue;
					} else if (colIdx >= 2 && colIdx <= 8) {
						teacherCell.setCellValue(String.valueOf(colIdx - 1));
						continue;
					}
				}
				if (rowIdx >= 3 && rowIdx <= 7) {
					if (colIdx == 0) {
						teacherCell.setCellValue(String.valueOf(rowIdx - 2));
						continue;
					} else if (colIdx >= 1 && colIdx <= 8) {
						teacherCell.setCellValue(
								teacherWeekView.getDayViewList().get(rowIdx - 3).getArrangedPeriodViewList()
										.get(colIdx - 1).getArrangementList().get(0).getTclass().getName());
						continue;
					}
				}
			}
		}

		for (int j = 0; j < 29; j++) {
			tclassSheet.setColumnWidth(j, 600);
			teacherSheet.setColumnWidth(j, 600);
		}
		FileOutputStream fileOutputStream = new FileOutputStream("C:/360Downloads/1.xls");
		workbook.write(fileOutputStream);
		fileOutputStream.close();
		workbook.close();
	}

	@Override
	public void initial(Integer scheduleId) {
		arrangementService.initial(scheduleId);
		return;
	}

}
