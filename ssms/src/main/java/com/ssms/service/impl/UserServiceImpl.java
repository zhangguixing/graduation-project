package com.ssms.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssms.common.conifg.shiro.EndecryptUtil;
import com.ssms.common.exception.BusinessException;
import com.ssms.common.exception.ParameterException;
import com.ssms.common.util.StringUtil;
import com.ssms.dao.*;
import com.ssms.model.*;
import com.ssms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private CollegeSubjectClassMapper collegeSubjectClassMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private CourseTimeTableMapper courseTimeTableMapper;

    @Override
    public User getByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    public PageInfo<User> list(int pageNum, int pageSize, boolean showDelete, Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, String column, String value) {
        Wrapper<User> wrapper = new EntityWrapper<>();
        if(collegeId != null){
            wrapper.eq("college_id", collegeId);
        }
        if(subjectId != null){
            wrapper.eq("subject_id", subjectId);
        }
        if(classId != null){
            wrapper.eq("class_id", classId);
        }
        if(gradeId != null){
            wrapper.eq("grade_id", gradeId);
        }
        if (StringUtil.isNotBlank(column)) {
            wrapper.like(column, value);
        }
        if (!showDelete) {  // 不显示锁定的用户
            wrapper.eq("state", 0);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = baseMapper.selectList(wrapper.orderBy("person_type", true).orderBy("create_time", true));
        if (userList != null && userList.size() > 0) {
            // 查询user的角色
            List<UserRole> userRoles = userRoleMapper.selectByUserIds(getUserIds(userList));
            for (User one : userList) {
                List<Role> tempURs = new ArrayList<>();
                for (UserRole ur : userRoles) {
                    if (one.getUserId().equals(ur.getUserId())) {
                        tempURs.add(new Role(ur.getRoleId(), ur.getRoleName()));
                    }
                }
                one.setRoles(tempURs);
            }
        }
        return PageInfo.of(userList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(User user) throws BusinessException {
        if (baseMapper.selectByUsername(user.getUsername()) != null) {
            throw new BusinessException("账号已经存在");
        }
        List<Role> roles = user.getRoles();
        if (CollectionUtils.isEmpty(roles)) {
            throw new BusinessException("未设置权限");
        }
        Integer roleId = roles.get(0).getRoleId();
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            throw new BusinessException("不合法权限");
        }
        String roleName = role.getRoleName();
        if ("学生".equals(roleName)) {
            user.setPersonType(User.STUDENT_TYPE);
        } else if ("教师".equals(roleName)) {
            user.setPersonType(User.TEACHER_TYPE);
        } else if ("管理员".equals(roleName)) {
            user.setPersonType(User.ADMIN_TYPE);
        } else {
            throw new BusinessException("添加用户的角色仅限【学生、教师、管理员】");
        }
        user.setPassword(EndecryptUtil.encrytMd5(user.getPassword(), user.getUsername(), 3));
        user.setState(0);
        user.setCreateTime(new Date());
        boolean rs = baseMapper.insert(user) > 0;
        if (rs) {
            List<Integer> roleIds = getRoleIds(user.getRoles());
            if (userRoleMapper.insertBatch(user.getUserId(), roleIds) < roleIds.size()) {
                throw new BusinessException("添加失败，请重试");
            }
        }
        return rs;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUserRoleByUserId(Integer userId) throws BusinessException {
        if (userRoleMapper.deleteUserRoleByUserId(userId) < 1) {
            throw new BusinessException("删除失败，请重试");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(User user) {
        user.setUsername(null);
        List<Integer> roleIds = getRoleIds(user.getRoles());
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new BusinessException("未设置权限");
        }
        Integer roleId = roleIds.get(0);
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            throw new BusinessException("不合法权限");
        }
        String roleName = role.getRoleName();
        if ("学生".equals(roleName)) {
            user.setPersonType(User.STUDENT_TYPE);
        } else if ("教师".equals(roleName)) {
            user.setPersonType(User.TEACHER_TYPE);
        } else if ("管理员".equals(roleName)) {
            user.setPersonType(User.ADMIN_TYPE);
        } else {
            throw new BusinessException("修改用户的角色仅限【学生、教师、管理员】");
        }
        boolean rs = baseMapper.updateById(user) > 0;
        if (rs) {
            userRoleMapper.delete(new EntityWrapper().eq("user_id", user.getUserId()));
            if (userRoleMapper.insertBatch(user.getUserId(), roleIds) < roleIds.size()) {
                throw new BusinessException("修改失败，请重试");
            }
        }
        return rs;
    }

    /**
     * 添加用户角色
     */
    private List<Integer> getRoleIds(List<Role> roles) {
        List<Integer> rs = new ArrayList<>();
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                rs.add(role.getRoleId());
            }
        }
        return rs;
    }

    @Override
    public boolean updateState(Integer userId, int state) throws ParameterException {
        if (state != 0 && state != 1) {
            throw new ParameterException("state值需要在[0,1]中");
        }
        User user = new User();
        user.setUserId(userId);
        user.setState(state);
        return baseMapper.updateById(user) > 0;
    }

    @Override
    public boolean updatePsw(Integer userId, String username, String password) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(EndecryptUtil.encrytMd5(password, username, 3));
        return baseMapper.updateById(user) > 0;
    }

    @Override
    public User getById(Integer userId) {
        return baseMapper.selectById(userId);
    }

    @Override
    public boolean delete(Integer userId) {
        try {
            User user = baseMapper.selectById(userId);
            if(user == null){
                return false;
            }
            if(user.getPersonType().equals(3)){
                //学生
                scoreMapper.delete(new EntityWrapper<Score>().eq("student_id",userId));
            }else if(user.getPersonType().equals(2)){
                //教师
                courseTimeTableMapper.delete(new EntityWrapper<CourseTimeTable>().eq("teacher_id",userId));
            }
            baseMapper.deleteById(userId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public Map<String, Object> existsName(Integer gradeId, Integer collegeId, Integer subjectId, Integer classId, String username, String nickName) {
        User user = new User();
        user.setGradeId(gradeId);
        user.setCollegeId(collegeId);
        user.setSubjectId(subjectId);
        user.setClassId(classId);
        user.setUsername(username);
        user.setNickName(nickName);
        Map<String, Object> result = new HashMap<>();
        User one = baseMapper.selectOne(user);
        if (one == null) {
            result.put("isExists", false);
        } else {
            result.put("isExists", true);
            result.put("userId", one.getUserId());
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> listUserIdAndName(Integer collegeId, Integer subjectId, Integer classId, Integer gradeId, Integer userType) {
        return baseMapper.listUserIdAndName(collegeId, subjectId, classId, gradeId, userType);
    }

    @Transactional
    @Override
    public void addUsers(MultipartFile file, Integer personType) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, UserModel.class));
        inputStream.close();
        //存储id和name键值对，减轻数据库查询次数
        Map<String,Integer> idMap = new HashMap<>();
        for (Object o:data){
            if(o instanceof UserModel){
                UserModel userModel = (UserModel)o;
                if (baseMapper.selectByUsername(userModel.getUsername()) != null) {
                    throw new BusinessException("账号【"+userModel.getUsername()+"】已经存在");
                }
                Role role = new Role();
                if (personType.equals(3)) {
                    role.setRoleName("学生");
                } else if (personType.equals(2)) {
                    role.setRoleName("教师");
                } else {
                    throw new BusinessException("批量导入用户的角色仅限【学生、教师】");
                }
                role = roleMapper.selectOne(role);
                if (role == null) {
                    throw new BusinessException("不合法权限");
                }
                User user = new User();
                user.setPersonType(personType);
                user.setUsername(userModel.getUsername());
                user.setNickName(userModel.getNickName());
                user.setSex(userModel.getSex());
                user.setPhone(userModel.getPhone());
                String collegeName = userModel.getCollegeName();
                Integer collegeId = idMap.get(collegeName);
                if(collegeId == null){
                    collegeId = collegeSubjectClassMapper.selectIdByName(collegeName);
                    if(collegeId == null){
                        throw new BusinessException("【"+collegeName+"】学院不存在！");
                    }
                    idMap.put(collegeName,collegeId);
                }
                String subjectName = userModel.getSubjectName();
                Integer subjectId = idMap.get(subjectName);
                if(subjectId == null){
                    subjectId = collegeSubjectClassMapper.selectIdByName(subjectName);
                    if(subjectId == null){
                        throw new BusinessException("【"+subjectName+"】专业不存在！");
                    }
                    idMap.put(subjectName,subjectId);
                }
                user.setCollegeId(collegeId);
                user.setSubjectId(subjectId);
                if(personType.equals(3)){
                    //学生
                    String className = userModel.getClassName();
                    Integer classId = idMap.get(subjectName+className);
                    if(classId == null){
                        CollegeSubjectClass collegeSubjectClass = new CollegeSubjectClass();
                        collegeSubjectClass.setName(className);
                        collegeSubjectClass.setParentId(subjectId);
                        collegeSubjectClass = collegeSubjectClassMapper.selectOne(collegeSubjectClass);
                        if(collegeSubjectClass == null){
                            throw new BusinessException("【"+className+"】班级不存在！");
                        }
                        classId = collegeSubjectClass.getId();
                        idMap.put(subjectName+className,classId);
                    }
                    String gradeName = userModel.getGradeName();
                    Integer gradeId = idMap.get(gradeName);
                    if(gradeId == null){
                        gradeId = gradeMapper.selectIdByName(gradeName);
                        if(gradeId == null){
                            throw new BusinessException("【"+gradeName+"】年级不存在！");
                        }
                        idMap.put(gradeName,gradeId);
                    }
                    user.setClassId(classId);
                    user.setGradeId(gradeId);
                }
                user.setPassword(EndecryptUtil.encrytMd5("123456", user.getUsername(), 3));
                user.setState(0);
                user.setCreateTime(new Date());

                boolean rs = baseMapper.insert(user) > 0;
                if(rs){
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(role.getRoleId());
                    userRole.setUserId(user.getUserId());
                    userRole.setCreateTime(new Date());
                    if(userRoleMapper.insert(userRole)<=0){
                        throw new BusinessException("添加失败，请重试");
                    }
                }
            }
        }
    }

    private List<Integer> getUserIds(List<User> userList) {
        List<Integer> userIds = new ArrayList<>();
        for (User one : userList) {
            userIds.add(one.getUserId());
        }
        return userIds;
    }
}
