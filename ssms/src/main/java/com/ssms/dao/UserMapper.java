package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

    List<Map<String, Object>> listUserIdAndName(@Param("collegeId") Integer collegeId, @Param("subjectId") Integer subjectId, @Param("classId") Integer classId, @Param("gradeId") Integer gradeId, @Param("userType") Integer userType);
}
