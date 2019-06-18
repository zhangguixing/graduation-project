package com.ssms.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class UserModel extends BaseRowModel {
    //姓名
    @ExcelProperty(index = 0)
    private String username;
    //学号/工号
    @ExcelProperty(index = 1)
    private String nickName;
    //性别
    @ExcelProperty(index = 2)
    private String sex;
    //手机号
    @ExcelProperty(index = 3)
    private String phone;
    //学院
    @ExcelProperty(index = 4)
    private String collegeName;
    //专业
    @ExcelProperty(index = 5)
    private String subjectName;
    //班级
    @ExcelProperty(index = 6)
    private String className;
    //年级
    @ExcelProperty(index = 7)
    private String gradeName;
}
