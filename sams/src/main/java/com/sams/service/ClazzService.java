package com.sams.service;

import com.sams.dao.ClazzDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClazzService {
    @Autowired
    private ClazzDao clazzDao;

}
