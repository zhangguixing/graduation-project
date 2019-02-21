package com.sams.service;

import com.sams.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    public String getPasswordByAccont(String account){
        return userDao.getPasswordByAccount(account);
    }
}
