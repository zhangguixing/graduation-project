package com.jefflee.service.schedule.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.jefflee.entity.information.Course;
import com.jefflee.entity.information.Period;
import com.jefflee.entity.information.Tclass;
import com.jefflee.entity.information.Teacher;
import com.jefflee.entity.schedule.Adjustment;
import com.jefflee.entity.schedule.Arrangement;
import com.jefflee.entity.schedule.Group;
import com.jefflee.entity.schedule.Plan;
import com.jefflee.entity.schedule.Schedule;
import com.jefflee.mapper.schedule.ArrangementMapper;
import com.jefflee.po.schedule.ArrangementPo;
import com.jefflee.po.schedule.PlanPo;
import com.jefflee.service.information.CourseService;
import com.jefflee.service.information.PeriodService;
import com.jefflee.service.information.TclassService;
import com.jefflee.service.information.TeacherService;
import com.jefflee.service.schedule.AdjustmentService;
import com.jefflee.service.schedule.ArrangementService;
import com.jefflee.service.schedule.GroupService;
import com.jefflee.service.schedule.PlanService;
import com.jefflee.util.ArrangementPredicate;
import com.jefflee.util.Cache;
import com.jefflee.view.DayView;
import com.jefflee.view.PeriodView;
import com.jefflee.view.PlanView;
import com.jefflee.view.ScheduleView;
import com.jefflee.view.TitleView;
import com.jefflee.view.WeekView;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("arrangementService")
public class ArrangementServiceImpl implements ArrangementService {

	@Resource(name = "arrangementMapper")
	private ArrangementMapper arrangementMapper;
	@Resource(name = "cache")
	private Cache cache;

	@Resource(name = "periodService")
	private PeriodService periodService;
	@Resource(name = "courseService")
	private CourseService courseService;
	@Resource(name = "tclassService")
	private TclassService tclassService;
	@Resource(name = "teacherService")
	private TeacherService teacherService;

	@Resource(name = "groupService")
	private GroupService groupService;
	@Resource(name = "planService")
	private PlanService planService;
	@Resource(name = "adjustmentService")
	private AdjustmentService adjustmentService;

	/* Initial Block Start */

	@Override
	public void initial(Integer scheduleId) {
		cache.initial(scheduleId);
		List<Arrangement> scheduleArrangementList = cache.getScheduleArrangementList();
		List<Adjustment> scheduleAdjustmentList = adjustmentService.selectTempListByScheduleId(scheduleId);
		mergeAdjustmentList(scheduleArrangementList, scheduleAdjustmentList);
	}

	/* Initial Block End */

	/* CRUD Block Start */

	@Override
	public Integer insert(ArrangementPo arrangementPo) {
		if (arrangementMapper.insert(arrangementPo) == 1) {
			return arrangementPo.getArrangementId();
		} else {
			return null;
		}
	}

	@Override
	public List<ArrangementPo> selectAll() {
		return arrangementMapper.selectAll();
	}

	@Override
	public ArrangementPo selectById(Integer arrangementId) {
		return arrangementMapper.selectByPrimaryKey(arrangementId);
	}

	@Override
	public Integer updateById(ArrangementPo arrangementPo) {
		if (arrangementMapper.updateByPrimaryKey(arrangementPo) == 1) {
			return arrangementPo.getArrangementId();
		} else {
			return null;
		}
	}

	@Override
	public Integer deleteById(Integer arrangementId) {
		if (arrangementMapper.deleteByPrimaryKey(arrangementId) == 1) {
			return arrangementId;
		} else {
			return null;
		}
	}

	@Override
	public Integer insertList(List<ArrangementPo> arrangementPoList) {
		return arrangementMapper.insertList(arrangementPoList);
	}

	/* CRUD Block End */

	/* Display Block Start */

