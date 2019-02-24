package com.sams.service;

import com.sams.dao.ClazzDao;
import com.sams.entity.Clazz;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ClazzService {
    @Autowired
    private ClazzDao clazzDao;

    public String getClazzList(Integer gradeId) {
        Example example = new Example(Clazz.class);
        example.createCriteria().andEqualTo("gradeid",gradeId);
        List<Clazz> clazzeList = clazzDao.selectByExample(example);
        //json化
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"grade", "studentList"});
        return JSONArray.fromObject(clazzeList, config).toString();
    }
}
