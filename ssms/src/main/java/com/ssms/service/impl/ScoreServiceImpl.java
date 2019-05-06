package com.ssms.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.dao.ScoreMapper;
import com.ssms.service.ScoreService;
import com.ssms.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public PageInfo<Map<String, Object>> listScore(Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String searchKey, String searchValue) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String, Object>> listScore = null;
        if(StringUtil.isBlank()){
            searchKey = null;
        }
        listScore = scoreMapper.listScore(gradeId, collegeId, subjectId, classId, schoolYear, semester, searchKey, searchValue);
        //表中无符合条件数据
        if(listScore.size()<=1){
            if(Integer.valueOf(listScore.get(0).get("id").toString()).intValue()==0){
                listScore = new ArrayList<>();
            }
        }
        return PageInfo.of(listScore);
    }

    @Override
    public List<Map<String, Object>> getPersonScore(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, Integer studentId, String schoolYear, Integer semester) {
        return scoreMapper.getPersonScore(gradeId,collegeId,subjectId,classId,studentId,schoolYear,semester);
    }
}