	@Override
	public ScheduleView gnrScheduleView(Schedule schedule) {
		ScheduleView scheduleView = gnrEmptyScheduleView(schedule);

		List<Arrangement> scheduleArrangementList = cache.getScheduleArrangementList();
		List<Plan> schedulePlanList = cache.getSchedulePlanList();
		Map<String, Integer> conflictMap = cache.getBackgroundMap().get("conflicting");
		conflictMap.clear();

		Integer daysPerWeek = schedule.getDays();
		Integer periodsPerDay = schedule.getForenoon() + schedule.getAfternoon() + schedule.getEvening();
		List<Period> periodList = periodService.selectPeriodListByScope(daysPerWeek, periodsPerDay);

		Map<String, WeekView> tclassWeekViewMap = scheduleView.getTclassWeekViewMap();
		Map<String, WeekView> teacherWeekViewMap = scheduleView.getTeacherWeekViewMap();

		WeekView tclassWeekView = null;
		String tclassWeekViewId = "";
		WeekView teacherWeekView = null;
		String teacherWeekViewId = "";

		PlanView tclassPlanView = null;
		String tclassPlanViewId = "";
		PlanView teacherPlanView = null;
		String teacherPlanViewId = "";

		PeriodView tclassPeriodView = null;
		PeriodView teacherPeriodView = null;

		TitleView titleView = null;

		// 新建WeekView
		for (Plan plan : schedulePlanList) {
			Course course = plan.getCourse();
			Tclass tclass = plan.getTclass();
			Teacher teacher = plan.getTeacher();

			if (tclass.getTclassId() != null) {
				tclassWeekViewId = "s" + tclass.getTclassId();
				// 若不存在该班级课表，新建之
				if (!tclassWeekViewMap.containsKey(tclassWeekViewId)) {
					tclassWeekView = gnrEmptyWeekView(schedule.getGroup(), periodList);
					tclassWeekViewMap.put(tclassWeekViewId, tclassWeekView);
					titleView = tclassWeekView.getTitleView();
					titleView.setTclassName(tclassService.gnrName(tclass));
					// 必要时添加
					for (DayView dayView : tclassWeekView.getDayViewList()) {
						for (PeriodView periodView : dayView.getArrangedPeriodViewList()) {
							periodView.setPeriodViewId(periodView.getPeriodViewId() + tclassWeekViewId);
						}
					}
				}

				if (teacher.getTeacherId() != null) {
					Integer courseId = course.getCourseId();
					teacherWeekViewId = "t" + teacher.getTeacherId() + "c" + courseId;
					// 若不存在该教师课程课表，新建之
					// TODO type
					if (courseId != 11 && courseId != 23 && courseId != 24 && courseId != 25) {
						if (!teacherWeekViewMap.containsKey(teacherWeekViewId)) {
							teacherWeekView = gnrEmptyWeekView(schedule.getGroup(), periodList);
							teacherWeekViewMap.put(teacherWeekViewId, teacherWeekView);
							titleView = teacherWeekView.getTitleView();
							titleView.setCourseName(course.getShortName());
							titleView.setGradeName(groupService.gnrGradeName(schedule.getGroup()));
							titleView.setTeacherName(teacher.getName());
							// 必要时添加
							for (DayView dayView : teacherWeekView.getDayViewList()) {
								for (PeriodView periodView : dayView.getArrangedPeriodViewList()) {
									periodView.setPeriodViewId(periodView.getPeriodViewId() + teacherWeekViewId);
								}
							}
						}
					}
				}
			}

		}

		for (Plan plan : schedulePlanList) {
			Course course = plan.getCourse();
			Tclass tclass = plan.getTclass();
			Teacher teacher = plan.getTeacher();

			if (tclass.getTclassId() != null) {
				tclassWeekViewId = "s" + tclass.getTclassId();
				tclassPlanViewId = tclassWeekViewId + "c" + course.getCourseId();
				Integer courseId = course.getCourseId();
				// TODO type
				// if (course.getType() == 0) {
				if (courseId != 23 && courseId != 24 && courseId != 25) {
					tclassWeekView = tclassWeekViewMap.get(tclassWeekViewId);
					if (courseId == 11) {
						tclassWeekView.getTitleView().setTeacherName(plan.getTeacher().getName());
					}
					tclassWeekView.getPlanViewMap().put(tclassPlanViewId, gnrEmptyPlanView(plan));
				}
			}

			if (teacher.getTeacherId() != null) {
				teacherWeekViewId = "t" + teacher.getTeacherId();
				Integer courseId = course.getCourseId();
				// TODO type
				// if (course.getType() == 0) {
				if (courseId != 11 && courseId != 23 && courseId != 24 && courseId != 25) {
					teacherWeekViewId = teacherWeekViewId + "c" + courseId;
					teacherPlanViewId = teacherWeekViewId + "s" + tclass.getTclassId();
					teacherWeekView = teacherWeekViewMap.get(teacherWeekViewId);
					teacherWeekView.getPlanViewMap().put(teacherPlanViewId, gnrEmptyPlanView(plan));
				} else {
					teacherPlanViewId = teacherWeekViewId + "c" + courseId;
					Set<String> existTeacherWeekViewIdList = teacherWeekViewMap.keySet();
					for (String existTeacherWeekViewId : existTeacherWeekViewIdList) {
						if (existTeacherWeekViewId.startsWith(teacherWeekViewId)) {
							teacherWeekView = teacherWeekViewMap.get(existTeacherWeekViewId);
							teacherWeekView.getPlanViewMap().put(teacherPlanViewId, gnrEmptyPlanView(plan));
						}
					}

				}
			}
		}

		for (Arrangement arrangement : scheduleArrangementList) {
			if (arrangement.getArranged() == 1) {
				Period period = arrangement.getPeriod();
				Course course = arrangement.getCourse();
				Tclass tclass = arrangement.getTclass();
				Teacher teacher = arrangement.getTeacher();

				if (tclass.getTclassId() != null) {
					tclassWeekViewId = "s" + tclass.getTclassId();
					tclassPlanViewId = tclassWeekViewId + "c" + course.getCourseId();
					Integer courseId = course.getCourseId();
					// TODO type
					// if (course.getType() == 0) {
					if (courseId != 23 && courseId != 24 && courseId != 25) {
						tclassWeekView = tclassWeekViewMap.get(tclassWeekViewId);
						tclassPeriodView = tclassWeekView.getDayViewList().get(period.getDayOfWeek() - 1)
								.getArrangedPeriodViewList().get(period.getOrderOfDay() - 1);

						if (!tclassPeriodView.getArrangementList().isEmpty()) {
							conflictMap.put(tclassPeriodView.getPeriodViewId(), 0);
						}
						tclassPeriodView.getArrangementList().add(arrangement);
						tclassPeriodView.getJumpViewIdList()
								.add("t" + teacher.getTeacherId() + "c" + course.getCourseId());

						tclassPlanView = tclassWeekView.getPlanViewMap().get(tclassPlanViewId);
						tclassPlanView.setArrangedNum(tclassPlanView.getArrangedNum() + 1);
					}
				}

				if (teacher.getTeacherId() != null) {
					teacherWeekViewId = "t" + teacher.getTeacherId();
					Integer courseId = course.getCourseId();
					// TODO type
					// if (course.getType() == 0) {
					if (courseId != 11 && courseId != 23 && courseId != 24 && courseId != 25) {
						teacherWeekViewId = teacherWeekViewId + "c" + courseId;
						teacherWeekView = teacherWeekViewMap.get(teacherWeekViewId);
						teacherPeriodView = teacherWeekView.getDayViewList().get(period.getDayOfWeek() - 1)
								.getArrangedPeriodViewList().get(period.getOrderOfDay() - 1);

						if (!teacherPeriodView.getArrangementList().isEmpty()) {
							conflictMap.put(teacherPeriodView.getPeriodViewId(), 0);
						}
						teacherPeriodView.getArrangementList().add(arrangement);
						teacherPeriodView.getJumpViewIdList().add("s" + tclass.getTclassId());

						teacherPlanViewId = teacherWeekViewId + "s" + tclass.getTclassId();
						teacherPlanView = teacherWeekView.getPlanViewMap().get(teacherPlanViewId);
						teacherPlanView.setArrangedNum(teacherPlanView.getArrangedNum() + 1);
					} else {
						teacherPlanViewId = teacherWeekViewId + "c" + courseId;
						Set<String> existTeacherWeekViewIdList = teacherWeekViewMap.keySet();
						for (String existTeacherWeekViewId : existTeacherWeekViewIdList) {
							if (existTeacherWeekViewId.startsWith(teacherWeekViewId)) {
								teacherWeekView = teacherWeekViewMap.get(existTeacherWeekViewId);
								teacherPeriodView = teacherWeekView.getDayViewList().get(period.getDayOfWeek() - 1)
										.getArrangedPeriodViewList().get(period.getOrderOfDay() - 1);
								if (!teacherPeriodView.getArrangementList().isEmpty()) {
									conflictMap.put(teacherPeriodView.getPeriodViewId(), 0);
								}
								teacherPeriodView.getArrangementList().add(arrangement);
								teacherPeriodView.getJumpViewIdList().add("s" + tclass.getTclassId());

								teacherPlanView = teacherWeekView.getPlanViewMap().get(teacherPlanViewId);
								teacherPlanView.setArrangedNum(teacherPlanView.getArrangedNum() + 1);
							}
						}
					}
				}
			}
		}
		return scheduleView;
	}

