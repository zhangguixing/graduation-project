package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
    @TableId
    private Integer id; //主键

    private String name;    //学院/专业/班级名称

    private Integer parentId; //父id，值为0表示学院，否则表示父id

    private Date createTime;  // 创建时间

    private Date updateTime;  // 修改时间

    private Integer status;  //状态，0删除，1正常
}
