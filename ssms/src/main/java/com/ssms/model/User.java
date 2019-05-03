package com.ssms.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Guixing
 * @Date 2019/4/26 13:05
 * @Description 用户表
 */
@Data
@TableName("sys_user")
public class User implements Serializable {

    public static final Integer SUPER_ADMIN_TYPE = 0;     //超级管理员
    public static final Integer ADMIN_TYPE = 1;     //管理员
    public static final Integer TEACHER_TYPE = 2;   //教师
    public static final Integer STUDENT_TYPE = 3;   //学生

    @TableId
    private Integer userId;  // 主键id

    private String username;  // 账号

    private String password;  // 密码

    private String nickName;  // 昵称

    private String avatar;  // 头像

    private String sex;  // 性别

    private String phone;  // 手机号

    private String email;  // 邮箱

    private Integer personType;  // 人员类型，比如：0超级管理员，1管理员，2教师，3学生

    private Integer collegeId;  // 所属学院id

    private Integer subjectId;  // 所属专业id

    private Integer classId;  // 所属年级id

    private Integer gradeId;  // 邮箱

    private Integer state;  // 用户状态，0正常，1锁定

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;  // 注册时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;  // 修改时间

    @TableField(exist = false)
    private List<Role> roles;  //角色
}
