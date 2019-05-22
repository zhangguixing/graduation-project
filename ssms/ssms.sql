/*
Navicat MySQL Data Transfer

Source Server         : xing
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : ssms

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-05-22 16:40:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for info_college_subject_class
-- ----------------------------
DROP TABLE IF EXISTS `info_college_subject_class`;
CREATE TABLE `info_college_subject_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学院/专业/班级id',
  `name` varchar(20) NOT NULL COMMENT '学院/专业/班级名称',
  `order_number` int(3) NOT NULL DEFAULT '0' COMMENT '排序号',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父id，值为0表示学院，否则表示父id',
  `type_number` int(1) DEFAULT '0' COMMENT '类型，0学院，1专业，2班级',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_college_subject_class
-- ----------------------------
INSERT INTO `info_college_subject_class` VALUES ('1', '信息科学与工程学院', '1', '0', '0', '2019-05-04 16:10:27', '2019-05-04 16:13:41');
INSERT INTO `info_college_subject_class` VALUES ('2', '计算机科学与技术', '2', '1', '1', '2019-05-04 16:24:37', '2019-05-04 16:26:46');
INSERT INTO `info_college_subject_class` VALUES ('3', '1班', '3', '2', '2', '2019-05-04 16:41:33', '2019-05-04 16:41:33');
INSERT INTO `info_college_subject_class` VALUES ('4', '2班', '4', '2', '2', '2019-05-04 16:43:20', '2019-05-04 16:43:20');
INSERT INTO `info_college_subject_class` VALUES ('5', '理学院', '2', '0', '0', '2019-05-04 18:28:26', '2019-05-04 18:28:26');
INSERT INTO `info_college_subject_class` VALUES ('6', '新能源', '2', '5', '1', '2019-05-04 18:28:55', '2019-05-13 21:45:44');
INSERT INTO `info_college_subject_class` VALUES ('7', '1班', '1', '6', '2', '2019-05-04 18:29:19', '2019-05-04 18:29:19');
INSERT INTO `info_college_subject_class` VALUES ('8', '应用化学', '3', '5', '1', '2019-05-13 21:46:41', '2019-05-13 21:46:41');
INSERT INTO `info_college_subject_class` VALUES ('9', '1班', '1', '8', '2', '2019-05-13 21:47:32', '2019-05-13 21:47:46');

-- ----------------------------
-- Table structure for info_course
-- ----------------------------
DROP TABLE IF EXISTS `info_course`;
CREATE TABLE `info_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `name` varchar(20) NOT NULL COMMENT '课程名称',
  `college_id` int(11) NOT NULL COMMENT '所属学院id',
  `subject_id` int(11) NOT NULL COMMENT '所属专业id',
  `class_id` int(11) NOT NULL COMMENT '所属班级id',
  `grade_id` int(11) NOT NULL COMMENT '所属年级id',
  `school_year` varchar(10) NOT NULL COMMENT '学年，2019-2020',
  `semester` tinyint(2) NOT NULL COMMENT '学期，1上学期，2下学期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_course
-- ----------------------------
INSERT INTO `info_course` VALUES ('1', 'Java程序设计', '1', '2', '4', '1', '2015-2016', '1', '2019-05-12 18:07:52', '2019-05-12 18:13:37');
INSERT INTO `info_course` VALUES ('2', 'SQLServer数据库理论基础', '1', '2', '4', '1', '2015-2016', '2', '2019-05-12 18:07:52', '2019-05-17 13:26:32');

-- ----------------------------
-- Table structure for info_course_timetable
-- ----------------------------
DROP TABLE IF EXISTS `info_course_timetable`;
CREATE TABLE `info_course_timetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_week_num` int(5) NOT NULL COMMENT '开始周',
  `end_week_num` int(5) NOT NULL COMMENT '结束周',
  `teacher_id` int(11) NOT NULL COMMENT '教师id',
  `teacher_name` varchar(20) DEFAULT NULL COMMENT '教师名',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `day_of_week` int(2) NOT NULL COMMENT '星期几',
  `start_lesson` int(2) NOT NULL COMMENT '开始上课节数',
  `end_lesson` int(2) NOT NULL COMMENT '结束上课节数',
  `address` varchar(20) NOT NULL COMMENT '上课地点',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_course_timetable
-- ----------------------------
INSERT INTO `info_course_timetable` VALUES ('1', '2', '16', '6', '教师账号', '1', '0', '1', '2', 'C501', '2019-05-21 21:31:11', '2019-05-22 11:35:53');
INSERT INTO `info_course_timetable` VALUES ('2', '1', '18', '6', '请选择教师', '1', '3', '1', '2', 'C501', '2019-05-21 21:59:23', '2019-05-22 12:28:44');
INSERT INTO `info_course_timetable` VALUES ('3', '1', '12', '6', '教师账号', '1', '1', '9', '10', 'B509', '2019-05-22 09:26:32', '2019-05-22 09:26:32');
INSERT INTO `info_course_timetable` VALUES ('4', '1', '18', '6', '教师账号', '2', '0', '1', '2', 'A504', '2019-05-22 09:30:28', '2019-05-22 09:30:28');
INSERT INTO `info_course_timetable` VALUES ('5', '1', '12', '6', '教师账号', '2', '1', '9', '10', 'A503', '2019-05-22 09:32:52', '2019-05-22 09:32:52');

-- ----------------------------
-- Table structure for info_grade
-- ----------------------------
DROP TABLE IF EXISTS `info_grade`;
CREATE TABLE `info_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '年级id',
  `name` varchar(20) NOT NULL COMMENT '年级名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_grade
-- ----------------------------
INSERT INTO `info_grade` VALUES ('1', '2015级', '2019-05-06 20:12:53', '2019-05-06 20:12:56', '1');
INSERT INTO `info_grade` VALUES ('2', '2016级', '2019-05-06 22:14:33', '2019-05-06 22:19:26', '1');
INSERT INTO `info_grade` VALUES ('3', '2014级', '2019-05-06 22:16:16', '2019-05-06 22:16:17', '1');

-- ----------------------------
-- Table structure for info_score
-- ----------------------------
DROP TABLE IF EXISTS `info_score`;
CREATE TABLE `info_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩id',
  `score` decimal(5,2) DEFAULT NULL COMMENT '分数，保留两位小数',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `college_id` int(11) NOT NULL COMMENT '所属学院id',
  `subject_id` int(11) NOT NULL COMMENT '所属专业id',
  `class_id` int(11) NOT NULL COMMENT '所属班级id',
  `grade_id` int(11) NOT NULL COMMENT '所属年级id',
  `school_year` varchar(10) NOT NULL COMMENT '学年，2019-2020',
  `semester` tinyint(2) NOT NULL COMMENT '学期，1上学期，2下学期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_score
-- ----------------------------
INSERT INTO `info_score` VALUES ('10', '99.00', '1', '7', '1', '2', '4', '1', '2015-2016', '1', '2019-05-17 13:45:34', '2019-05-17 13:45:34', '1');
INSERT INTO `info_score` VALUES ('12', '89.00', '2', '7', '1', '2', '4', '1', '2015-2016', '2', '2019-05-17 13:48:51', '2019-05-17 13:48:51', '1');

-- ----------------------------
-- Table structure for sys_authorities
-- ----------------------------
DROP TABLE IF EXISTS `sys_authorities`;
CREATE TABLE `sys_authorities` (
  `authority_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `authority_name` varchar(20) NOT NULL COMMENT '权限名称',
  `authority` varchar(40) DEFAULT NULL COMMENT '授权标识',
  `menu_url` varchar(40) DEFAULT NULL COMMENT '菜单url',
  `parent_id` int(11) NOT NULL DEFAULT '-1' COMMENT '父id',
  `is_menu` int(1) NOT NULL DEFAULT '0' COMMENT '0菜单，1按钮',
  `order_number` int(3) NOT NULL DEFAULT '0' COMMENT '排序号',
  `menu_icon` varchar(30) DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of sys_authorities
-- ----------------------------
INSERT INTO `sys_authorities` VALUES ('1', '系统管理', '', '', '-1', '0', '4', 'layui-icon-set', '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('2', '用户管理', '', 'system/user', '1', '0', '3', '', '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('3', '查询用户', 'user:view', '', '2', '1', '3', '', '2018-07-21 13:54:16', '2018-07-21 13:54:16');
INSERT INTO `sys_authorities` VALUES ('4', '添加用户', 'user:add', null, '2', '1', '4', null, '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('5', '修改用户', 'user:edit', null, '2', '1', '5', null, '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('6', '删除用户', 'user:delete', null, '2', '1', '6', null, '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('7', '角色管理', '', 'system/role', '1', '0', '7', '', '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('8', '查询角色', 'role:view', '', '7', '1', '8', '', '2018-07-21 13:54:59', '2018-07-21 13:54:58');
INSERT INTO `sys_authorities` VALUES ('9', '添加角色', 'role:add', '', '7', '1', '9', '', '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('10', '修改角色', 'role:edit', '', '7', '1', '10', '', '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('11', '删除角色', 'role:delete', '', '7', '1', '11', '', '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('12', '角色权限管理', 'role:auth', '', '7', '1', '12', '', '2018-06-29 11:05:41', '2018-07-13 15:27:18');
INSERT INTO `sys_authorities` VALUES ('13', '权限管理', null, 'system/authorities', '1', '0', '13', null, '2018-06-29 11:05:41', '2018-07-13 15:45:13');
INSERT INTO `sys_authorities` VALUES ('14', '查询权限', 'authorities:view', '', '13', '1', '14', '', '2018-07-21 13:55:57', '2018-07-21 13:55:56');
INSERT INTO `sys_authorities` VALUES ('15', '添加权限', 'authorities:add', '', '13', '1', '15', '', '2018-06-29 11:05:41', '2018-06-29 11:05:41');
INSERT INTO `sys_authorities` VALUES ('16', '修改权限', 'authorities:edit', '', '13', '1', '16', '', '2018-07-13 09:13:42', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('17', '删除权限', 'authorities:delete', '', '13', '1', '17', '', '2018-06-29 11:05:41', '2018-06-29 11:05:41');
INSERT INTO `sys_authorities` VALUES ('27', '仪表盘（参考）', '', '', '-1', '0', '101', 'layui-icon-home', '2018-10-27 14:07:20', '2018-10-27 14:07:20');
INSERT INTO `sys_authorities` VALUES ('28', '控制台', '', '/other/console', '27', '0', '1', '', '2018-10-27 14:19:44', '2018-10-27 14:19:44');
INSERT INTO `sys_authorities` VALUES ('29', '分析页', '', '/other/console2', '27', '0', '2', '', '2018-10-27 15:18:45', '2018-10-27 15:18:45');
INSERT INTO `sys_authorities` VALUES ('30', '欢迎页', '', '/other/welcome', '27', '0', '3', '', '2018-10-27 15:27:50', '2018-10-27 15:27:50');
INSERT INTO `sys_authorities` VALUES ('31', '表单页（参考）', '', '', '-1', '0', '102', 'layui-icon-form', '2018-10-27 15:32:41', '2018-10-27 15:32:41');
INSERT INTO `sys_authorities` VALUES ('32', '基础表单', '', 'other/formBasic', '31', '0', '0', '', '2018-10-27 15:33:24', '2018-10-27 15:33:24');
INSERT INTO `sys_authorities` VALUES ('33', '复杂表单', '', 'other/formAdvance', '31', '0', '1', '', '2018-10-27 15:33:52', '2018-10-27 15:33:52');
INSERT INTO `sys_authorities` VALUES ('34', '分步表单', '', 'other/formStep', '31', '0', '2', '', '2018-10-27 15:34:16', '2018-10-27 15:34:16');
INSERT INTO `sys_authorities` VALUES ('35', '表格页（参考）', '', '', '-1', '0', '103', 'layui-icon-table', '2018-10-27 15:45:26', '2018-10-27 15:45:26');
INSERT INTO `sys_authorities` VALUES ('36', '数据表格', '', 'other/tableBasic', '35', '0', '0', '', '2018-10-27 15:46:03', '2018-10-27 15:46:03');
INSERT INTO `sys_authorities` VALUES ('37', '复杂查询', '', 'other/tableAdvance', '35', '0', '1', '', '2018-10-27 15:46:24', '2018-10-27 15:46:24');
INSERT INTO `sys_authorities` VALUES ('38', '树形表格', '', 'other/tableTree', '35', '0', '2', '', '2018-10-27 15:46:50', '2018-10-27 15:46:50');
INSERT INTO `sys_authorities` VALUES ('39', '平台信息', '', '', '-1', '0', '1', 'layui-icon-about', '2018-10-29 11:12:08', '2018-10-29 11:12:08');
INSERT INTO `sys_authorities` VALUES ('40', '平台简介', '', 'system/platformInfo', '39', '0', '1', '', '2018-10-29 11:13:15', '2018-10-29 11:13:15');
INSERT INTO `sys_authorities` VALUES ('47', '课程信息', '', '', '-1', '0', '3', 'layui-icon-template-1', '2018-10-29 11:21:30', '2018-10-29 11:21:30');
INSERT INTO `sys_authorities` VALUES ('50', '其他页（参考）', '', '', '-1', '0', '104', 'layui-icon-table', '2018-11-20 13:48:40', '2018-11-20 13:48:40');
INSERT INTO `sys_authorities` VALUES ('51', '下拉菜单', '', '/other/dropdown', '50', '0', '1', '', '2018-11-20 14:08:59', '2018-11-20 14:08:59');
INSERT INTO `sys_authorities` VALUES ('52', '通知消息', '', '/other/notice', '50', '0', '2', '', '2018-11-20 14:09:28', '2018-11-20 14:09:28');
INSERT INTO `sys_authorities` VALUES ('53', '风格弹窗', '', '/other/dialog', '50', '0', '3', '', '2018-11-20 14:09:58', '2018-11-20 14:09:58');
INSERT INTO `sys_authorities` VALUES ('67', '课程管理', '', 'course/manage', '47', '0', '3', '', '2019-03-15 13:57:16', '2019-03-15 13:57:16');
INSERT INTO `sys_authorities` VALUES ('73', '查看课程', '', 'course/view', '47', '0', '4', '', '2019-04-29 21:50:17', '2019-04-29 21:50:17');
INSERT INTO `sys_authorities` VALUES ('74', '成绩信息', '', '', '-1', '0', '2', 'layui-icon-read', '2019-04-29 21:52:42', '2019-04-29 21:52:42');
INSERT INTO `sys_authorities` VALUES ('75', '成绩管理', '', 'score/manage', '74', '0', '1', '', '2019-04-29 21:54:13', '2019-04-29 21:54:13');
INSERT INTO `sys_authorities` VALUES ('76', '班级成绩', '', 'score/class', '74', '0', '2', '', '2019-04-29 21:55:14', '2019-04-29 21:55:14');
INSERT INTO `sys_authorities` VALUES ('77', '个人成绩', '', 'score/person', '74', '0', '3', '', '2019-04-29 21:57:05', '2019-04-29 21:57:05');
INSERT INTO `sys_authorities` VALUES ('78', '成绩趋势', '', 'score/trend', '74', '0', '4', '', '2019-04-29 21:58:04', '2019-04-29 21:58:04');
INSERT INTO `sys_authorities` VALUES ('79', '学院专业管理', '', 'system/college', '1', '0', '1', '', '2019-04-29 22:00:31', '2019-04-29 22:00:31');
INSERT INTO `sys_authorities` VALUES ('80', '通知公告', '', 'system/notice', '1', '0', '18', '', '2019-04-29 22:02:36', '2019-04-29 22:02:36');
INSERT INTO `sys_authorities` VALUES ('81', '系统信息', '', 'system/platformManage', '1', '0', '19', '', '2019-04-29 22:03:52', '2019-04-29 22:03:52');
INSERT INTO `sys_authorities` VALUES ('82', '查询学院信息', 'college:view', '', '79', '1', '1', '', '2019-05-04 11:59:49', '2019-05-04 11:59:49');
INSERT INTO `sys_authorities` VALUES ('83', '修改学院信息', 'college:edit', '', '79', '1', '3', '', '2019-05-04 12:00:58', '2019-05-04 12:00:58');
INSERT INTO `sys_authorities` VALUES ('84', '添加学院信息', 'college:add', '', '79', '1', '2', '', '2019-05-04 12:01:43', '2019-05-04 12:01:43');
INSERT INTO `sys_authorities` VALUES ('85', '删除学院信息', 'college:delete', '', '79', '1', '4', '', '2019-05-04 12:02:20', '2019-05-04 12:02:20');
INSERT INTO `sys_authorities` VALUES ('86', '年级管理', '', 'system/gradeManage', '1', '0', '2', '', '2019-05-06 23:17:00', '2019-05-06 23:17:00');
INSERT INTO `sys_authorities` VALUES ('87', '登录日志', '', 'system/loginRecord', '1', '0', '20', '', '2019-05-08 10:22:16', '2019-05-08 10:22:16');
INSERT INTO `sys_authorities` VALUES ('88', '查询登录日志', 'loginRecord:view', '', '87', '1', '21', '', '2019-05-08 10:23:45', '2019-05-08 10:23:45');

-- ----------------------------
-- Table structure for sys_information
-- ----------------------------
DROP TABLE IF EXISTS `sys_information`;
CREATE TABLE `sys_information` (
  `id` int(11) NOT NULL COMMENT '系统信息id',
  `message` text COMMENT '系统欢迎信息',
  `school_name` varchar(20) DEFAULT NULL COMMENT '学校名称',
  `developer_name` varchar(20) DEFAULT NULL COMMENT '系统开发者',
  `information` text COMMENT '系统简介',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_information
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_record`;
CREATE TABLE `sys_login_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `os_name` varchar(40) DEFAULT NULL COMMENT '操作系统',
  `device` varchar(40) DEFAULT NULL COMMENT '设备名',
  `browser_type` varchar(40) DEFAULT NULL COMMENT '浏览器类型',
  `ip_address` varchar(40) DEFAULT NULL COMMENT 'ip地址',
  `create_time` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`),
  KEY `FK_sys_login_record_user` (`user_id`),
  CONSTRAINT `sys_login_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_login_record
-- ----------------------------
INSERT INTO `sys_login_record` VALUES ('1', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-08 20:40:51');
INSERT INTO `sys_login_record` VALUES ('2', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-08 21:11:47');
INSERT INTO `sys_login_record` VALUES ('3', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-08 21:13:39');
INSERT INTO `sys_login_record` VALUES ('4', '7', 'Windows 10', 'Windows 10', 'Microsoft Edge', '169.254.191.202', '2019-05-08 21:37:21');
INSERT INTO `sys_login_record` VALUES ('5', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-08 21:39:34');
INSERT INTO `sys_login_record` VALUES ('6', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-08 21:56:17');
INSERT INTO `sys_login_record` VALUES ('7', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-08 22:35:36');
INSERT INTO `sys_login_record` VALUES ('8', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-08 22:36:50');
INSERT INTO `sys_login_record` VALUES ('9', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-09 21:38:36');
INSERT INTO `sys_login_record` VALUES ('10', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-09 21:41:07');
INSERT INTO `sys_login_record` VALUES ('11', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-09 22:50:01');
INSERT INTO `sys_login_record` VALUES ('12', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-10 21:13:29');
INSERT INTO `sys_login_record` VALUES ('13', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-10 23:03:19');
INSERT INTO `sys_login_record` VALUES ('14', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-12 18:11:41');
INSERT INTO `sys_login_record` VALUES ('15', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-12 21:07:58');
INSERT INTO `sys_login_record` VALUES ('16', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-12 21:33:14');
INSERT INTO `sys_login_record` VALUES ('17', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-12 21:48:37');
INSERT INTO `sys_login_record` VALUES ('18', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-12 22:49:56');
INSERT INTO `sys_login_record` VALUES ('19', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-13 19:04:36');
INSERT INTO `sys_login_record` VALUES ('20', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-13 21:44:18');
INSERT INTO `sys_login_record` VALUES ('21', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-13 21:51:50');
INSERT INTO `sys_login_record` VALUES ('22', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-13 21:54:56');
INSERT INTO `sys_login_record` VALUES ('23', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-13 22:02:24');
INSERT INTO `sys_login_record` VALUES ('24', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-13 22:08:15');
INSERT INTO `sys_login_record` VALUES ('25', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-14 22:23:52');
INSERT INTO `sys_login_record` VALUES ('26', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-17 11:09:20');
INSERT INTO `sys_login_record` VALUES ('27', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-17 12:40:14');
INSERT INTO `sys_login_record` VALUES ('28', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-17 12:48:12');
INSERT INTO `sys_login_record` VALUES ('29', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-17 13:17:21');
INSERT INTO `sys_login_record` VALUES ('30', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-18 10:41:58');
INSERT INTO `sys_login_record` VALUES ('31', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-18 13:26:19');
INSERT INTO `sys_login_record` VALUES ('32', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-18 14:46:58');
INSERT INTO `sys_login_record` VALUES ('33', '2', 'Android Mobile', 'MI 8', 'Chrome Mobile', '192.168.43.1', '2019-05-18 15:22:10');
INSERT INTO `sys_login_record` VALUES ('34', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-20 11:04:25');
INSERT INTO `sys_login_record` VALUES ('35', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-20 19:06:28');
INSERT INTO `sys_login_record` VALUES ('36', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-20 19:20:36');
INSERT INTO `sys_login_record` VALUES ('37', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-20 22:28:37');
INSERT INTO `sys_login_record` VALUES ('38', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-20 23:12:45');
INSERT INTO `sys_login_record` VALUES ('39', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-21 12:28:36');
INSERT INTO `sys_login_record` VALUES ('40', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-21 18:47:15');
INSERT INTO `sys_login_record` VALUES ('41', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-21 19:07:04');
INSERT INTO `sys_login_record` VALUES ('42', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-21 21:30:28');
INSERT INTO `sys_login_record` VALUES ('43', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-21 21:35:32');
INSERT INTO `sys_login_record` VALUES ('44', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-21 21:47:07');
INSERT INTO `sys_login_record` VALUES ('45', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-21 21:53:53');
INSERT INTO `sys_login_record` VALUES ('46', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-22 09:25:28');
INSERT INTO `sys_login_record` VALUES ('47', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-22 10:36:34');
INSERT INTO `sys_login_record` VALUES ('48', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-22 11:55:37');
INSERT INTO `sys_login_record` VALUES ('49', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-22 15:36:53');
INSERT INTO `sys_login_record` VALUES ('50', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-22 15:40:33');
INSERT INTO `sys_login_record` VALUES ('51', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-22 16:15:24');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `content` text COMMENT '公告内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '内容状态，0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `comments` varchar(100) DEFAULT NULL COMMENT '备注',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0否，1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '超级管理员', '0', '2018-07-21 09:58:31', '2018-07-21 11:07:16');
INSERT INTO `sys_role` VALUES ('2', '管理员', '系统管理员', '0', '2018-07-21 09:58:35', '2018-07-21 11:07:18');
INSERT INTO `sys_role` VALUES ('3', '教师', '系统教师用户', '0', '2018-07-21 09:58:39', '2019-04-25 14:24:42');
INSERT INTO `sys_role` VALUES ('4', '学生', '系统学生用户', '0', '2019-04-25 14:25:03', '2019-04-25 14:25:10');

-- ----------------------------
-- Table structure for sys_role_authorities
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authorities`;
CREATE TABLE `sys_role_authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `authority_id` int(11) NOT NULL COMMENT '权限id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_sys_role_permission_pm` (`authority_id`),
  KEY `FK_sys_role_permission_role` (`role_id`),
  CONSTRAINT `sys_role_authorities_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `sys_role_authorities_ibfk_3` FOREIGN KEY (`authority_id`) REFERENCES `sys_authorities` (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1352 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色权限关联表';

-- ----------------------------
-- Records of sys_role_authorities
-- ----------------------------
INSERT INTO `sys_role_authorities` VALUES ('1182', '4', '39', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1183', '4', '40', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1184', '4', '74', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1185', '4', '76', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1186', '4', '77', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1187', '4', '78', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1188', '4', '47', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1189', '4', '73', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1190', '4', '1', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1191', '4', '79', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1192', '4', '82', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1193', '4', '2', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1194', '4', '3', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1195', '4', '80', '2019-05-04 19:17:12');
INSERT INTO `sys_role_authorities` VALUES ('1196', '3', '39', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1197', '3', '40', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1198', '3', '74', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1199', '3', '75', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1200', '3', '76', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1201', '3', '47', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1202', '3', '67', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1203', '3', '73', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1204', '3', '1', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1205', '3', '79', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1206', '3', '82', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1207', '3', '2', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1208', '3', '3', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1209', '3', '80', '2019-05-04 19:17:44');
INSERT INTO `sys_role_authorities` VALUES ('1210', '1', '39', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1211', '1', '40', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1212', '1', '74', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1213', '1', '75', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1214', '1', '76', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1215', '1', '77', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1216', '1', '78', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1217', '1', '47', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1218', '1', '67', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1219', '1', '73', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1220', '1', '1', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1221', '1', '79', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1222', '1', '82', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1223', '1', '84', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1224', '1', '83', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1225', '1', '85', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1226', '1', '2', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1227', '1', '3', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1228', '1', '4', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1229', '1', '5', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1230', '1', '6', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1231', '1', '7', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1232', '1', '8', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1233', '1', '9', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1234', '1', '10', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1235', '1', '11', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1236', '1', '12', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1237', '1', '13', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1238', '1', '14', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1239', '1', '15', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1240', '1', '16', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1241', '1', '17', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1242', '1', '80', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1243', '1', '81', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1244', '1', '27', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1245', '1', '28', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1246', '1', '29', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1247', '1', '30', '2019-05-04 19:18:00');
INSERT INTO `sys_role_authorities` VALUES ('1299', '2', '39', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1300', '2', '40', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1301', '2', '74', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1302', '2', '75', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1303', '2', '76', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1304', '2', '77', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1305', '2', '78', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1306', '2', '47', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1307', '2', '67', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1308', '2', '73', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1309', '2', '1', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1310', '2', '79', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1311', '2', '82', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1312', '2', '84', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1313', '2', '83', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1314', '2', '85', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1315', '2', '86', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1316', '2', '2', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1317', '2', '3', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1318', '2', '4', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1319', '2', '5', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1320', '2', '6', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1321', '2', '7', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1322', '2', '8', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1323', '2', '9', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1324', '2', '10', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1325', '2', '11', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1326', '2', '12', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1327', '2', '13', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1328', '2', '14', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1329', '2', '15', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1330', '2', '16', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1331', '2', '17', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1332', '2', '80', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1333', '2', '81', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1334', '2', '87', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1335', '2', '88', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1336', '2', '27', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1337', '2', '28', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1338', '2', '29', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1339', '2', '30', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1340', '2', '31', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1341', '2', '32', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1342', '2', '33', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1343', '2', '34', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1344', '2', '35', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1345', '2', '36', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1346', '2', '37', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1347', '2', '38', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1348', '2', '50', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1349', '2', '51', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1350', '2', '52', '2019-05-09 21:40:47');
INSERT INTO `sys_role_authorities` VALUES ('1351', '2', '53', '2019-05-09 21:40:47');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '账号',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `nick_name` varchar(20) NOT NULL COMMENT '昵称',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `sex` varchar(1) NOT NULL DEFAULT '男' COMMENT '性别',
  `phone` varchar(12) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `person_type` tinyint(11) DEFAULT NULL COMMENT '人员类型，比如：0超级管理员，1管理员，2教师，3学生',
  `college_id` int(11) NOT NULL COMMENT '所属学院id',
  `subject_id` int(11) NOT NULL COMMENT '所属专业id',
  `class_id` int(11) NOT NULL COMMENT '所属班级id',
  `grade_id` int(11) NOT NULL COMMENT '所属年级id',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0正常，1冻结',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'superadmin', '2d4d426bb63055bfdc17bed0d10792b7', '超级管理员', null, '男', '12345678901', null, '0', '0', '0', '0', '0', '0', '2018-07-21 10:03:40', '2019-05-03 17:51:44');
INSERT INTO `sys_user` VALUES ('2', 'admin', '3fed7a346e430ea4c2aa10250928f4de', '管理员', null, '男', '12345678901', null, '1', '0', '0', '0', '2', '0', '2018-07-21 10:50:18', '2019-05-06 22:14:44');
INSERT INTO `sys_user` VALUES ('6', 'teacher', '629a1bf631c5ee1e898e1cd283e5c156', '教师账号', null, '女', '18849334210', null, '2', '1', '2', '0', '2', '0', '2019-04-25 15:30:49', '2019-05-21 19:16:43');
INSERT INTO `sys_user` VALUES ('7', 'student', '7b5470e577dc3c5750d1a9f49ae660a3', '仙女大人', null, '女', '18849334210', null, '3', '1', '2', '4', '1', '0', '2019-04-25 15:31:11', '2019-05-13 22:02:13');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sys_user_role` (`user_id`),
  KEY `FK_sys_user_role_role` (`role_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '2018-07-18 15:25:50');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2', '2018-07-19 09:48:33');
INSERT INTO `sys_user_role` VALUES ('9', '6', '3', '2019-04-25 15:30:49');
INSERT INTO `sys_user_role` VALUES ('11', '7', '4', '2019-05-04 11:04:14');
SET FOREIGN_KEY_CHECKS=1;
