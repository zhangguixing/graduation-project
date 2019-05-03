package com.ssms.service;

import com.github.pagehelper.PageInfo;
import com.ssms.model.User;

import java.util.Map;

public interface ScoreService {
    PageInfo<Map<String,Object>> listScore(Integer pageNum, Integer pageSize, User user);
}
