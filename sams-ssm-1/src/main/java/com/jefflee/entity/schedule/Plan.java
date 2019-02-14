package com.jefflee.entity.schedule;

import com.jefflee.entity.information.Course;
import com.jefflee.entity.information.Room;
import com.jefflee.entity.information.Tclass;
import com.jefflee.entity.information.Teacher;
import com.jefflee.po.schedule.PlanPo;

public class Plan {
	private Integer planId;
	private Integer scheduleId;
	private Course course;
	private Room room;
	private Tclass tclass;
	private Teacher teacher;
	private Integer periodNum;

	public Plan() {
		this.course = new Course();
		this.room = new Room();
		this.tclass = new Tclass();
		this.teacher = new Teacher();
	}

	public Plan(Integer planId) {
		this.planId = planId;
		this.course = new Course();
		this.room = new Room();
		this.tclass = new Tclass();
		this.teacher = new Teacher();
	}

	public Plan(PlanPo planPo) {
		planId = planPo.getPlanId();
		scheduleId = planPo.getScheduleId();
		course = new Course(planPo.getCourseId());
		room = new Room(planPo.getRoomId());
		tclass = new Tclass(planPo.getTclassId());
		teacher = new Teacher(planPo.getTeacherId());
		periodNum = planPo.getPeriodNum();
	}

	public PlanPo toPo() {
		PlanPo planPo = new PlanPo();
		planPo.setPlanId(planId);
		planPo.setScheduleId(scheduleId);
		planPo.setCourseId(course.getCourseId());
		planPo.setRoomId(room.getRoomId());
		planPo.setTclassId(tclass.getTclassId());
		planPo.setTeacherId(teacher.getTeacherId());
		planPo.setPeriodNum(periodNum);
		return planPo;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Tclass getTclass() {
		return tclass;
	}

	public void setTclass(Tclass tclass) {
		this.tclass = tclass;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getPeriodNum() {
		return periodNum;
	}

	public void setPeriodNum(Integer periodNum) {
		this.periodNum = periodNum;
	}
}
