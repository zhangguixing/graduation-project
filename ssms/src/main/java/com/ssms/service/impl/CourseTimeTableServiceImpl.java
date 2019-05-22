package com.ssms.service.impl;

import com.ssms.dao.CourseTimeTableMapper;
import com.ssms.model.CourseTimeTable;
import com.ssms.service.CourseTimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CourseTimeTableServiceImpl implements CourseTimeTableService {

    @Autowired
    private CourseTimeTableMapper courseTimeTableMapper;

    @Transactional
    @Override
    public boolean updateTimeTable(CourseTimeTable courseTimeTable) {
        return courseTimeTableMapper.updateById(courseTimeTable) > 0;
    }

    @Transactional
    @Override
    public boolean addTimeTable(CourseTimeTable courseTimeTable) {
        courseTimeTable.setCreateTime(new Date());
        return courseTimeTableMapper.insert(courseTimeTable)>0;
    }

    @Override
    public CourseTimeTable getCourseInfo(Map<String, Object> map) {
        return courseTimeTableMapper.getCourseInfo(map);
    }

    @Transactional
    @Override
    public boolean deleteTimeTable(Integer id) {
        return courseTimeTableMapper.deleteById(id)>0;
    }

    @Override
    public List<List<String>> getMyTimeTable(String schoolYear, Integer semester, Integer teacherId) {
        List<Map<String,Object>> mapList = courseTimeTableMapper.getMyTimeTable(schoolYear,semester,teacherId);

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
            if (startLesson >= 5 && startLesson<=8) {
                startLesson += 1;
                endLesson += 1;
            } else if (startLesson >=9) {
                startLesson += 2;
                if(endLesson+2>12){
                    endLesson = 12;
                }else {
                    endLesson += 2;
                }
            }
            List<String> timeTable = result.get(dayOfWeek);
            if (startLesson != null && endLesson != null) {
                for (int i = startLesson; i <= endLesson && i-1<timeTable.size(); i++) {
                    String courseInfo = timeTable.get(i - 1);
                    courseInfo += courseName + "@" + address + "("+startWeekNum+"-"+endWeekNum+"周)\n";
                    timeTable.set(i - 1, courseInfo);
                }
            }
        }
        return result;
    }
}