	private ScheduleView gnrEmptyScheduleView(Schedule schedule) {
		ScheduleView scheduleView = new ScheduleView();
		scheduleView.setSchedule(schedule);
		scheduleView.setTclassWeekViewMap(gnrEmptyWeekViewMap());
		scheduleView.setTeacherWeekViewMap(gnrEmptyWeekViewMap());
		return scheduleView;

	}

	private Map<String, WeekView> gnrEmptyWeekViewMap() {
		return new LinkedHashMap<String, WeekView>();
	}

	private WeekView gnrEmptyWeekView(Group group, List<Period> periodList) {
		WeekView weekView = new WeekView();
		weekView.setTitleView(gnrEmptyTitleView(group));
		weekView.setPlanViewMap(gnrEmptyPlanViewMap());
		weekView.setDayViewList(gnrEmptyDayViewList(periodList));
		return weekView;
	}

	private TitleView gnrEmptyTitleView(Group group) {
		TitleView titleView = new TitleView();
		titleView.setStartDate(group.getStartDate());
		return titleView;
	}

	private Map<String, PlanView> gnrEmptyPlanViewMap() {
		return new LinkedHashMap<String, PlanView>();
	}

	private PlanView gnrEmptyPlanView(Plan plan) {
		PlanView planView = new PlanView();
		planView.setPlan(plan);
		planView.setJumpViewId("t" + plan.getTeacher().getTeacherId() + "c" + plan.getCourse().getCourseId());
		planView.setArrangedNum(0);
		planView.setHighestNum(0);
		return planView;
	}

