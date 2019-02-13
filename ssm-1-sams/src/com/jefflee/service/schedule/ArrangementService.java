package com.jefflee.service.schedule;

import java.util.List;
import java.util.Map;

import com.jefflee.entity.schedule.Arrangement;
import com.jefflee.entity.schedule.Schedule;
import com.jefflee.po.schedule.ArrangementPo;
import com.jefflee.view.ScheduleView;

public interface ArrangementService {

	Integer insert(ArrangementPo arrangementPo);

	List<ArrangementPo> selectAll();

	ArrangementPo selectById(Integer arrangementId);

	Integer updateById(ArrangementPo arrangementPo);

	Integer deleteById(Integer arrangementId);

	Arrangement selectArrangementById(Integer arrangementId);

	void gnrArrangementList(Integer scheduleId);

	Integer insertList(List<ArrangementPo> arrangementPoList);

	void initial(Integer scheduleId);

	ScheduleView gnrScheduleView(Schedule schedule);

	void doAdjust(Integer adjustmentId);

	void undoAdjust(Integer adjustmentId);

	Map<String, Map<String, Integer>> getBackgroundMap(Integer scheduleId);

	void saveAdjustment(Integer scheduleId);

}
