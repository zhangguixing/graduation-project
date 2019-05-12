package com.ssms.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.common.util.JSONUtil;
import com.ssms.dao.ScoreMapper;
import com.ssms.model.Score;
import com.ssms.service.ScoreService;
import com.ssms.common.util.StringUtil;
import com.sun.xml.internal.bind.v2.util.QNameMap;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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
        String[] semesterArray = {"上学期", "下学期"};
        for (Map<String, Object> map : maps) {
            String schoolYear = map.get("schoolYear") == null ? "" : map.get("schoolYear").toString();
            Integer semester = map.get("semester") == null ? 1 : Integer.valueOf(map.get("semester").toString());
            BigDecimal totalScore = map.get("yData") == null ? null : new BigDecimal(map.get("yData").toString());
            xData.add(schoolYear + "学年" + semesterArray[semester - 1]);
            yData.add(totalScore);
        }
        chartsDatas.put("xData", xData);
        chartsDatas.put("yData", yData);
        return chartsDatas;
    }

    @Transactional
    @Override
    public boolean delete(Integer id) {
        return scoreMapper.deleteById(id) > 0;
    }

    @Override
    public Map<String, Object> getCollegeInfoById(Integer id) {
        Map<String, Object> collegeInfo = scoreMapper.getCollegeInfoById(id);
        List<Map<String, Object>> scoreList = scoreMapper.getStudentScore(collegeInfo);
        Map<String, Object> result = new HashMap<>();
        if(collegeInfo!=null){
            result.putAll(collegeInfo);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("scoreList",scoreList);
        result.put("scoreList", JSON.toJSONString(map));
        return result;
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(Map<String, Object> map) {
        try {
            //0、获取所需数据
            Integer gradeId = map.get("gradeId") == null ? null : Integer.valueOf(map.get("gradeId").toString());
            Integer collegeId = map.get("collegeId") == null ? null : Integer.valueOf(map.get("collegeId").toString());
            Integer subjectId = map.get("subjectId") == null ? null : Integer.valueOf(map.get("subjectId").toString());
            Integer classId = map.get("classId") == null ? null : Integer.valueOf(map.get("classId").toString());
            Integer studentId = map.get("studentId") == null ? null : Integer.valueOf(map.get("studentId").toString());
            String schoolYear = map.get("schoolYear") == null ? null : map.get("schoolYear").toString();
            Integer semester = map.get("semester") == null ? null : Integer.valueOf(map.get("semester").toString());
            Map<String,String> scoreMap = (Map<String, String>) map.get("scoreList");
            //1、删除成绩
            scoreMapper.deleteByCollegeInfo(gradeId,collegeId,subjectId,classId,schoolYear,semester,studentId);
            //2、添加成绩
            Set<Map.Entry<String, String>> entries = scoreMap.entrySet();
            for (Map.Entry<String,String> entry:entries) {
                Integer courseId = Integer.valueOf(entry.getKey());
                BigDecimal score = new BigDecimal(entry.getValue());
                Score sc = new Score();
                sc.setGradeId(gradeId);
                sc.setCollegeId(collegeId);
                sc.setSubjectId(subjectId);
                sc.setClassId(classId);
                sc.setSchoolYear(schoolYear);
                sc.setSemester(semester);
                sc.setStudentId(studentId);
                sc.setCourseId(courseId);
                sc.setScore(score);
                sc.setCreateTime(new Date());
                System.out.println(JSON.toJSONString(sc));
                scoreMapper.insert(sc);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
