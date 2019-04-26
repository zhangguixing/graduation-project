package com.ssms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ssms.dao.RoomMapper;
import com.ssms.dao.ScoreMapper;
import com.ssms.model.Room;
import com.ssms.model.Score;
import com.ssms.service.RoomService;
import com.ssms.service.ScoreService;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

}
