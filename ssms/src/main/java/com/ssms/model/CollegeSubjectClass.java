package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Guixing
 * @Date 2019/4/26 13:58
 * @Description 学院专业班级信息表
 */
@Data
@TableName("info_college_subject_class")
public class CollegeSubjectClass implements Serializable {

    public static final Integer TYPE_COLLEGE = 0;   //学院
    public static final Integer TYPE_SUBJECT = 1;   //专业
    public static final Integer TYPE_CLASS = 2;     //班级

    @TableId
    private Integer id; //主键

    private String name;    //学院/专业/班级名称

    private Integer orderNumber;    //排序号

    private Integer parentId; //父id，值为0表示学院，否则表示父id

    private Integer typeNumber; // 类型，0学院，1专业，2班级

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;  // 创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;  // 修改时间
}
