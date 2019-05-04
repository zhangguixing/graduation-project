package com.ssms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.dao.ScoreMapper;
import com.ssms.model.User;
import com.ssms.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public PageInfo<Map<String, Object>> listScore(Integer pageNum, Integer pageSize, User user, String searchKey, String searchValue) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> listScore = null;
        if(StringUtil.isBlank()){
            searchKey = null;
        }
        if(user.getPersonType() != User.STUDENT_TYPE){
            //管理员--教师--查询所有
            listScore = scoreMapper.listScore(null, null, null, null, null,searchKey,searchValue);
        }else{
            //学生--查询所在班级
            Integer collegeId = user.getCollegeId();
            Integer subjectId = user.getSubjectId();
            Integer classId = user.getClassId();
            listScore = scoreMapper.listDefaultScore(collegeId,subjectId,classId,searchKey,searchValue);
        }
        //表中无符合条件数据
        if(listScore.size()<=1){
            if(Integer.valueOf(listScore.get(0).get("id").toString()).intValue()==0){
                listScore = new ArrayList<>();
            }
        }
        return PageInfo.of(listScore);
    }
}
