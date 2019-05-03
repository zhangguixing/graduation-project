package com.ssms.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.dao.ScoreMapper;
import com.ssms.model.User;
import com.ssms.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public PageInfo<Map<String, Object>> listScore(Integer pageNum, Integer pageSize, User user) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> listScore = null;
        if(user.getPersonType() == User.ADMIN_TYPE || user.getPersonType() == User.SUPER_ADMIN_TYPE){
            //管理员--查询所有
            listScore = scoreMapper.listScore(null, null, null, null, null);
        }else{
            //教师/学生--查询所在班级
            Integer collegeId = user.getCollegeId();
            Integer subjectId = user.getSubjectId();
            Integer classId = user.getClassId();
            listScore = scoreMapper.listDefaultScore(collegeId,subjectId,classId);
        }
        //表中无符合条件数据
        if(listScore.size()==1){
            if(Integer.valueOf(listScore.get(0).get("id").toString()).intValue()==0){
                listScore = null;
            }
        }
        return PageInfo.of(listScore);
    }
}
