package com.ssms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssms.dao.ScoreMapper;
import com.ssms.model.Score;
import com.ssms.service.ScoreService;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

}
