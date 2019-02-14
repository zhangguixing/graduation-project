package com.jefflee.entity.Grade;

import lombok.Data;

import java.util.Date;

/**
 * Created by TGL on 2018/5/2.
 */
@Data
public class Exam {
    private int id;

    private int sr_course_id;//课程id

    private int room_id;//考场id

    private String exam_no;//考试场次

    private String exam_name;//考试名称

    private Date exam_time;//考试时间

    private String exam_supervisor;//监考人名字
}
