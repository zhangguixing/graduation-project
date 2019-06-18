package com.ssms.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ssms.common.exception.BusinessException;
import com.ssms.dao.*;
import com.ssms.model.CollegeSubjectClass;
import com.ssms.model.Course;
import com.ssms.model.CourseTimeTable;
import com.ssms.model.User;
import com.ssms.service.CourseTimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class CourseTimeTableServiceImpl implements CourseTimeTableService {

    @Autowired
    private CourseTimeTableMapper courseTimeTableMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private CollegeSubjectClassMapper collegeSubjectClassMapper;

    @Transactional
    @Override
    public boolean updateTimeTable(CourseTimeTable courseTimeTable) {
        return courseTimeTableMapper.updateById(courseTimeTable) > 0;
    }

    @Transactional
    @Override
    public boolean addTimeTable(CourseTimeTable courseTimeTable) {
        courseTimeTable.setCreateTime(new Date());
        return courseTimeTableMapper.insert(courseTimeTable) > 0;
    }

    @Override
    public CourseTimeTable getCourseInfo(Map<String, Object> map) {
        return courseTimeTableMapper.getCourseInfo(map);
    }

    @Transactional
    @Override
    public boolean deleteTimeTable(Integer id) {
        return courseTimeTableMapper.deleteById(id) > 0;
    }

    @Override
    public List<List<String>> getMyTimeTable(String schoolYear, Integer semester, Integer teacherId) {
        List<Map<String, Object>> mapList = courseTimeTableMapper.getMyTimeTable(schoolYear, semester, teacherId);

        List<List<String>> result = new ArrayList<>(7);
        //初始化
        for (int i = 0; i < 7; i++) {
            result.add(new ArrayList<>(Collections.nCopies(12, "")));
        }

        for (Map<String, Object> map : mapList) {
            String courseName = map.get("name") == null ? "" : map.get("name").toString();
            Integer dayOfWeek = map.get("dayOfWeek") == null ? null : Integer.valueOf(map.get("dayOfWeek").toString());
            Integer startLesson = map.get("startLesson") == null ? null : Integer.valueOf(map.get("startLesson").toString());
            Integer endLesson = map.get("endLesson") == null ? null : Integer.valueOf(map.get("endLesson").toString());
            Integer startWeekNum = map.get("startWeekNum") == null ? null : Integer.valueOf(map.get("startWeekNum").toString());
            Integer endWeekNum = map.get("endWeekNum") == null ? null : Integer.valueOf(map.get("endWeekNum").toString());
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
                    String courseInfo = timeTable.get(i - 1);
                    courseInfo += courseName + "@" + address + "(" + startWeekNum + "-" + endWeekNum + "周)\n";
                    timeTable.set(i - 1, courseInfo);
                }
            }
        }
        return result;
    }

    @Override
    public Boolean isConflict(CourseTimeTable courseTimeTable) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", courseTimeTable.getId());
        map.put("dayOfWeek", courseTimeTable.getDayOfWeek());
        map.put("startLesson", courseTimeTable.getStartLesson());
        map.put("endLesson", courseTimeTable.getEndLesson());
        map.put("startWeekNum", courseTimeTable.getStartWeekNum());
        map.put("endWeekNum", courseTimeTable.getEndWeekNum());
        Course course = courseMapper.selectById(courseTimeTable.getCourseId());
        if (course != null) {
            map.put("subjectId", course.getSubjectId());
            map.put("classId", course.getClassId());
            map.put("gradeId", course.getGradeId());
            map.put("schoolYear", course.getSchoolYear());
            map.put("semester", course.getSemester());
            long count = courseTimeTableMapper.isConflict(map);
            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addTimeTables(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
        inputStream.close();
        if (CollectionUtils.isEmpty(data)) {
            throw new BusinessException("上传课程表不可为空");
        }
        //存儲name和id键值对
        Map<String, Integer> idMap = new HashMap<>();
        //读入excel内容
        for (int i = 1; i < data.size(); i++) {
            List<String> courseTimeList = (List<String>) data.get(i);
            //课程名称	学院	专业	班级	年级	学年	学期	上课节数	上课周数	星期	教师姓名	上课地点
            String name = courseTimeList.get(0);
            String collegeName = courseTimeList.get(1);
            String subjectName = courseTimeList.get(2);
            String className = courseTimeList.get(3);
            String gradeName = courseTimeList.get(4);
            String schoolYear = courseTimeList.get(5);
            String semesterName = courseTimeList.get(6);
            String lessonStr = courseTimeList.get(7);
            String weekNumStr = courseTimeList.get(8);
            String dayOfWeekName = courseTimeList.get(9);
            String teacherName = courseTimeList.get(10);
            String address = courseTimeList.get(11);
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
            if (one == null) {
                throw new BusinessException("添加失败，课程不存在");
            }
            Integer courseId = one.getId();

            String[] lessonArr = lessonStr.split("-");
            Integer startLesson = Integer.valueOf(lessonArr[0]);
            Integer endLesson = Integer.valueOf(lessonArr[1]);

            String[] weekNumArr = weekNumStr.split("-");
            Integer startWeekNum = Integer.valueOf(weekNumArr[0]);
            Integer endWeekNum = Integer.valueOf(weekNumArr[1]);

            Integer dayOfWeekNum = null;
            if (dayOfWeekName.contains("一")) {
                dayOfWeekNum = 0;
            } else if (dayOfWeekName.contains("二")) {
                dayOfWeekNum = 1;
            } else if (dayOfWeekName.contains("三")) {
                dayOfWeekNum = 2;
            } else if (dayOfWeekName.contains("四")) {
                dayOfWeekNum = 3;
            } else if (dayOfWeekName.contains("五")) {
                dayOfWeekNum = 4;
            } else if (dayOfWeekName.contains("六")) {
                dayOfWeekNum = 5;
            } else if (dayOfWeekName.contains("日")) {
                dayOfWeekNum = 6;
            }
            //获取userId
            List<User> userList = userMapper.selectList(new EntityWrapper<User>()
                    .setSqlSelect("user_id")
                    .eq("nick_name", teacherName)
                    .eq("college_id", collegeId)
                    .eq("subject_id", subjectId)
                    .last("limit 1"));
            if (CollectionUtils.isEmpty(userList)) {
                throw new BusinessException("【" + teacherName + "】用户信息不存在");
            }
            Integer teacherId = userList.get(0).getUserId();
            //插入课程表
            CourseTimeTable courseTimeTable = new CourseTimeTable();
            courseTimeTable.setStartWeekNum(startWeekNum);
            courseTimeTable.setEndWeekNum(endWeekNum);
            courseTimeTable.setStartLesson(startLesson);
            courseTimeTable.setEndLesson(endLesson);
            courseTimeTable.setDayOfWeek(dayOfWeekNum);
            courseTimeTable.setCourseId(courseId);

            if (!isConflict(courseTimeTable)) {
                courseTimeTable.setTeacherId(teacherId);
                courseTimeTable.setTeacherName(teacherName);
                courseTimeTable.setAddress(address);
                courseTimeTable.setCreateTime(new Date());
                if (courseTimeTableMapper.insert(courseTimeTable) <= 0) {
                    throw new BusinessException("添加失败，请重试");
                }
            } else {
                throw new BusinessException("【" + name + "】课程安排时间冲突");
            }
        }
    }
}
