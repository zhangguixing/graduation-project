package com.sams.service;

import com.sams.dao.UserDao;
import com.sams.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    public User getPasswordByAccontAndType(String account, int type){
        return userDao.getPasswordByAccontAndType(account,type);
    }
}
