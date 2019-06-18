package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("info_course_timetable")
public class CourseTimeTable implements Serializable {

    @TableId
    private Integer id;     //主键

    private Integer startWeekNum;    //开始周数

    private Integer endWeekNum;    //结束周数

    private Integer courseId;   //  课程id

    private Integer teacherId;     //教师id

    private String teacherName;    //教师名称

    private Integer dayOfWeek;      //星期几

    private Integer startLesson;    //开始上课节数

    private Integer endLesson;  //结束上课节数

    private String address;    //上课地点

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;  // 创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;  // 修改时间
}