	private List<DayView> gnrEmptyDayViewList(List<Period> periodList) {
		List<DayView> dayViewList = new ArrayList<DayView>();
		Map<Integer, List<Period>> dayPeriodListMap = new HashMap<Integer, List<Period>>();
		for (Period period : periodList) {
			if (!dayPeriodListMap.containsKey(period.getDayOfWeek() - 1)) {
				dayPeriodListMap.put(period.getDayOfWeek() - 1, new ArrayList<Period>());
			}
			dayPeriodListMap.get(period.getDayOfWeek() - 1).add(period.getOrderOfDay() - 1, period);
		}
		for (int i = 0; i < dayPeriodListMap.size(); i++) {
			dayViewList.add(i, gnrEmptyDayView(dayPeriodListMap.get(i)));
		}
		return dayViewList;
	}

	private DayView gnrEmptyDayView(List<Period> dayPeriodList) {
		DayView dayView = new DayView();
		dayView.setArrangedPeriodViewList(gnrEmptyPeriodViewList(dayPeriodList));
		dayView.setHighestPeriodViewList(gnrEmptyPeriodViewList(dayPeriodList));
		return dayView;
	}

	private List<PeriodView> gnrEmptyPeriodViewList(List<Period> dayPeriodList) {
		List<PeriodView> periodViewList = new ArrayList<PeriodView>();
		for (Period period : dayPeriodList) {
			periodViewList.add(period.getOrderOfDay() - 1, gnrEmptyPeriodView(period));
		}
		return periodViewList;
	}

	private PeriodView gnrEmptyPeriodView(Period period) {
		PeriodView periodView = new PeriodView();
		List<Arrangement> arrangementList = new ArrayList<Arrangement>();
		List<String> jumpViewIdList = new ArrayList<String>();
		periodView.setArrangementList(arrangementList);
		periodView.setJumpViewIdList(jumpViewIdList);
		periodView.setPeriodViewId("p" + period.getPeriodId());
		return periodView;
	}

	/* Display Block End */

	/* Conflict Block Start */

