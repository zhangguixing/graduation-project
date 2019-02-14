package com.jefflee.entity.Grade;

import lombok.Data;

/**
 * Created by TGL on 2018/5/2.
 */
@Data
public class Score {
    private int id;

    private int student_id;//课程id

    private int exam_id;//考场id

    private String score;//考试场次

}
