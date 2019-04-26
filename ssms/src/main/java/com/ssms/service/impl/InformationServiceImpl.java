package com.ssms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssms.dao.InformationMapper;
import com.ssms.model.Information;
import com.ssms.service.InformationService;
import org.springframework.stereotype.Service;

@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService {

}
