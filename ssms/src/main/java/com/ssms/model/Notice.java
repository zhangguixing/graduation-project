package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Guixing
 * @Date 2019/4/26 13:05
 * @Description 系统通知
 */
@Data
@TableName("sys_notice")
public class Notice implements Serializable {
    @TableId
    private Integer id;        //主键

    private String content;

    private Date createTime;  // 创建时间

    private Date updateTime;  // 修改时间

    private Integer status;  //状态，0删除，1正常
}
