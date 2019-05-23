package com.ssms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.common.util.StringUtil;
import com.ssms.dao.CourseMapper;
import com.ssms.dao.CourseTimeTableMapper;
import com.ssms.model.Course;
import com.ssms.model.CourseTimeTable;
import com.ssms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseTimeTableMapper courseTimeTableMapper;

    @Override
    public Map<String, Object> listByCollege(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String schoolYear, Integer semester) {
        List<Map<String, Object>> scoreList = courseMapper.listByCollege(gradeId, collegeId, subjectId, classId, schoolYear, semester);
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
}
