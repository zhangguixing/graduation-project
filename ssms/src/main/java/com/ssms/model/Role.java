package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Guixing
 * @Date 2019/4/26 13:05
 * @Description 角色表
 */
@Data
@TableName("sys_role")
public class Role implements Serializable {
    @TableId
    private Integer roleId;  // 角色id

    private String roleName;  // 角色名称

    private String comments;  // 描述

    private Integer isDelete;  // 逻辑删除，0未删除，1已删除

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;  // 创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;  // 修改时间

    public Role() {
    }

    public Role(Integer roleId) {
        this.roleId = roleId;
    }

    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}