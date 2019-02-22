package com.sams.service;

import com.sams.dao.UserDao;
import com.sams.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void updatePasswordByAccount(String account, String password) {
        userDao.updatePasswordByAccount(account,password);
    }
}
