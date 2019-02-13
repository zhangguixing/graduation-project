package com.jefflee.util;

import java.util.Objects;

import org.apache.commons.collections4.Predicate;

import com.jefflee.entity.schedule.Arrangement;

public class ConflictPredicate implements Predicate<Arrangement> {

	private Arrangement arrangement;

	public ConflictPredicate(Arrangement arrangement) {
		this.arrangement = arrangement;
	}

	// TODO 待优化
	@Override
	public boolean evaluate(Arrangement arrangement) {

		Integer periodId1 = this.arrangement.getPeriod().getPeriodId();
		Integer courseId1 = this.arrangement.getCourse().getCourseId();
		Integer courseType1 = this.arrangement.getCourse().getType();
		Integer roomId1 = this.arrangement.getRoom().getRoomId();
		Integer tclassId1 = this.arrangement.getTclass().getTclassId();
		Integer teacherId1 = this.arrangement.getTeacher().getTeacherId();

		Integer periodId2 = arrangement.getPeriod().getPeriodId();
		Integer courseId2 = arrangement.getCourse().getCourseId();
		Integer courseType2 = arrangement.getCourse().getType();
		Integer roomId2 = arrangement.getRoom().getRoomId();
		Integer tclassId2 = arrangement.getTclass().getTclassId();
		Integer teacherId2 = arrangement.getTeacher().getTeacherId();

		// TODO 适当设置课程类型以求精简判断
		if (periodId1 != periodId2) {
			return false;
		}
		// TODO type
		if (courseType1 == 1 && courseType2 == 1) {
			if (courseId1 != 11 && Objects.equals(courseId1, courseId2)) {
				return false;
			}
		}
		if (Objects.equals(roomId1, roomId2)) {
			return true;
		}
		if (Objects.equals(tclassId1, tclassId2)) {
			return true;
		}
		if (Objects.equals(teacherId1, teacherId2)) {
			return true;
		}
		return false;
	}

}
