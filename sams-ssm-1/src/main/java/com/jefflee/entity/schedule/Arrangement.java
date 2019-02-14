package com.jefflee.entity.schedule;

import com.jefflee.entity.information.Course;
import com.jefflee.entity.information.Period;
import com.jefflee.entity.information.Room;
import com.jefflee.entity.information.Tclass;
import com.jefflee.entity.information.Teacher;
import com.jefflee.po.schedule.ArrangementPo;

public class Arrangement {
	private Integer arrangementId;
	private Integer scheduleId;
	private Period period;
	private Course course;
	private Room room;
	private Tclass tclass;
	private Teacher teacher;
	private Integer arranged;
	private Integer priority;

	public Arrangement() {
		this.period = new Period();
		this.course = new Course();
		this.room = new Room();
		this.tclass = new Tclass();
		this.teacher = new Teacher();
	}

	public Arrangement(Integer arrangementId) {
		this.arrangementId = arrangementId;
		this.period = new Period();
		this.course = new Course();
		this.room = new Room();
		this.tclass = new Tclass();
		this.teacher = new Teacher();
	}

	public Arrangement(ArrangementPo arrangementPo) {
		arrangementId = arrangementPo.getArrangementId();
		scheduleId = arrangementPo.getScheduleId();
		period = new Period(arrangementPo.getPeriodId());
		course = new Course(arrangementPo.getCourseId());
		room = new Room(arrangementPo.getRoomId());
		tclass = new Tclass(arrangementPo.getTclassId());
		teacher = new Teacher(arrangementPo.getTeacherId());
		arranged = arrangementPo.getArranged();
		priority = arrangementPo.getPriority();
	}

	public ArrangementPo toPo() {
		ArrangementPo arrangementPo = new ArrangementPo();
		arrangementPo.setArrangementId(arrangementId);
		arrangementPo.setScheduleId(scheduleId);
		arrangementPo.setPeriodId(period.getPeriodId());
		arrangementPo.setCourseId(course.getCourseId());
		arrangementPo.setRoomId(room.getRoomId());
		arrangementPo.setTclassId(tclass.getTclassId());
		arrangementPo.setTeacherId(teacher.getTeacherId());
		arrangementPo.setArranged(arranged);
		arrangementPo.setPriority(priority);
		return arrangementPo;
	}

	public Integer getArrangementId() {
		return arrangementId;
	}

	public void setArrangementId(Integer arrangementId) {
		this.arrangementId = arrangementId;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
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

	public Integer getArranged() {
		return arranged;
	}

	public void setArranged(Integer arranged) {
		this.arranged = arranged;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}