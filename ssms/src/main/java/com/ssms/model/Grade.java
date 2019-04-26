package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Guixing
 * @Date 2019/4/26 13:22
 * @Description 年级表
 */
@Data
@TableName("info_grade")
public class Grade implements Serializable {
    @TableId
    private Integer id; //主键

    private String name;    //  年级名称

    private Date createTime;  // 创建时间

    private Date updateTime;  // 修改时间

    private Integer status;  //状态，0删除，1正常
}
