package com.ssms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssms.dao.NoticeMapper;
import com.ssms.model.Notice;
import com.ssms.service.NoticeService;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
