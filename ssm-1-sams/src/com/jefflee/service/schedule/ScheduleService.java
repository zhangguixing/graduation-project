package com.jefflee.service.schedule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.jefflee.po.schedule.SchedulePo;
import com.jefflee.view.ScheduleView;

public interface ScheduleService {

	public Integer insert(SchedulePo schedulePo);

	public List<SchedulePo> selectAll();

	public SchedulePo selectById(Integer scheduleId);

	public Integer updateById(SchedulePo schedulePo);

	public Integer deleteById(Integer scheduleId);

	public ScheduleView gnrScheduleView(Integer scheduleId);

	public void gnrEmptyArrangementList(Integer scheduleId);

	public void gnrSchedule(Integer scheduleId);

	public void gnrScheduleViewExcel(Integer scheduleId) throws FileNotFoundException, IOException;

	public void initial(Integer scheduleId);
}
