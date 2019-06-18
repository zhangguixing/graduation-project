package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Guixing
 * @Date 2019/4/26 13:05
 * @Description 用户角色关联表
 * 如果你的用户只对应一个角色，把前台的多选select改成单选即可，不需要改表结构
 */
@Data
@TableName("sys_user_role")
public class UserRole implements Serializable {
    @TableId
    private Integer id;  // 主键

    private Integer userId;  // 用户id

    private Integer roleId;  // 角色id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;  // 创建时间

    @TableField(exist = false)
    private String roleName;  // 角色名称
}