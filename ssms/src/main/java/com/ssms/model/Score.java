package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Guixing
 * @Date 2019/4/26 13:16
 * @Description 成绩表
 */
@Data
@TableName("info_score")
public class Score implements Serializable {
    @TableId
    private Integer id; //主键

    private BigDecimal score;   //成绩

    private Integer courseId;   //课程id

    private Integer studentId;  //学生id

    private Integer collegeId;  //学院id

    private Integer subjectId;  //专业id

    private Integer classId;    //班级id

    private Integer gradeId;    //年级id

    private String schoolYear;  //学年，2019-2020

    private Integer semester;   //学期 1上学期，2下学期

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;  // 创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;  // 修改时间


}
