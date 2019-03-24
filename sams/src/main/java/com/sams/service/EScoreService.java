package com.sams.service;

import com.sams.dao.CourseDao;
import com.sams.dao.EScoreDao;
import com.sams.dao.StudentDao;
import com.sams.entity.Course;
import com.sams.entity.EScore;
import com.sams.entity.Exam;
import com.sams.entity.Student;
import com.sams.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class EScoreService {
    @Autowired
    private EScoreDao scoreDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private StudentDao studentDao;

    public List<Course> columnList(Exam exam) {
        List<Course> list = null;
        if(exam.getType() == Exam.EXAM_GRADE_TYPE){ //年级考试
            //获取考试的科目
            list = courseDao.columnList(exam.getGradeid());
        } else{
            //获取某科
            list = new ArrayList<>();
            list.add(courseDao.selectByPrimaryKey(exam.getCourseid()));
        }
        return list;
    }

    public List<Map<String, Object>> scoreList(Exam exam) {
        Example example = new Example(Student.class);
        example.createCriteria().andEqualTo("gradeid",exam.getGradeid());
        if(exam.getClazzid() != 0) {
            example.createCriteria().andEqualTo("clazzid", exam.getClazzid());
        }
        //获取该年级下的所有学生
        List<Student> stuList = studentDao.selectByExample(example);

        //数据集合
        List<Map<String, Object>> list = new LinkedList<>();

        for(int i = 0;i < stuList.size();i++){
            Map<String, Object> map = new LinkedHashMap<>();

            Student student = stuList.get(i);
            map.put("name", student.getName());
            map.put("number", student.getNumber());

            List<EScore> scoreList = scoreDao.findScoreList(exam.getId(), student.getId());
            int total = 0;
            for(EScore score : scoreList){
                total += score.getScore();

                //将成绩表id放入:便于获取单科成绩用于登记
                map.put("course"+score.getCourseid(), score.getScore());
                map.put("escoreid"+score.getCourseid(), score.getId());
            }
            if(exam.getType() == 1){
                map.put("total", total);
            }
            list.add(map);
        }
        return list;
    }

    public JsonResult setScorse(String[] score) {
        try{
            for(int i = 0;i < score.length;i++){
                String[] id_score = score[i].split("_");
                int id = Integer.parseInt(id_score[0]);
                int sco = Integer.parseInt(id_score[1]);
                EScore eScore = new EScore();
                eScore.setId(id);
                eScore.setScore(sco);
                scoreDao.updateByPrimaryKeySelective(eScore);
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("更行失败");
        }
        return JsonResult.ok();
    }

    public void insert(EScore score) {
        scoreDao.insert(score);
    }
}
