package com.ssms.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.ssms.common.exception.BusinessException;
import com.ssms.common.exception.ParameterException;
import com.ssms.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    User getByUsername(String username);

    User getById(Integer userId);

    PageInfo<User> list(int pageNum, int pageSize, boolean showDelete, Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, String column, String value, Integer loginUserId);

    boolean add(User user) throws BusinessException;

    boolean update(User user);

    boolean deleteUserRoleByUserId(Integer userId);

    boolean updateState(Integer userId, int state) throws ParameterException;

    boolean updatePsw(Integer userId, String username, String newPsw);

    boolean delete(Integer userId);

    Map<String, Object> existsName(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String username, String nickName);

    List<Map<String, Object>> listUserIdAndName(Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, Integer userType);

    void addUsers(MultipartFile file, Integer personType) throws IOException;
}