	private boolean isConflictive(Arrangement first, Arrangement second) {
		// TODO 适当设置课程类型以求精简判断
		if (first.getPeriod().getPeriodId() != second.getPeriod().getPeriodId()) {
			return false;
		}
		if (first.getCourse().getType() == 1 && second.getCourse().getType() == 1) {
			if (first.getCourse().getCourseId() != 11
					&& first.getCourse().getCourseId() == second.getCourse().getCourseId()) {
				return false;
			}
		}
		if (first.getRoom().getRoomId() == second.getRoom().getRoomId()) {
			return true;
		}
		if (first.getTclass().getTclassId() == second.getTclass().getTclassId()) {
			return true;
		}
		if (first.getTeacher().getTeacherId() == second.getTeacher().getTeacherId()) {
			return true;
		}
		return false;
	}

	/* Conflict Block End */

	/* Arrange Block Start */

	@Override
	public void gnrArrangementList(Integer scheduleId) {
		clearArrangedArrangement(scheduleId);

		Arrangement queryArrangedArrangement = new Arrangement();
		Arrangement queryPriorityArrangement = new Arrangement();
		Arrangement queryIdArrangement = new Arrangement();

		List<Arrangement> scheduleArrangementList = cache.getScheduleArrangementList();
		queryArrangedArrangement.setArranged(0);
		List<Arrangement> unarrangedArrangementList = (List<Arrangement>) CollectionUtils
				.select(scheduleArrangementList, new ArrangementPredicate(queryArrangedArrangement));
		List<PlanPo> schedulePlanPoList = planService.selectByScheduleId(scheduleId);

		Integer courseId;
		Integer tclassId;
		Integer arrangedNum;
		Integer periodNum;
		List<Arrangement> arrangedArrangementList = new ArrayList<Arrangement>();

		// TODO 重新调整排课逻辑
		while (!unarrangedArrangementList.isEmpty()) {
			for (int priority = 4; priority > 0; priority--) {
				queryPriorityArrangement.setPriority(priority);
				List<Arrangement> priorityArrangementList = (List<Arrangement>) CollectionUtils
						.select(unarrangedArrangementList, new ArrangementPredicate(queryPriorityArrangement));
				if (!priorityArrangementList.isEmpty()) {
					Arrangement chosenArrangement = chooseRandomly(priorityArrangementList);
					courseId = chosenArrangement.getCourse().getCourseId();
					tclassId = chosenArrangement.getTclass().getTclassId();

					queryArrangedArrangement.setArranged(1);
					arrangedArrangementList = (List<Arrangement>) CollectionUtils.select(scheduleArrangementList,
							new ArrangementPredicate(queryArrangedArrangement));

					queryIdArrangement.getCourse().setCourseId(courseId);
					queryIdArrangement.getTclass().setTclassId(tclassId);
					arrangedNum = ((List<Arrangement>) CollectionUtils.select(arrangedArrangementList,
							new ArrangementPredicate(queryIdArrangement))).size();
					periodNum = planService
							.selectByCourseId(planService.selectByTclassId(schedulePlanPoList, tclassId), courseId)
							.get(0).getPeriodNum();
					if (periodNum - arrangedNum <= 0) {
						chosenArrangement.setArranged(-1);
						break;
					}
					for (Arrangement arrangement : unarrangedArrangementList) {
						if (isConflictive(chosenArrangement, arrangement)) {
							arrangement.setArranged(-1);
						}
					}
					chosenArrangement.setArranged(1);
					break;
				}
			}
			queryArrangedArrangement.setArranged(0);
			unarrangedArrangementList = (List<Arrangement>) CollectionUtils.select(unarrangedArrangementList,
					new ArrangementPredicate(queryArrangedArrangement));
		}
		for (Arrangement arrangement : scheduleArrangementList) {
			updateArrangement(arrangement);
		}
		return;
	}

