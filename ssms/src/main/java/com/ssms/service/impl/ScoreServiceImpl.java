package com.ssms.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.dao.ScoreMapper;
import com.ssms.service.ScoreService;
import com.ssms.common.util.StringUtil;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public PageInfo<Map<String, Object>> listScore(Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String searchKey, String searchValue) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> listScore = null;
        if (StringUtil.isBlank()) {
            searchKey = null;
        }
        listScore = scoreMapper.listScore(gradeId, collegeId, subjectId, classId, schoolYear, semester, searchKey, searchValue);
        //表中无符合条件数据
        if (CollectionUtils.isEmpty(listScore) || CollectionUtils.isEmpty(listScore.get(0)) || listScore.get(0).get("id").toString().equals("0")) {
            listScore = new ArrayList<>();
        }
        return PageInfo.of(listScore);
    }

    @Override
    public List<Map<String, Object>> getPersonScore(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, Integer studentId, String schoolYear, Integer semester) {
        return scoreMapper.getPersonScore(gradeId, collegeId, subjectId, classId, studentId, schoolYear, semester);
    }

    @Override
    public Map<String, Object> getChartsData(Integer studentId) {
        List<Map<String, Object>> maps = scoreMapper.getChartsData(studentId);
        Map<String, Object> chartsDatas = new HashMap<>();
        List<String> xData = new ArrayList<>();
        List<BigDecimal> yData = new ArrayList<>();
        String[] semesterArray = {"上学期","下学期"};
        for (Map<String, Object> map:maps) {
            String schoolYear = map.get("schoolYear")==null?"":map.get("schoolYear").toString();
            Integer semester = map.get("semester")==null?1:Integer.valueOf(map.get("semester").toString());
            BigDecimal totalScore = map.get("yData")==null?null:new BigDecimal(map.get("yData").toString());
            xData.add(schoolYear+"学年"+semesterArray[semester-1]);
            yData.add(totalScore);
        }
        chartsDatas.put("xData",xData);
        chartsDatas.put("yData",yData);
        return chartsDatas;
    }

    @Override
    public boolean delete(Integer id) {
        return scoreMapper.deleteById(id)>0;
    }
}
