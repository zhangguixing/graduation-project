package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Guixing
 * @Date 2019/4/26 13:05
 * @Description 角色权限关联表
 */
@Data
@TableName("sys_role_authorities")
public class RoleAuthorities implements Serializable {
    @TableId
    private Integer id;  // 主键，也可以用联合主键，根据个人习惯

    private Integer roleId;  // 角色id

    private Integer authorityId;  // 权限id

    private Date createTime;  // 添加时间
}