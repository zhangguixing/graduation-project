package com.ssms.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.common.exception.BusinessException;
import com.ssms.common.util.StringUtil;
import com.ssms.dao.*;
import com.ssms.model.CollegeSubjectClass;
import com.ssms.model.Course;
import com.ssms.model.Score;
import com.ssms.model.User;
import com.ssms.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;

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
    public Map<String, Object> getCollegeInfo(Integer studentId, Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, String schoolYear, Integer semester) {
        User user = userMapper.selectById(studentId);
        if (user == null) {
            throw new BusinessException("无此用户信息studentId=" + studentId);
        }
        Map<String, Object> collegeInfo = new HashMap<>();
        collegeInfo.put("studentId", studentId);
        collegeInfo.put("collegeId", collegeId);
        collegeInfo.put("subjectId", subjectId);
        collegeInfo.put("classId", classId);
        collegeInfo.put("gradeId", gradeId);
        collegeInfo.put("schoolYear", schoolYear);
        collegeInfo.put("semester", semester);
        collegeInfo.put("nickName", user.getNickName());
        collegeInfo.put("username", user.getUsername());
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
            headList.add("成绩");
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

    @Transactional
    @Override
    public void addScores(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        if (CollectionUtils.isEmpty(data)) {
            throw new BusinessException("上传成绩不可为空");
        }
        List<String> headList = (List<String>) data.get(0);
        if (headList.size() < 9) {
            throw new BusinessException("导入的成绩至少包含一科成绩");
        }
        //获取导入成绩的courseId
        List<Integer> courseIdList = new ArrayList<>();
        for (int i = 8; i < headList.size(); i++) {
            String courseName = headList.get(i);
            Course courseCondition = new Course();
            courseCondition.setName(courseName);
            Course course = courseMapper.selectOne(courseCondition);
            if (course == null) {
                throw new BusinessException("【" + courseName + "】课程不存在,无法导入成绩");
            }
            courseIdList.add(course.getId());
        }
        //存儲name和id键值对
        Map<String, Integer> idMap = new HashMap<>();
        //读入excel内容
        for (int i = 1; i < data.size(); i++) {
            List<String> scoreList = (List<String>) data.get(i);
            //姓名, 学号, 学院, 专业, 班级, 年级, 学年, 学期
            String nickName = scoreList.get(0);
            String username = scoreList.get(1);
            String collegeName = scoreList.get(2);
            String subjectName = scoreList.get(3);
            String className = scoreList.get(4);
            String gradeName = scoreList.get(5);
            String schoolYear = scoreList.get(6);
            String semesterName = scoreList.get(7);
            //获取学院信息id和学生id
            Integer collegeId = idMap.get(collegeName);
            if (collegeId == null) {
                collegeId = collegeSubjectClassMapper.selectIdByName(collegeName);
                if (collegeId == null) {
                    throw new BusinessException("【" + collegeName + "】学院不存在！");
                }
                idMap.put(collegeName, collegeId);
            }
            Integer subjectId = idMap.get(subjectName);
            if (subjectId == null) {
                subjectId = collegeSubjectClassMapper.selectIdByName(subjectName);
                if (subjectId == null) {
                    throw new BusinessException("【" + subjectName + "】专业不存在！");
                }
                idMap.put(subjectName, subjectId);
            }
            Integer classId = idMap.get(subjectName + className);
            if (classId == null) {
                CollegeSubjectClass collegeSubjectClass = new CollegeSubjectClass();
                collegeSubjectClass.setName(className);
                collegeSubjectClass.setParentId(subjectId);
                collegeSubjectClass = collegeSubjectClassMapper.selectOne(collegeSubjectClass);
                if (collegeSubjectClass == null) {
                    throw new BusinessException("【" + className + "】班级不存在！");
                }
                classId = collegeSubjectClass.getId();
                idMap.put(subjectName + className, classId);
            }
            Integer gradeId = idMap.get(gradeName);
            if (gradeId == null) {
                gradeId = gradeMapper.selectIdByName(gradeName);
                if (gradeId == null) {
                    throw new BusinessException("【" + gradeName + "】年级不存在！");
                }
                idMap.put(gradeName, gradeId);
            }
            //获取userId
            List<User> userList = userMapper.selectList(new EntityWrapper<User>()
                    .setSqlSelect("user_id")
                    .eq("nick_name", nickName)
                    .eq("username", username)
                    .eq("college_id", collegeId)
                    .eq("subject_id", subjectId)
                    .eq("class_id", classId)
                    .eq("grade_id", gradeId)
                    .last("limit 1"));
            if (CollectionUtils.isEmpty(userList)) {
                throw new BusinessException("【" + nickName + ":" + username + "】用户信息不合法");
            }
            Integer studentId = userList.get(0).getUserId();
            Integer semester = null;
            if ("上学期".equals(semesterName)) {
                semester = 1;
            } else if ("下学期".equals(semesterName)) {
                semester = 2;
            } else {
                throw new BusinessException("学期不合法");
            }
            //插入成绩
            for (int j = 8; j < scoreList.size(); j++) {
                BigDecimal scoreResult = new BigDecimal(scoreList.get(j));
                Score score = new Score();
                score.setScore(scoreResult);
                score.setCourseId(courseIdList.get(j - 8));
                score.setStudentId(studentId);
                score.setCollegeId(collegeId);
                score.setSubjectId(subjectId);
                score.setClassId(classId);
                score.setGradeId(gradeId);
                score.setSchoolYear(schoolYear);
                score.setSemester(semester);
                score.setCreateTime(new Date());
                if (scoreMapper.insert(score) <= 0) {
                    throw new BusinessException("添加失败，请重试");
                }
            }
        }
    }

    @Override
    public Map<String, Object> getCollegeNameAndScore(Integer studentId, Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, String schoolYear, Integer semester) {
        Map<String, Object> collegeNameAndScore = new HashMap<>();

        Map<String, Object> collegeInfo = new HashMap<>();
        collegeInfo.put("studentId", studentId);
        collegeInfo.put("collegeId", collegeId);
        collegeInfo.put("subjectId", subjectId);
        collegeInfo.put("classId", classId);
        collegeInfo.put("gradeId", gradeId);
        collegeInfo.put("schoolYear", schoolYear);
        collegeInfo.put("semester", semester);
        List<Map<String, Object>> scoreList = scoreMapper.getStudentScore(collegeInfo);

        String collegeName = collegeSubjectClassMapper.selectNameById(collegeId);
        String subjectName = collegeSubjectClassMapper.selectNameById(subjectId);
        String className = collegeSubjectClassMapper.selectNameById(classId);
        String gradeName = gradeMapper.selectNameById(gradeId);
        User user = userMapper.selectById(studentId);
        String username = user.getUsername();
        String nickName = user.getNickName();

        collegeNameAndScore.put("collegeName", collegeName);
        collegeNameAndScore.put("subjectName", subjectName);
        collegeNameAndScore.put("className", className);
        collegeNameAndScore.put("gradeName", gradeName);
        collegeNameAndScore.put("username", username);
        collegeNameAndScore.put("nickName", nickName);
        collegeNameAndScore.put("scoreList", scoreList);
        return collegeNameAndScore;
    }
}
