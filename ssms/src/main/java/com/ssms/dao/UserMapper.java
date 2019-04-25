package com.ssms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ssms.model.User;

public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);
}
