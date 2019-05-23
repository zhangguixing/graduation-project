package com.ssms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.common.util.StringUtil;
import com.ssms.dao.CollegeSubjectClassMapper;
import com.ssms.dao.GradeMapper;
import com.ssms.dao.ScoreMapper;
import com.ssms.model.Score;
import com.ssms.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private CollegeSubjectClassMapper collegeSubjectClassMapper;
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public PageInfo<Map<String, Object>> listScore(Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String searchKey, String searchValue) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> listScore = null;
        if (StringUtil.isBlank(searchKey)) {
            searchKey = null;
        }
        listScore = scoreMapper.listScore(gradeId, collegeId, subjectId, classId, schoolYear, semester, searchKey, searchValue);
        //表中无符合条件数据
        if (CollectionUtils.isEmpty(listScore) || CollectionUtils.isEmpty(listScore.get(0))) {
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
    public boolean delete(Map<String, Object> map) {
        try {
            //0、获取所需数据
            Integer subjectId = map.get("subjectId") == null ? null : Integer.valueOf(map.get("subjectId").toString());
            Integer collegeId = map.get("collegeId") == null ? null : Integer.valueOf(map.get("collegeId").toString());
            Integer studentId = map.get("studentId") == null ? null : Integer.valueOf(map.get("studentId").toString());
            Integer classId = map.get("classId") == null ? null : Integer.valueOf(map.get("classId").toString());
            Integer gradeId = map.get("gradeId") == null ? null : Integer.valueOf(map.get("gradeId").toString());
            Integer semester = map.get("semester") == null ? null : Integer.valueOf(map.get("semester").toString());
            String schoolYear = map.get("schoolYear") == null ? null : map.get("schoolYear").toString();
            //1、删除成绩
            scoreMapper.deleteByCollegeInfo(gradeId, collegeId, subjectId, classId, schoolYear, semester, studentId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<String, Object> getCollegeInfoById(Integer id) {
        Map<String, Object> collegeInfo = scoreMapper.getCollegeInfoById(id);
        List<Map<String, Object>> scoreList = scoreMapper.getStudentScore(collegeInfo);
        Map<String, Object> result = new HashMap<>();
        if (collegeInfo != null) {
            result.putAll(collegeInfo);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("scoreList", scoreList);
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
            Map<String, String> scoreMap = (Map<String, String>) map.get("scoreList");
            //1、删除成绩
            scoreMapper.deleteByCollegeInfo(gradeId, collegeId, subjectId, classId, schoolYear, semester, studentId);
            //2、添加成绩
            Set<Map.Entry<String, String>> entries = scoreMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
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
                scoreMapper.insert(sc);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<String, Object> export(List<Map<String, Object>> list) {
        Map<String, Object> result = new HashMap<>();
        if (!CollectionUtils.isEmpty(list) && list.size() > 0) {
            Map<String, Object> map = list.get(0);
            Integer gradeId = map.get("gradeId") == null ? null : Integer.valueOf(map.get("gradeId").toString());
            Integer collegeId = map.get("collegeId") == null ? null : Integer.valueOf(map.get("collegeId").toString());
            Integer subjectId = map.get("subjectId") == null ? null : Integer.valueOf(map.get("subjectId").toString());
            Integer classId = map.get("classId") == null ? null : Integer.valueOf(map.get("classId").toString());
            Integer semester = map.get("semester") == null ? null : Integer.valueOf(map.get("semester").toString());
            String schoolYear = map.get("schoolYear") == null ? null : map.get("schoolYear").toString();

            List<String> headList = new ArrayList<>();
            headList.add("学号");
            headList.add("姓名");
            headList.add("年级");
            headList.add("学院");
            headList.add("专业");
            headList.add("班级");
            if (!StringUtil.isBlank(schoolYear)) {
                headList.add("学年");
            }
            if (!StringUtil.isBlank(semester + "")) {
                headList.add("学期");
            }
            List<String> courseList = scoreMapper.listCourseInfo(gradeId, collegeId, subjectId, classId, schoolYear, semester);
            headList.addAll(courseList);
            headList.add("总成绩");
            List<List<Object>> bodyList = new ArrayList<>();

            List<Object> row = new ArrayList<>();
            String username = map.get("username") == null ? null : map.get("username").toString();
            String nickName = map.get("nickName") == null ? null : map.get("nickName").toString();
            String gradeName = gradeMapper.selectNameById(gradeId);
            String collegeName = collegeSubjectClassMapper.selectNameById(collegeId);
            String subjectName = collegeSubjectClassMapper.selectNameById(subjectId);
            String className = collegeSubjectClassMapper.selectNameById(classId);


            row.add(username);
            row.add(nickName);
            row.add(gradeName);
            row.add(collegeName);
            row.add(subjectName);
            row.add(className);
            if (!StringUtil.isBlank(schoolYear)) {
                row.add(schoolYear);
            }
            if (!StringUtil.isBlank(semester + "")) {
                String[] semesterNames = {"上学期", "下学期"};
                String semesterName = semesterNames[semester - 1];
                row.add(semesterName);
            }
            for (Map<String, Object> studentMap : list) {
                Integer studentId = studentMap.get("studentId") == null ? null : Integer.valueOf(studentMap.get("studentId").toString());
                //获取课程、成绩
                List<BigDecimal> scoreList = scoreMapper.listScoreInfo(gradeId, collegeId, subjectId, classId, schoolYear, semester, studentId);
                row.addAll(scoreList);
                BigDecimal totalScore = new BigDecimal("0");
                for (BigDecimal score : scoreList) {
                    totalScore = totalScore.add(score);
                }
                row.add(totalScore);
                bodyList.add(row);
            }
            result.put("headList", headList);
            result.put("bodyList", bodyList);
        }
        System.out.println(JSON.toJSONString(result));
        return result;
    }
}
