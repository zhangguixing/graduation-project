package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Guixing
 * @Date 2019/4/26 13:14
 * @Description 系统信息表
 */
@Data
@TableName("sys_information")
public class Information implements Serializable {
    @TableId
    private Integer id; //主键

    private String message; //系统欢迎信息

    private String schoolName; //学校名称

    private String developerName; //系统开发者

    private String information; //系统简介

    private Date createTime;  // 创建时间

    private Date updateTime;  // 修改时间
}
