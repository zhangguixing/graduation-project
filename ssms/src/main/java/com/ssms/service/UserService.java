package com.ssms.service;

import com.baomidou.mybatisplus.service.IService;
import com.ssms.exception.BusinessException;
import com.ssms.exception.ParameterException;
import com.ssms.model.User;
import com.ssms.util.PageResult;

public interface UserService extends IService<User> {

    User getByUsername(String username);

    PageResult<User> list(int pageNum, int pageSize, boolean showDelete, String searchKey, String searchValue);

    User getById(Integer userId);

    boolean add(User user) throws BusinessException;

    boolean update(User user);

    boolean deleteUserRoleByUserId(Integer userId);

    boolean updateState(Integer userId, int state) throws ParameterException;

    boolean updatePsw(Integer userId, String username, String newPsw);

    boolean delete(Integer userId);

}