	private void clearArrangedArrangement(Integer scheduleId) {
		ArrangementPo arrangementPo = new ArrangementPo();
		arrangementPo.setArranged(0);
		Example example = new Example(ArrangementPo.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("scheduleId", scheduleId);
		arrangementMapper.updateByExampleSelective(arrangementPo, example);
	}
	/* Arrange Block End */

	/* Adjust Block Start */

	@Override
	public void doAdjust(Integer adjustmentId) {
		List<Arrangement> arrangementList = cache.getScheduleArrangementList();
		Adjustment adjustment = adjustmentService.selectAdjustmentById(adjustmentId);
		adjust(arrangementList, adjustment);
	}

	@Override
	public void undoAdjust(Integer adjustmentId) {
		List<Arrangement> arrangementList = cache.getScheduleArrangementList();
		Adjustment adjustment = adjustmentService.selectAdjustmentById(adjustmentId);
		reverseAdjust(arrangementList, adjustment);
	}

	private void mergeAdjustmentList(List<Arrangement> arrangementList, List<Adjustment> adjustmentList) {
		for (Adjustment adjustment : adjustmentList) {
			adjust(arrangementList, adjustment);
		}
	}

	@Override
	public void saveAdjustment(Integer scheduleId) {
		List<Adjustment> scheduleAdjustmentList = adjustmentService.selectTempListByScheduleId(scheduleId);
		for (Adjustment adjustment : scheduleAdjustmentList) {
			saveAdjust(adjustment);
			adjustmentService.deletePoById(adjustment.getAdjustmentId());
		}
	}

	private void saveAdjust(Adjustment adjustment) {
		String firstId = adjustment.getFirstId();
		String secondId = adjustment.getSecondId();
		Map<String, Integer> firstIdPairs = parseViewId(firstId);
		Map<String, Integer> secondIdPairs = parseViewId(secondId);

		if (adjustment.getType() == 0) {
			saveSwap(firstIdPairs, secondIdPairs);
		} else if (adjustment.getType() == 1) {
			saveAdd(firstIdPairs, secondIdPairs);
		} else if (adjustment.getType() == 2) {
			saveRemove(firstIdPairs, secondIdPairs);
		}
	}

	private void saveSwap(Map<String, Integer> firstIdPairs, Map<String, Integer> secondIdPairs) {
		ArrangementPo addedArrangementPo = new ArrangementPo();
		ArrangementPo cancelledArrangementPo = new ArrangementPo();
		addedArrangementPo.setArranged(1);
		cancelledArrangementPo.setArranged(-1);

		Integer firstPeriodId = firstIdPairs.get("p");
		Integer firstTclassId = firstIdPairs.get("s");
		Integer secondPeriodId = secondIdPairs.get("p");
		Integer secondTclassId = secondIdPairs.get("s");

		Example firstExample = new Example(ArrangementPo.class);
		Criteria firstCriteria = firstExample.createCriteria();
		firstCriteria.andEqualTo("periodId", firstPeriodId);
		firstCriteria.andEqualTo("tclassId", firstTclassId);
		firstCriteria.andEqualTo("arranged", 1);
		List<ArrangementPo> firstArrangementPoList = arrangementMapper.selectByExample(firstExample);
		Integer firstCourseId = firstArrangementPoList.get(0).getCourseId();

		Example secondExample = new Example(ArrangementPo.class);
		Criteria secondCriteria = secondExample.createCriteria();
		secondCriteria.andEqualTo("periodId", secondPeriodId);
		secondCriteria.andEqualTo("tclassId", secondTclassId);
		secondCriteria.andEqualTo("arranged", 1);
		List<ArrangementPo> secondArrangementPoList = arrangementMapper.selectByExample(secondExample);
		Integer secondCourseId = secondArrangementPoList.get(0).getCourseId();

		arrangementMapper.updateByExampleSelective(cancelledArrangementPo, firstExample);
		arrangementMapper.updateByExampleSelective(cancelledArrangementPo, secondExample);

		firstCriteria.getCriteria().remove(firstCriteria.getCriteria().size() - 1);
		firstCriteria.andEqualTo("courseId", secondCourseId);
		firstCriteria.andEqualTo("arranged", -1);

		secondCriteria.getCriteria().remove(secondCriteria.getCriteria().size() - 1);
		secondCriteria.andEqualTo("courseId", firstCourseId);
		secondCriteria.andEqualTo("arranged", -1);

		arrangementMapper.updateByExampleSelective(addedArrangementPo, firstExample);
		arrangementMapper.updateByExampleSelective(addedArrangementPo, secondExample);
	}

	private void saveAdd(Map<String, Integer> firstIdPairs, Map<String, Integer> secondIdPairs) {
		ArrangementPo addedArrangementPo = new ArrangementPo();
		addedArrangementPo.setArranged(1);

		Integer firstCourseId = firstIdPairs.get("c");
		Integer secondPeriodId = secondIdPairs.get("p");
		Integer secondTclassId = secondIdPairs.get("s");

		Example example = new Example(ArrangementPo.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("periodId", secondPeriodId);
		criteria.andEqualTo("courseId", firstCourseId);
		criteria.andEqualTo("tclassId", secondTclassId);

		arrangementMapper.updateByExampleSelective(addedArrangementPo, example);
	}

	private void saveRemove(Map<String, Integer> firstIdPairs, Map<String, Integer> secondIdPairs) {
		ArrangementPo cancelledArrangementPo = new ArrangementPo();
		cancelledArrangementPo.setArranged(-1);

		Integer secondPeriodId = secondIdPairs.get("p");
		Integer secondTclassId = secondIdPairs.get("s");

		Example example = new Example(ArrangementPo.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("periodId", secondPeriodId);
		criteria.andEqualTo("tclassId", secondTclassId);

		arrangementMapper.updateByExampleSelective(cancelledArrangementPo, example);
	}

	private void adjust(List<Arrangement> arrangementList, Adjustment adjustment) {
		String firstId = adjustment.getFirstId();
		String secondId = adjustment.getSecondId();
		Map<String, Integer> firstIdPairs = parseViewId(firstId);
		Map<String, Integer> secondIdPairs = parseViewId(secondId);
		Integer type = adjustment.getType();

		switch (type) {
		case 0:
			swap(arrangementList, firstIdPairs, secondIdPairs);
			break;
		case 1:
			add(arrangementList, firstIdPairs, secondIdPairs);
			break;
		case 2:
			remove(arrangementList, firstIdPairs, secondIdPairs);
			break;
		default:
			break;
		}
	}

	private void reverseAdjust(List<Arrangement> arrangementList, Adjustment adjustment) {
		String firstId = adjustment.getFirstId();
		String secondId = adjustment.getSecondId();
		Map<String, Integer> firstIdPairs = parseViewId(firstId);
		Map<String, Integer> secondIdPairs = parseViewId(secondId);
		Integer type = adjustment.getType();

		switch (type) {
		case 0:
			swap(arrangementList, firstIdPairs, secondIdPairs);
			break;
		case 1:
			remove(arrangementList, firstIdPairs, secondIdPairs);
			break;
		case 2:
			add(arrangementList, firstIdPairs, secondIdPairs);
			break;
		default:
			break;
		}
	}

	private void swap(List<Arrangement> arrangementList, Map<String, Integer> firstIdPairs,
			Map<String, Integer> secondIdPairs) {
		Arrangement firstArrangement = new Arrangement();
		Arrangement secondArrangement = new Arrangement();

		Integer firstPeriodId = firstIdPairs.get("p");
		Integer firstTclassId = firstIdPairs.get("s");
		Integer secondPeriodId = secondIdPairs.get("p");
		Integer secondTclassId = secondIdPairs.get("s");

		firstArrangement.getPeriod().setPeriodId(firstPeriodId);
		firstArrangement.getTclass().setTclassId(firstTclassId);
		firstArrangement.setArranged(1);
		Collection<Arrangement> firstCancelledArrangementCollection = CollectionUtils.select(arrangementList,
				new ArrangementPredicate(firstArrangement));
		Arrangement firstCancelledArrangement = firstCancelledArrangementCollection.iterator().next();
		Integer firstCourseId = firstCancelledArrangement.getCourse().getCourseId();

		secondArrangement.getPeriod().setPeriodId(secondPeriodId);
		secondArrangement.getTclass().setTclassId(secondTclassId);
		secondArrangement.setArranged(1);
		Collection<Arrangement> secondCancelledArrangementCollection = CollectionUtils.select(arrangementList,
				new ArrangementPredicate(secondArrangement));
		Arrangement secondCancelledArrangement = secondCancelledArrangementCollection.iterator().next();
		Integer secondCourseId = secondCancelledArrangement.getCourse().getCourseId();

		for (Arrangement arrangement : firstCancelledArrangementCollection) {
			arrangement.setArranged(-1);
		}
		for (Arrangement arrangement : secondCancelledArrangementCollection) {
			arrangement.setArranged(-1);
		}

		firstArrangement.getCourse().setCourseId(secondCourseId);
		firstArrangement.setArranged(-1);
		Collection<Arrangement> firstAddedArrangementCollection = CollectionUtils.select(arrangementList,
				new ArrangementPredicate(firstArrangement));

		secondArrangement.getCourse().setCourseId(firstCourseId);
		secondArrangement.setArranged(-1);
		Collection<Arrangement> secondAddedArrangementCollection = CollectionUtils.select(arrangementList,
				new ArrangementPredicate(secondArrangement));

		for (Arrangement arrangement : firstAddedArrangementCollection) {
			arrangement.setArranged(1);
		}
		for (Arrangement arrangement : secondAddedArrangementCollection) {
			arrangement.setArranged(1);
		}

	}

	private void add(List<Arrangement> arrangementList, Map<String, Integer> firstIdPairs,
			Map<String, Integer> secondIdPairs) {
		Arrangement queryArrangement = new Arrangement();

		Integer firstCourseId = firstIdPairs.get("c");
		Integer secondPeriodId = secondIdPairs.get("p");
		Integer secondTclassId = secondIdPairs.get("s");

		queryArrangement.getPeriod().setPeriodId(secondPeriodId);
		queryArrangement.getCourse().setCourseId(firstCourseId);
		queryArrangement.getTclass().setTclassId(secondTclassId);
		Collection<Arrangement> addedArrangementCollection = CollectionUtils.select(arrangementList,
				new ArrangementPredicate(queryArrangement));
		for (Arrangement arrangement : addedArrangementCollection) {
			arrangement.setArranged(1);
		}
	}

	private void remove(List<Arrangement> arrangementList, Map<String, Integer> firstIdPairs,
			Map<String, Integer> secondIdPairs) {
		Arrangement queryArrangement = new Arrangement();

		Integer secondPeriodId = secondIdPairs.get("p");
		Integer secondTclassId = secondIdPairs.get("s");

		queryArrangement.getPeriod().setPeriodId(secondPeriodId);
		queryArrangement.getTclass().setTclassId(secondTclassId);
		queryArrangement.setArranged(1);
		Collection<Arrangement> cancelledArrangementCollection = CollectionUtils.select(arrangementList,
				new ArrangementPredicate(queryArrangement));
		for (Arrangement arrangement : cancelledArrangementCollection) {
			arrangement.setArranged(-1);
		}
	}

	/* Adjust Block End */

	/* Tool Block Start */

	private <T> T chooseRandomly(List<T> tList) {
		Random random = new Random();
		T t = tList.get(random.nextInt(tList.size()));
		return t;
	}

	private Map<String, Integer> parseViewId(String viewId) {
		Map<String, Integer> idPairs = new HashMap<String, Integer>();

		Pattern attrPattern = Pattern.compile("[0-9]+");
		Pattern numPattern = Pattern.compile("[a-z]+");

		String[] attrArray = attrPattern.split(viewId);
		String[] numArray = numPattern.split(viewId);

		for (int i = 0; i < attrArray.length; i++) {
			idPairs.put(attrArray[i], Integer.parseInt(numArray[i + 1]));
		}
		return idPairs;
	}
	/* Tool Block End */

	private void updateArrangement(Arrangement arrangement) {
		ArrangementPo arrangementPo = arrangement.toPo();
		arrangementMapper.updateByPrimaryKeySelective(arrangementPo);
		return;
	}

	@Override
	public Arrangement selectArrangementById(Integer arrangementId) {
		return arrangementMapper.selectEntityById(arrangementId);
	}

	@Override
	public Map<String, Map<String, Integer>> getBackgroundMap(Integer scheduleId) {
		Map<String, Integer> adjustedMap = cache.getBackgroundMap().get("adjusted");
		adjustedMap.clear();
		List<Adjustment> adjustmentList = adjustmentService.selectTempListByScheduleId(scheduleId);
		Integer type = null;
		String firstId = null;
		String secondId = null;
		for (Adjustment adjustment : adjustmentList) {
			type = adjustment.getType();
			firstId = adjustment.getFirstId();
			secondId = adjustment.getSecondId();
			switch (type) {
			case 0:
				adjustedMap.put(firstId, 0);
			case 1:
			case 2:
				adjustedMap.put(secondId, 0);
				break;
			default:
				break;
			}
		}
		return cache.getBackgroundMap();
	}

}
