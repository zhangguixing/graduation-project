/*
Navicat MySQL Data Transfer

Source Server         : xing
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : ssms

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-06-23 21:04:14
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

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
INSERT INTO `info_college_subject_class` VALUES ('10', '信息工程', '5', '1', '1', '2019-05-25 19:18:38', '2019-05-25 19:18:38');
INSERT INTO `info_college_subject_class` VALUES ('11', '1班', '6', '10', '2', '2019-05-25 19:19:02', '2019-05-25 19:19:02');

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_course
-- ----------------------------
INSERT INTO `info_course` VALUES ('1', 'Java程序设计', '1', '2', '4', '1', '2015-2016', '1', '2019-05-12 18:07:52', '2019-05-12 18:13:37');
INSERT INTO `info_course` VALUES ('2', 'SQLServer数据库理论基础', '1', '2', '4', '1', '2015-2016', '2', '2019-05-12 18:07:52', '2019-05-17 13:26:32');
INSERT INTO `info_course` VALUES ('9', '数据结构与算法', '1', '2', '3', '2', '2016-2017', '1', '2019-05-23 20:20:58', '2019-05-23 20:20:58');
INSERT INTO `info_course` VALUES ('10', 'C语言程序设计', '1', '2', '4', '1', '2016-2017', '1', '2019-06-17 11:14:11', '2019-06-17 11:14:11');
INSERT INTO `info_course` VALUES ('11', 'JSP程序设计', '1', '2', '4', '1', '2016-2017', '2', '2019-06-17 11:14:39', '2019-06-17 11:14:39');
INSERT INTO `info_course` VALUES ('12', '计算机网络', '1', '2', '4', '1', '2017-2018', '1', '2019-06-17 11:15:07', '2019-06-17 11:15:07');
INSERT INTO `info_course` VALUES ('13', '高等数学', '1', '2', '4', '1', '2017-2018', '2', '2019-06-17 11:15:37', '2019-06-17 11:15:37');
INSERT INTO `info_course` VALUES ('14', 'Linux操作系统', '1', '2', '4', '1', '2017-2018', '2', '2019-06-17 11:16:25', '2019-06-17 11:16:25');
INSERT INTO `info_course` VALUES ('15', '操作系统', '1', '2', '4', '1', '2015-2016', '1', '2019-06-17 11:16:56', '2019-06-17 11:16:56');
INSERT INTO `info_course` VALUES ('16', 'C++程序设计', '1', '2', '4', '1', '2015-2016', '2', '2019-06-17 11:17:31', '2019-06-17 11:17:31');
INSERT INTO `info_course` VALUES ('17', 'C#程序设计', '1', '2', '4', '1', '2016-2017', '1', '2019-06-17 11:17:47', '2019-06-17 11:17:47');
INSERT INTO `info_course` VALUES ('18', 'ARM汇编指令', '1', '2', '4', '1', '2016-2017', '2', '2019-06-17 11:18:39', '2019-06-17 11:18:39');
INSERT INTO `info_course` VALUES ('19', 'Android开发', '1', '2', '4', '1', '2017-2018', '1', '2019-06-17 11:19:53', '2019-06-17 11:19:53');
INSERT INTO `info_course` VALUES ('20', '计算机组成原理', '1', '2', '4', '1', '2017-2018', '2', '2019-06-17 11:20:23', '2019-06-17 11:20:23');
INSERT INTO `info_course` VALUES ('21', 'SQLServer数据库原理', '1', '2', '4', '1', '2017-2018', '2', '2019-06-17 11:21:01', '2019-06-17 11:21:01');
INSERT INTO `info_course` VALUES ('22', '计算机导论', '1', '2', '4', '1', '2015-2016', '1', '2019-06-17 12:11:57', '2019-06-17 12:11:57');
INSERT INTO `info_course` VALUES ('25', 'Java程序设计', '1', '2', '3', '1', '2015-2016', '1', '2019-06-19 19:17:08', '2019-06-19 19:17:08');
INSERT INTO `info_course` VALUES ('26', '操作系统', '1', '2', '3', '1', '2015-2016', '1', '2019-06-20 14:57:25', '2019-06-20 14:57:25');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_course_timetable
-- ----------------------------
INSERT INTO `info_course_timetable` VALUES ('2', '1', '17', '6', '人民教师', '1', '3', '1', '2', 'A503', '2019-05-21 21:59:23', '2019-06-17 12:12:49');
INSERT INTO `info_course_timetable` VALUES ('3', '1', '12', '6', '人民教师', '1', '1', '9', '10', 'B509', '2019-05-22 09:26:32', '2019-06-17 12:14:18');
INSERT INTO `info_course_timetable` VALUES ('4', '1', '18', '6', '人民教师', '2', '0', '1', '2', 'A504', '2019-05-22 09:30:28', '2019-06-17 12:17:29');
INSERT INTO `info_course_timetable` VALUES ('5', '1', '12', '6', '人民教师', '2', '1', '9', '10', 'A503', '2019-05-22 09:32:52', '2019-06-17 12:14:21');
INSERT INTO `info_course_timetable` VALUES ('6', '1', '18', '6', '人民教师', '1', '1', '3', '4', 'A503', '2019-06-17 11:21:56', '2019-06-17 12:14:22');
INSERT INTO `info_course_timetable` VALUES ('7', '1', '18', '39', '曹老师', '15', '0', '5', '6', 'C501', '2019-06-17 12:08:37', '2019-06-17 12:08:47');
INSERT INTO `info_course_timetable` VALUES ('8', '1', '18', '39', '曹老师', '15', '2', '7', '8', 'C501', '2019-06-17 12:09:16', '2019-06-17 12:09:16');
INSERT INTO `info_course_timetable` VALUES ('10', '1', '18', '40', '张老师', '22', '4', '3', '4', 'B406', '2019-06-17 12:12:34', '2019-06-17 12:12:34');
INSERT INTO `info_course_timetable` VALUES ('12', '2', '18', '6', '人民教师', '1', '0', '1', '2', 'C501', '2019-06-17 12:29:51', '2019-06-17 12:29:51');
INSERT INTO `info_course_timetable` VALUES ('13', '1', '18', '40', '张老师', '17', '0', '1', '2', 'A508', '2019-06-17 16:37:06', '2019-06-17 16:37:06');

-- ----------------------------
-- Table structure for info_grade
-- ----------------------------
DROP TABLE IF EXISTS `info_grade`;
CREATE TABLE `info_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '年级id',
  `name` varchar(20) NOT NULL COMMENT '年级名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态，0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_grade
-- ----------------------------
INSERT INTO `info_grade` VALUES ('1', '2015级', '2019-05-06 20:12:53', '2019-05-06 20:12:56', '1');
INSERT INTO `info_grade` VALUES ('2', '2016级', '2019-05-06 22:14:33', '2019-05-23 17:08:15', '1');
INSERT INTO `info_grade` VALUES ('4', '2017级', '2019-05-23 16:49:20', '2019-05-23 17:08:25', '1');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_score
-- ----------------------------
INSERT INTO `info_score` VALUES ('125', '99.00', '1', '33', '1', '2', '3', '1', '2015-2016', '1', '2019-05-27 12:05:06', '2019-05-27 12:05:06');
INSERT INTO `info_score` VALUES ('126', '89.00', '2', '33', '1', '2', '3', '1', '2015-2016', '1', '2019-05-27 12:05:06', '2019-05-27 12:05:06');
INSERT INTO `info_score` VALUES ('127', '98.00', '9', '33', '1', '2', '3', '1', '2015-2016', '1', '2019-05-27 12:05:06', '2019-05-27 12:05:06');
INSERT INTO `info_score` VALUES ('131', '98.00', '1', '34', '1', '2', '4', '1', '2015-2016', '1', '2019-05-27 12:07:14', '2019-05-27 12:07:14');
INSERT INTO `info_score` VALUES ('132', '92.00', '2', '34', '1', '2', '4', '1', '2015-2016', '1', '2019-05-27 12:07:14', '2019-05-27 12:07:14');
INSERT INTO `info_score` VALUES ('133', '88.00', '9', '34', '1', '2', '4', '1', '2015-2016', '1', '2019-05-27 12:07:14', '2019-05-27 12:07:14');
INSERT INTO `info_score` VALUES ('134', '92.00', '2', '33', '1', '2', '4', '1', '2015-2016', '2', '2019-06-17 11:28:29', '2019-06-17 11:28:29');
INSERT INTO `info_score` VALUES ('135', '89.00', '16', '33', '1', '2', '4', '1', '2015-2016', '2', '2019-06-17 11:28:29', '2019-06-17 11:28:29');
INSERT INTO `info_score` VALUES ('136', '77.00', '10', '33', '1', '2', '4', '1', '2016-2017', '1', '2019-06-17 11:30:13', '2019-06-17 11:39:27');
INSERT INTO `info_score` VALUES ('137', '80.00', '17', '33', '1', '2', '4', '1', '2016-2017', '1', '2019-06-17 11:30:13', '2019-06-17 11:39:30');
INSERT INTO `info_score` VALUES ('140', '96.00', '12', '33', '1', '2', '4', '1', '2017-2018', '1', '2019-06-17 11:32:30', '2019-06-17 11:38:25');
INSERT INTO `info_score` VALUES ('141', '87.00', '19', '33', '1', '2', '4', '1', '2017-2018', '1', '2019-06-17 11:32:30', '2019-06-17 11:38:11');
INSERT INTO `info_score` VALUES ('142', '98.00', '11', '33', '1', '2', '4', '1', '2016-2017', '2', '2019-06-17 11:32:23', '2019-06-17 11:32:23');
INSERT INTO `info_score` VALUES ('143', '99.00', '18', '33', '1', '2', '4', '1', '2016-2017', '2', '2019-06-17 11:32:23', '2019-06-17 11:39:06');
INSERT INTO `info_score` VALUES ('144', '85.00', '13', '33', '1', '2', '4', '1', '2017-2018', '2', '2019-06-17 11:33:23', '2019-06-17 11:33:23');
INSERT INTO `info_score` VALUES ('145', '99.00', '14', '33', '1', '2', '4', '1', '2017-2018', '2', '2019-06-17 11:33:23', '2019-06-17 11:33:23');
INSERT INTO `info_score` VALUES ('146', '92.00', '20', '33', '1', '2', '4', '1', '2017-2018', '2', '2019-06-17 11:33:23', '2019-06-17 11:33:23');
INSERT INTO `info_score` VALUES ('147', '86.00', '21', '33', '1', '2', '4', '1', '2017-2018', '2', '2019-06-17 11:33:23', '2019-06-17 11:33:23');
INSERT INTO `info_score` VALUES ('148', '98.00', '1', '7', '1', '2', '4', '1', '2015-2016', '1', '2019-06-17 11:40:38', '2019-06-17 11:40:38');
INSERT INTO `info_score` VALUES ('149', '82.00', '15', '7', '1', '2', '4', '1', '2015-2016', '1', '2019-06-17 11:40:38', '2019-06-17 11:40:38');
INSERT INTO `info_score` VALUES ('150', '99.00', '2', '7', '1', '2', '4', '1', '2015-2016', '2', '2019-06-17 12:04:47', '2019-06-17 12:04:47');
INSERT INTO `info_score` VALUES ('151', '88.00', '16', '7', '1', '2', '4', '1', '2015-2016', '2', '2019-06-17 12:04:47', '2019-06-17 12:04:47');
INSERT INTO `info_score` VALUES ('152', '97.00', '10', '7', '1', '2', '4', '1', '2016-2017', '1', '2019-06-17 12:05:22', '2019-06-17 12:05:22');
INSERT INTO `info_score` VALUES ('153', '63.00', '17', '7', '1', '2', '4', '1', '2016-2017', '1', '2019-06-17 12:05:22', '2019-06-17 12:05:22');
INSERT INTO `info_score` VALUES ('154', '88.00', '11', '7', '1', '2', '4', '1', '2016-2017', '2', '2019-06-17 12:05:58', '2019-06-17 12:05:58');
INSERT INTO `info_score` VALUES ('155', '99.00', '18', '7', '1', '2', '4', '1', '2016-2017', '2', '2019-06-17 12:05:58', '2019-06-17 12:05:58');
INSERT INTO `info_score` VALUES ('160', '86.00', '1', '33', '1', '2', '4', '1', '2015-2016', '1', '2019-06-20 15:08:21', '2019-06-20 15:08:21');
INSERT INTO `info_score` VALUES ('161', '85.00', '15', '33', '1', '2', '4', '1', '2015-2016', '1', '2019-06-20 15:08:21', '2019-06-20 15:08:21');
INSERT INTO `info_score` VALUES ('162', '96.00', '22', '33', '1', '2', '4', '1', '2015-2016', '1', '2019-06-20 15:08:21', '2019-06-20 15:08:21');
INSERT INTO `info_score` VALUES ('163', '86.00', '25', '36', '1', '2', '3', '1', '2015-2016', '1', '2019-06-20 15:10:04', '2019-06-20 15:10:04');
INSERT INTO `info_score` VALUES ('164', '76.00', '26', '36', '1', '2', '3', '1', '2015-2016', '1', '2019-06-20 15:10:04', '2019-06-20 15:10:04');

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
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限表';

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
INSERT INTO `sys_authorities` VALUES ('39', '欢迎使用', '', '', '-1', '0', '1', 'layui-icon-about', '2018-10-29 11:12:08', '2018-10-29 11:12:08');
INSERT INTO `sys_authorities` VALUES ('40', 'Welcome', '', 'system/welcome', '39', '0', '1', '', '2018-10-29 11:13:15', '2018-10-29 11:13:15');
INSERT INTO `sys_authorities` VALUES ('47', '课程信息', '', '', '-1', '0', '3', 'layui-icon-template-1', '2018-10-29 11:21:30', '2018-10-29 11:21:30');
INSERT INTO `sys_authorities` VALUES ('67', '课程表管理', '', 'course/timeTableManage', '47', '0', '3', '', '2019-03-15 13:57:16', '2019-03-15 13:57:16');
INSERT INTO `sys_authorities` VALUES ('73', '查看课程表', '', 'course/view', '47', '0', '4', '', '2019-04-29 21:50:17', '2019-04-29 21:50:17');
INSERT INTO `sys_authorities` VALUES ('74', '成绩信息', '', '', '-1', '0', '2', 'layui-icon-read', '2019-04-29 21:52:42', '2019-04-29 21:52:42');
INSERT INTO `sys_authorities` VALUES ('75', '成绩管理', '', 'score/manage', '74', '0', '1', '', '2019-04-29 21:54:13', '2019-04-29 21:54:13');
INSERT INTO `sys_authorities` VALUES ('76', '班级成绩', '', 'score/class', '74', '0', '2', '', '2019-04-29 21:55:14', '2019-04-29 21:55:14');
INSERT INTO `sys_authorities` VALUES ('77', '个人成绩', '', 'score/person', '74', '0', '3', '', '2019-04-29 21:57:05', '2019-04-29 21:57:05');
INSERT INTO `sys_authorities` VALUES ('78', '成绩趋势', '', 'score/trend', '74', '0', '4', '', '2019-04-29 21:58:04', '2019-04-29 21:58:04');
INSERT INTO `sys_authorities` VALUES ('79', '学院专业管理', '', 'system/college', '1', '0', '1', '', '2019-04-29 22:00:31', '2019-04-29 22:00:31');
INSERT INTO `sys_authorities` VALUES ('82', '查询学院信息', 'college:view', '', '79', '1', '1', '', '2019-05-04 11:59:49', '2019-05-04 11:59:49');
INSERT INTO `sys_authorities` VALUES ('83', '修改学院信息', 'college:edit', '', '79', '1', '3', '', '2019-05-04 12:00:58', '2019-05-04 12:00:58');
INSERT INTO `sys_authorities` VALUES ('84', '添加学院信息', 'college:add', '', '79', '1', '2', '', '2019-05-04 12:01:43', '2019-05-04 12:01:43');
INSERT INTO `sys_authorities` VALUES ('85', '删除学院信息', 'college:delete', '', '79', '1', '4', '', '2019-05-04 12:02:20', '2019-05-04 12:02:20');
INSERT INTO `sys_authorities` VALUES ('86', '年级管理', '', 'grade/manage', '1', '0', '2', '', '2019-05-06 23:17:00', '2019-05-06 23:17:00');
INSERT INTO `sys_authorities` VALUES ('87', '登录日志', '', 'system/loginRecord', '1', '0', '20', '', '2019-05-08 10:22:16', '2019-05-08 10:22:16');
INSERT INTO `sys_authorities` VALUES ('88', '查询登录日志', 'loginRecord:view', '', '87', '1', '21', '', '2019-05-08 10:23:45', '2019-05-08 10:23:45');
INSERT INTO `sys_authorities` VALUES ('89', '我的课程', '', 'course/myCourse', '47', '0', '5', '', '2019-05-22 17:41:19', '2019-05-22 17:41:19');
INSERT INTO `sys_authorities` VALUES ('90', '课程管理', '', 'course/manage', '47', '0', '2', '', '2019-05-23 17:13:17', '2019-05-23 17:13:17');
INSERT INTO `sys_authorities` VALUES ('91', '成绩分布', '', 'score/distribdution', '74', '0', '5', '', '2019-06-18 18:28:06', '2019-06-18 18:28:06');
INSERT INTO `sys_authorities` VALUES ('92', '成绩对比', '', 'score/compare', '74', '0', '6', '', '2019-06-19 00:35:40', '2019-06-19 00:35:40');

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
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

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
INSERT INTO `sys_login_record` VALUES ('52', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-22 17:39:36');
INSERT INTO `sys_login_record` VALUES ('53', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-22 17:43:19');
INSERT INTO `sys_login_record` VALUES ('54', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-22 17:51:19');
INSERT INTO `sys_login_record` VALUES ('55', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-23 15:43:17');
INSERT INTO `sys_login_record` VALUES ('56', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-23 16:18:15');
INSERT INTO `sys_login_record` VALUES ('57', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-23 16:32:35');
INSERT INTO `sys_login_record` VALUES ('58', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-23 17:03:40');
INSERT INTO `sys_login_record` VALUES ('59', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-23 17:10:58');
INSERT INTO `sys_login_record` VALUES ('60', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-23 18:17:52');
INSERT INTO `sys_login_record` VALUES ('61', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-24 10:28:35');
INSERT INTO `sys_login_record` VALUES ('62', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-24 11:16:19');
INSERT INTO `sys_login_record` VALUES ('63', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-24 17:05:21');
INSERT INTO `sys_login_record` VALUES ('64', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-24 18:27:43');
INSERT INTO `sys_login_record` VALUES ('65', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-24 19:02:03');
INSERT INTO `sys_login_record` VALUES ('66', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-24 22:18:35');
INSERT INTO `sys_login_record` VALUES ('67', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-24 22:52:08');
INSERT INTO `sys_login_record` VALUES ('68', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-24 23:18:45');
INSERT INTO `sys_login_record` VALUES ('69', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-24 23:22:10');
INSERT INTO `sys_login_record` VALUES ('70', '2', 'Android Mobile', 'MI 8', 'Chrome Mobile', '192.168.43.75', '2019-05-24 23:47:03');
INSERT INTO `sys_login_record` VALUES ('71', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-25 17:02:38');
INSERT INTO `sys_login_record` VALUES ('72', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-25 17:54:32');
INSERT INTO `sys_login_record` VALUES ('73', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-25 18:59:29');
INSERT INTO `sys_login_record` VALUES ('74', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-25 19:42:33');
INSERT INTO `sys_login_record` VALUES ('75', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-25 20:42:30');
INSERT INTO `sys_login_record` VALUES ('76', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-26 15:42:50');
INSERT INTO `sys_login_record` VALUES ('77', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-26 15:53:33');
INSERT INTO `sys_login_record` VALUES ('78', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-26 16:46:14');
INSERT INTO `sys_login_record` VALUES ('79', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-26 17:16:38');
INSERT INTO `sys_login_record` VALUES ('80', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-26 17:30:39');
INSERT INTO `sys_login_record` VALUES ('81', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-26 17:37:42');
INSERT INTO `sys_login_record` VALUES ('82', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 10:01:14');
INSERT INTO `sys_login_record` VALUES ('83', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 11:11:06');
INSERT INTO `sys_login_record` VALUES ('84', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 11:36:59');
INSERT INTO `sys_login_record` VALUES ('85', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 11:40:50');
INSERT INTO `sys_login_record` VALUES ('86', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 11:46:18');
INSERT INTO `sys_login_record` VALUES ('87', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 11:59:09');
INSERT INTO `sys_login_record` VALUES ('88', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 12:04:20');
INSERT INTO `sys_login_record` VALUES ('89', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 13:23:47');
INSERT INTO `sys_login_record` VALUES ('90', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 13:30:49');
INSERT INTO `sys_login_record` VALUES ('91', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 13:33:59');
INSERT INTO `sys_login_record` VALUES ('92', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 14:27:12');
INSERT INTO `sys_login_record` VALUES ('93', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 14:27:24');
INSERT INTO `sys_login_record` VALUES ('94', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 14:27:39');
INSERT INTO `sys_login_record` VALUES ('95', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 14:29:22');
INSERT INTO `sys_login_record` VALUES ('96', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 16:48:19');
INSERT INTO `sys_login_record` VALUES ('97', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 17:04:08');
INSERT INTO `sys_login_record` VALUES ('98', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-27 20:23:56');
INSERT INTO `sys_login_record` VALUES ('99', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 10:03:52');
INSERT INTO `sys_login_record` VALUES ('100', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 11:20:35');
INSERT INTO `sys_login_record` VALUES ('101', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 11:32:12');
INSERT INTO `sys_login_record` VALUES ('102', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 11:32:19');
INSERT INTO `sys_login_record` VALUES ('103', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 11:32:57');
INSERT INTO `sys_login_record` VALUES ('104', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 11:35:01');
INSERT INTO `sys_login_record` VALUES ('105', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 11:36:02');
INSERT INTO `sys_login_record` VALUES ('106', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 11:41:10');
INSERT INTO `sys_login_record` VALUES ('107', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 11:50:10');
INSERT INTO `sys_login_record` VALUES ('108', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 12:02:55');
INSERT INTO `sys_login_record` VALUES ('109', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 12:07:28');
INSERT INTO `sys_login_record` VALUES ('110', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 12:07:54');
INSERT INTO `sys_login_record` VALUES ('111', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 12:22:14');
INSERT INTO `sys_login_record` VALUES ('112', '7', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 12:28:42');
INSERT INTO `sys_login_record` VALUES ('113', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 12:29:40');
INSERT INTO `sys_login_record` VALUES ('114', '6', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 12:35:38');
INSERT INTO `sys_login_record` VALUES ('115', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 14:34:14');
INSERT INTO `sys_login_record` VALUES ('116', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 15:14:40');
INSERT INTO `sys_login_record` VALUES ('117', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 15:16:31');
INSERT INTO `sys_login_record` VALUES ('118', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 15:48:38');
INSERT INTO `sys_login_record` VALUES ('119', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 15:51:48');
INSERT INTO `sys_login_record` VALUES ('120', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 16:09:02');
INSERT INTO `sys_login_record` VALUES ('121', '2', 'Windows 10', 'Windows 10', 'Chrome', '169.254.191.202', '2019-05-28 16:36:46');
INSERT INTO `sys_login_record` VALUES ('122', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-03 10:14:53');
INSERT INTO `sys_login_record` VALUES ('123', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-03 10:18:23');
INSERT INTO `sys_login_record` VALUES ('124', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-03 10:20:47');
INSERT INTO `sys_login_record` VALUES ('125', '6', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-03 10:25:16');
INSERT INTO `sys_login_record` VALUES ('126', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-03 10:26:10');
INSERT INTO `sys_login_record` VALUES ('127', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-03 10:27:17');
INSERT INTO `sys_login_record` VALUES ('128', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-03 10:33:32');
INSERT INTO `sys_login_record` VALUES ('129', '6', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-03 11:09:39');
INSERT INTO `sys_login_record` VALUES ('130', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-03 11:18:32');
INSERT INTO `sys_login_record` VALUES ('131', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-11 13:33:13');
INSERT INTO `sys_login_record` VALUES ('132', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-11 13:33:16');
INSERT INTO `sys_login_record` VALUES ('133', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-11 15:21:48');
INSERT INTO `sys_login_record` VALUES ('134', '2', 'Windows 10', 'Windows 10', 'Chrome', '127.0.0.1', '2019-06-12 11:42:07');
INSERT INTO `sys_login_record` VALUES ('135', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-12 20:11:48');
INSERT INTO `sys_login_record` VALUES ('136', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-12 21:33:01');
INSERT INTO `sys_login_record` VALUES ('137', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-12 21:54:19');
INSERT INTO `sys_login_record` VALUES ('138', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-12 22:01:08');
INSERT INTO `sys_login_record` VALUES ('139', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 15:35:56');
INSERT INTO `sys_login_record` VALUES ('140', '6', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 15:48:21');
INSERT INTO `sys_login_record` VALUES ('141', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 16:20:49');
INSERT INTO `sys_login_record` VALUES ('142', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 17:29:27');
INSERT INTO `sys_login_record` VALUES ('143', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 18:01:32');
INSERT INTO `sys_login_record` VALUES ('144', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 18:04:27');
INSERT INTO `sys_login_record` VALUES ('145', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 18:39:24');
INSERT INTO `sys_login_record` VALUES ('146', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 19:03:52');
INSERT INTO `sys_login_record` VALUES ('147', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 19:16:50');
INSERT INTO `sys_login_record` VALUES ('148', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 19:27:00');
INSERT INTO `sys_login_record` VALUES ('149', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 19:49:09');
INSERT INTO `sys_login_record` VALUES ('150', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 19:54:36');
INSERT INTO `sys_login_record` VALUES ('151', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-14 19:58:45');
INSERT INTO `sys_login_record` VALUES ('152', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-15 17:43:23');
INSERT INTO `sys_login_record` VALUES ('153', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-15 19:50:25');
INSERT INTO `sys_login_record` VALUES ('154', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-15 19:59:28');
INSERT INTO `sys_login_record` VALUES ('155', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-16 17:24:42');
INSERT INTO `sys_login_record` VALUES ('156', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-16 19:18:02');
INSERT INTO `sys_login_record` VALUES ('157', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-16 19:18:22');
INSERT INTO `sys_login_record` VALUES ('158', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 11:12:57');
INSERT INTO `sys_login_record` VALUES ('159', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 11:53:01');
INSERT INTO `sys_login_record` VALUES ('160', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 12:18:25');
INSERT INTO `sys_login_record` VALUES ('161', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 12:29:15');
INSERT INTO `sys_login_record` VALUES ('162', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 14:58:22');
INSERT INTO `sys_login_record` VALUES ('163', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 16:22:47');
INSERT INTO `sys_login_record` VALUES ('164', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 16:27:56');
INSERT INTO `sys_login_record` VALUES ('165', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 16:32:09');
INSERT INTO `sys_login_record` VALUES ('166', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 20:47:48');
INSERT INTO `sys_login_record` VALUES ('167', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-17 20:58:58');
INSERT INTO `sys_login_record` VALUES ('168', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 09:25:17');
INSERT INTO `sys_login_record` VALUES ('169', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 10:18:30');
INSERT INTO `sys_login_record` VALUES ('170', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 11:50:41');
INSERT INTO `sys_login_record` VALUES ('171', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 18:26:42');
INSERT INTO `sys_login_record` VALUES ('172', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 19:40:19');
INSERT INTO `sys_login_record` VALUES ('173', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 21:51:17');
INSERT INTO `sys_login_record` VALUES ('174', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 21:59:33');
INSERT INTO `sys_login_record` VALUES ('175', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 23:23:55');
INSERT INTO `sys_login_record` VALUES ('176', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 23:30:03');
INSERT INTO `sys_login_record` VALUES ('177', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 23:34:30');
INSERT INTO `sys_login_record` VALUES ('178', '6', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-18 23:56:26');
INSERT INTO `sys_login_record` VALUES ('179', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 00:05:12');
INSERT INTO `sys_login_record` VALUES ('180', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 00:06:33');
INSERT INTO `sys_login_record` VALUES ('181', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 00:29:09');
INSERT INTO `sys_login_record` VALUES ('182', '6', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 00:29:25');
INSERT INTO `sys_login_record` VALUES ('183', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 00:34:47');
INSERT INTO `sys_login_record` VALUES ('184', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 15:05:29');
INSERT INTO `sys_login_record` VALUES ('185', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 15:46:37');
INSERT INTO `sys_login_record` VALUES ('186', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 16:35:48');
INSERT INTO `sys_login_record` VALUES ('187', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 18:41:30');
INSERT INTO `sys_login_record` VALUES ('188', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 19:02:33');
INSERT INTO `sys_login_record` VALUES ('189', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 19:49:59');
INSERT INTO `sys_login_record` VALUES ('190', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-19 19:51:13');
INSERT INTO `sys_login_record` VALUES ('191', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-20 11:56:41');
INSERT INTO `sys_login_record` VALUES ('192', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-20 14:54:43');
INSERT INTO `sys_login_record` VALUES ('193', '2', 'Windows 10', 'Windows 10', 'Chrome', '127.0.0.1', '2019-06-20 16:50:33');
INSERT INTO `sys_login_record` VALUES ('194', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 12:16:01');
INSERT INTO `sys_login_record` VALUES ('195', '6', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 12:26:11');
INSERT INTO `sys_login_record` VALUES ('196', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 12:27:51');
INSERT INTO `sys_login_record` VALUES ('197', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 12:37:15');
INSERT INTO `sys_login_record` VALUES ('198', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 12:45:19');
INSERT INTO `sys_login_record` VALUES ('199', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 12:45:39');
INSERT INTO `sys_login_record` VALUES ('200', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 20:19:09');
INSERT INTO `sys_login_record` VALUES ('201', '6', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 20:30:49');
INSERT INTO `sys_login_record` VALUES ('202', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 20:43:26');
INSERT INTO `sys_login_record` VALUES ('203', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-21 21:12:44');
INSERT INTO `sys_login_record` VALUES ('204', '2', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-22 09:59:07');
INSERT INTO `sys_login_record` VALUES ('205', '6', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-22 10:02:03');
INSERT INTO `sys_login_record` VALUES ('206', '7', 'Windows 10', 'Windows 10', 'Chrome', '192.168.11.1', '2019-06-22 10:02:58');

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
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '超级管理员', '1', '2018-07-21 09:58:31', '2019-05-28 14:44:03');
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
) ENGINE=InnoDB AUTO_INCREMENT=1597 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色权限关联表';

-- ----------------------------
-- Records of sys_role_authorities
-- ----------------------------
INSERT INTO `sys_role_authorities` VALUES ('1531', '4', '39', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1532', '4', '40', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1533', '4', '74', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1534', '4', '76', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1535', '4', '77', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1536', '4', '78', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1537', '4', '91', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1538', '4', '47', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1539', '4', '73', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1540', '4', '1', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1541', '4', '79', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1542', '4', '82', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1543', '4', '2', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1544', '4', '3', '2019-06-18 18:28:46');
INSERT INTO `sys_role_authorities` VALUES ('1545', '2', '39', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1546', '2', '40', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1547', '2', '74', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1548', '2', '75', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1549', '2', '91', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1550', '2', '92', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1551', '2', '47', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1552', '2', '90', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1553', '2', '67', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1554', '2', '1', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1555', '2', '79', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1556', '2', '82', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1557', '2', '84', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1558', '2', '83', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1559', '2', '85', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1560', '2', '86', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1561', '2', '2', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1562', '2', '3', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1563', '2', '4', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1564', '2', '5', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1565', '2', '6', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1566', '2', '7', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1567', '2', '8', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1568', '2', '9', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1569', '2', '10', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1570', '2', '11', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1571', '2', '12', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1572', '2', '13', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1573', '2', '14', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1574', '2', '15', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1575', '2', '16', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1576', '2', '17', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1577', '2', '87', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1578', '2', '88', '2019-06-19 00:35:53');
INSERT INTO `sys_role_authorities` VALUES ('1579', '3', '39', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1580', '3', '40', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1581', '3', '74', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1582', '3', '75', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1583', '3', '76', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1584', '3', '91', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1585', '3', '92', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1586', '3', '47', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1587', '3', '73', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1588', '3', '89', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1589', '3', '1', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1590', '3', '79', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1591', '3', '82', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1592', '3', '2', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1593', '3', '3', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1594', '3', '4', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1595', '3', '5', '2019-06-19 00:35:57');
INSERT INTO `sys_role_authorities` VALUES ('1596', '3', '6', '2019-06-19 00:35:57');

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
  `college_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属学院id',
  `subject_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属专业id',
  `class_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属班级id',
  `grade_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属年级id',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0正常，1冻结',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'admin', '3fed7a346e430ea4c2aa10250928f4de', '管理员', null, '男', '12345678901', null, '1', '1', '0', '0', '2', '0', '2019-05-25 10:50:18', '2019-06-11 13:38:56');
INSERT INTO `sys_user` VALUES ('6', 'teacher', '629a1bf631c5ee1e898e1cd283e5c156', '人民教师', null, '女', '18849334210', null, '2', '1', '2', '0', '0', '0', '2019-05-25 15:30:49', '2019-06-17 11:26:40');
INSERT INTO `sys_user` VALUES ('7', 'student', '7b5470e577dc3c5750d1a9f49ae660a3', '张学生', null, '女', '18849334210', null, '3', '1', '2', '4', '1', '0', '2019-04-25 15:31:11', '2019-06-14 17:30:42');
INSERT INTO `sys_user` VALUES ('11', 'zhangsan', 'b51ae3e5279bc66eae7a4fe2600ab0b1', '张三', null, '男', '18849334210', null, '1', '0', '0', '0', '0', '0', '2019-05-24 20:30:06', '2019-06-17 20:57:53');
INSERT INTO `sys_user` VALUES ('33', '201542735', 'e0b84b77fe0d490d323bd5d263cf70b0', '张贵兴1', null, '男', '18849334210', null, '3', '1', '2', '4', '1', '0', '2019-05-25 19:52:30', '2019-06-11 13:45:43');
INSERT INTO `sys_user` VALUES ('34', '201542736', 'bfb02fa13e7cacd403db74fea3ae8f39', '张贵兴2', null, '女', '18849334210', null, '3', '1', '2', '4', '1', '0', '2019-05-25 19:52:30', '2019-05-25 19:52:30');
INSERT INTO `sys_user` VALUES ('35', '201542737', 'cf1343ff7d29535b81c1ac69fed8e66b', '张贵兴3', null, '男', '18849334210', null, '3', '1', '10', '11', '2', '0', '2019-05-25 19:52:31', '2019-05-25 19:52:31');
INSERT INTO `sys_user` VALUES ('36', '201542738', 'c2c9d73c1b7cd8854d6a1aff5ede3d9e', '张贵兴1', null, '男', '18849334210', null, '3', '1', '2', '3', '1', '0', '2019-05-25 20:03:46', '2019-05-25 20:03:46');
INSERT INTO `sys_user` VALUES ('37', '201542739', '3341c7985df3479e601642035963ec4c', '张贵兴2', null, '女', '18849334210', null, '3', '1', '2', '4', '1', '0', '2019-05-25 20:03:46', '2019-05-25 20:03:46');
INSERT INTO `sys_user` VALUES ('38', '201542740', 'ebe77ebc9075b6425ac5590114b5702d', '张贵兴3', null, '男', '18849334210', null, '3', '1', '10', '11', '2', '0', '2019-05-25 20:03:46', '2019-05-25 20:03:46');
INSERT INTO `sys_user` VALUES ('39', '001', 'bbf10d473efc508341d7736937569abf', '曹老师', null, '男', '18849334210', null, '2', '1', '2', '0', '0', '0', '2019-06-17 11:23:15', '2019-06-17 11:23:15');
INSERT INTO `sys_user` VALUES ('40', '002', '5fd6a54831258daacef1fa00f035ea29', '张老师', null, '男', '18849334210', null, '2', '1', '2', '0', '0', '0', '2019-06-17 11:23:46', '2019-06-17 11:23:46');
INSERT INTO `sys_user` VALUES ('41', '003', '7b282c7fc053532c60cfcdcbeb9a74dc', '通老师', null, '男', '18849334210', null, '2', '1', '2', '0', '0', '0', '2019-06-17 11:25:37', '2019-06-17 11:25:37');

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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('20', '11', '2', '2019-05-24 20:30:06');
INSERT INTO `sys_user_role` VALUES ('43', '34', '4', '2019-05-25 19:52:30');
INSERT INTO `sys_user_role` VALUES ('44', '35', '4', '2019-05-25 19:52:31');
INSERT INTO `sys_user_role` VALUES ('45', '36', '4', '2019-05-25 20:03:46');
INSERT INTO `sys_user_role` VALUES ('46', '37', '4', '2019-05-25 20:03:46');
INSERT INTO `sys_user_role` VALUES ('47', '38', '4', '2019-05-25 20:03:46');
INSERT INTO `sys_user_role` VALUES ('50', '2', '2', '2019-06-11 13:38:56');
INSERT INTO `sys_user_role` VALUES ('56', '33', '4', '2019-06-11 13:46:37');
INSERT INTO `sys_user_role` VALUES ('60', '7', '4', '2019-06-14 17:30:42');
INSERT INTO `sys_user_role` VALUES ('61', '39', '3', '2019-06-17 11:23:15');
INSERT INTO `sys_user_role` VALUES ('62', '40', '3', '2019-06-17 11:23:46');
INSERT INTO `sys_user_role` VALUES ('63', '41', '3', '2019-06-17 11:25:37');
INSERT INTO `sys_user_role` VALUES ('64', '6', '3', '2019-06-17 11:26:40');
SET FOREIGN_KEY_CHECKS=1;
