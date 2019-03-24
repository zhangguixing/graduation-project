package com.sams.dao;

import com.sams.entity.EScore;
import com.sams.entity.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author Guixing
 * @Date 2019/2/21 13:17
 * @Description
 */
public interface EScoreDao extends Mapper<EScore> {

    @Select("SELECT e.id,e.courseid,e.score FROM student s, escore e WHERE s.id=e.studentid AND e.examid=#{eid} AND e.studentid=#{sid}")
    List<EScore> findScoreList(@Param("eid") int eid,@Param("sid") int sid);
}
