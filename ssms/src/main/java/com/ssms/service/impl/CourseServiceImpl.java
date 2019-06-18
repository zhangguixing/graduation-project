package com.ssms.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.common.exception.BusinessException;
import com.ssms.common.util.StringUtil;
import com.ssms.dao.CollegeSubjectClassMapper;
import com.ssms.dao.CourseMapper;
import com.ssms.dao.CourseTimeTableMapper;
import com.ssms.dao.GradeMapper;
import com.ssms.model.CollegeSubjectClass;
import com.ssms.model.Course;
import com.ssms.model.CourseTimeTable;
import com.ssms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseTimeTableMapper courseTimeTableMapper;
    @Autowired
    private CollegeSubjectClassMapper collegeSubjectClassMapper;
    @Autowired
    private GradeMapper gradeMapper;


    @Override
    public Map<String, Object> listByCollege(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, Integer studentId) {
        List<Map<String, Object>> scoreList = courseMapper.listByCollege(gradeId, collegeId, subjectId, classId, schoolYear, semester, studentId);
        Map<String, Object> result = new HashMap<>();
        result.put("scoreList", scoreList);
        return result;
    }

    @Override
    public List<List<String>> listTimeTable(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, Integer weekNum) {
        List<Map<String, Object>> mapList = courseTimeTableMapper.listTimeTable(gradeId, collegeId, subjectId, classId, schoolYear, semester, weekNum);

        List<List<String>> result = new ArrayList<>(7);
        //初始化
        for (int i = 0; i < 7; i++) {
            result.add(new ArrayList<>(Collections.nCopies(12, "")));
        }

        for (Map<String, Object> map : mapList) {
            String teacherName = map.get("teacherName") == null ? "" : map.get("teacherName").toString();
            String courseName = map.get("name") == null ? "" : map.get("name").toString();
            Integer dayOfWeek = map.get("dayOfWeek") == null ? null : Integer.valueOf(map.get("dayOfWeek").toString());
            Integer startLesson = map.get("startLesson") == null ? null : Integer.valueOf(map.get("startLesson").toString());
            Integer endLesson = map.get("endLesson") == null ? null : Integer.valueOf(map.get("endLesson").toString());
            String address = map.get("address") == null ? "" : map.get("address").toString();
            if (dayOfWeek == null) {
                break;
            }
            if (startLesson >= 5 && startLesson <= 8) {
                startLesson += 1;
                endLesson += 1;
            } else if (startLesson >= 9) {
                startLesson += 2;
                if (endLesson + 2 > 12) {
                    endLesson = 12;
                } else {
                    endLesson += 2;
                }
            }
            List<String> timeTable = result.get(dayOfWeek);
            if (startLesson != null && endLesson != null) {
                for (int i = startLesson; i <= endLesson && i - 1 < timeTable.size(); i++) {
                    timeTable.set(i - 1, courseName + "@" + address + "\n" + teacherName);
                }
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> listCourseIdAndName(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester) {
        return courseMapper.listCourseIdAndName(gradeId, collegeId, subjectId, classId, schoolYear, semester);
    }

    @Override
    public PageInfo<Map<String, Object>> all(Integer pageNum, Integer pageSize, Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester, String searchKey, String searchValue) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> courseList = null;
        if (StringUtil.isBlank(searchKey)) {
            searchKey = null;
        }
        courseList = courseMapper.all(gradeId, collegeId, subjectId, classId, schoolYear, semester, searchKey, searchValue);
        //表中无符合条件数据
        if (CollectionUtils.isEmpty(courseList) || CollectionUtils.isEmpty(courseList.get(0))) {
            courseList = new ArrayList<>();
        }
        return PageInfo.of(courseList);
    }

    @Transactional
    @Override
    public boolean add(Course course) {
        course.setCreateTime(new Date());
        return courseMapper.insert(course) > 0;
    }

    @Transactional
    @Override
    public boolean update(Course course) {
        return courseMapper.updateById(course) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            //级联删除课程表
            courseTimeTableMapper.delete(new EntityWrapper<CourseTimeTable>().eq("course_id", id));
            courseMapper.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addCourses(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        if (CollectionUtils.isEmpty(data)) {
            throw new BusinessException("上传课程不可为空");
        }
        //存儲name和id键值对
        Map<String, Integer> idMap = new HashMap<>();
        //读入excel内容
        for (int i = 1; i < data.size(); i++) {
            List<String> courseList = (List<String>) data.get(i);
            //课程名称	学院	专业 	班级	年级	学年	学期
            String name = courseList.get(0);
            String collegeName = courseList.get(1);
            String subjectName = courseList.get(2);
            String className = courseList.get(3);
            String gradeName = courseList.get(4);
            String schoolYear = courseList.get(5);
            String semesterName = courseList.get(6);
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
            Integer semester = null;
            if ("上学期".equals(semesterName)) {
                semester = 1;
            } else if ("下学期".equals(semesterName)) {
                semester = 2;
            } else {
                throw new BusinessException("学期不合法");
            }
            Course course = new Course();
            course.setName(name);
            course.setCollegeId(collegeId);
            course.setSubjectId(subjectId);
            course.setClassId(classId);
            course.setGradeId(gradeId);
            course.setSchoolYear(schoolYear);
            course.setSemester(semester);
            Course one = courseMapper.selectOne(course);
            if (one != null) {
                throw new BusinessException("添加失败，课程已存在");
            }
            course.setCreateTime(new Date());
            //插入年级
            if (courseMapper.insert(course) <= 0) {
                throw new BusinessException("添加失败，请重试");
            }
        }
    }
}
