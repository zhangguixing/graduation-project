/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : ssms

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-04-26 14:37:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for info_college_subject_class
-- ----------------------------
DROP TABLE IF EXISTS `info_college_subject_class`;
CREATE TABLE `info_college_subject_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学院/专业/班级id',
  `name` varchar(20) NOT NULL COMMENT '学院/专业/班级名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父id，值为0表示学院，否则表示父id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_college_subject_class
-- ----------------------------

-- ----------------------------
-- Table structure for info_course
-- ----------------------------
DROP TABLE IF EXISTS `info_course`;
CREATE TABLE `info_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `name` varchar(20) NOT NULL COMMENT '课程名称',
  `class_time` varchar(255) DEFAULT NULL COMMENT '上课时间json串',
  `room_id` int(11) NOT NULL DEFAULT '0' COMMENT '教室id',
  `teacher_id` int(11) NOT NULL COMMENT '教师id',
  `teacher_name` varchar(20) DEFAULT NULL COMMENT '教师名称',
  `college_id` int(11) NOT NULL COMMENT '所属学院id',
  `subject_id` int(11) NOT NULL COMMENT '所属专业id',
  `class_id` int(11) NOT NULL COMMENT '所属班级id',
  `grade_id` int(11) NOT NULL COMMENT '所属年级id',
  `school_year` varchar(10) NOT NULL COMMENT '学年，2019-2020',
  `semester` tinyint(1) NOT NULL COMMENT '学期，1上学期，2下学期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_course
-- ----------------------------

-- ----------------------------
-- Table structure for info_grade
-- ----------------------------
DROP TABLE IF EXISTS `info_grade`;
CREATE TABLE `info_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '年级id',
  `name` varchar(20) NOT NULL COMMENT '年级名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_grade
-- ----------------------------

-- ----------------------------
-- Table structure for info_room
-- ----------------------------
DROP TABLE IF EXISTS `info_room`;
CREATE TABLE `info_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教室id',
  `name` varchar(20) NOT NULL COMMENT '教室名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_room
-- ----------------------------

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
  `semester` tinyint(1) NOT NULL COMMENT '学期，1上学期，2下学期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态，0删除，1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info_score
-- ----------------------------

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
  `menu_icon` varchar(20) DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of sys_authorities
-- ----------------------------
INSERT INTO `sys_authorities` VALUES ('1', '系统管理', '', '', '-1', '0', '4', 'layui-icon-set', '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('2', '用户管理', null, 'system/user', '1', '0', '2', null, '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('3', '查询用户', 'user:view', '', '2', '1', '3', '', '2018-07-21 13:54:16', '2018-07-21 13:54:16');
INSERT INTO `sys_authorities` VALUES ('4', '添加用户', 'user:add', null, '2', '1', '4', null, '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('5', '修改用户', 'user:edit', null, '2', '1', '5', null, '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('6', '删除用户', 'user:delete', null, '2', '1', '6', null, '2018-06-29 11:05:41', '2018-07-13 09:13:42');
INSERT INTO `sys_authorities` VALUES ('7', '角色管理', null, 'system/role', '1', '0', '7', null, '2018-06-29 11:05:41', '2018-07-13 09:13:42');
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
INSERT INTO `sys_authorities` VALUES ('39', '学校信息', '', '', '-1', '0', '1', 'layui-icon-console', '2018-10-29 11:12:08', '2018-10-29 11:12:08');
INSERT INTO `sys_authorities` VALUES ('40', '平台简介', '', 'system/platform', '39', '0', '1', '', '2018-10-29 11:13:15', '2018-10-29 11:13:15');
INSERT INTO `sys_authorities` VALUES ('47', '数据搬迁', '', '', '-1', '0', '3', 'layui-icon-release', '2018-10-29 11:21:30', '2018-10-29 11:21:30');
INSERT INTO `sys_authorities` VALUES ('50', '其他页（参考）', '', '', '-1', '0', '104', 'layui-icon-table', '2018-11-20 13:48:40', '2018-11-20 13:48:40');
INSERT INTO `sys_authorities` VALUES ('51', '下拉菜单', '', '/other/dropdown', '50', '0', '1', '', '2018-11-20 14:08:59', '2018-11-20 14:08:59');
INSERT INTO `sys_authorities` VALUES ('52', '通知消息', '', '/other/notice', '50', '0', '2', '', '2018-11-20 14:09:28', '2018-11-20 14:09:28');
INSERT INTO `sys_authorities` VALUES ('53', '风格弹窗', '', '/other/dialog', '50', '0', '3', '', '2018-11-20 14:09:58', '2018-11-20 14:09:58');
INSERT INTO `sys_authorities` VALUES ('67', '调度管理', '', 'migration/dispatch', '47', '0', '3', '', '2019-03-15 13:57:16', '2019-03-15 13:57:16');

-- ----------------------------
-- Table structure for sys_information
-- ----------------------------
DROP TABLE IF EXISTS `sys_information`;
CREATE TABLE `sys_information` (
  `id` int(11) NOT NULL COMMENT '系统信息id',
  `message` varchar(50) DEFAULT NULL COMMENT '系统欢迎信息',
  `school_name` varchar(20) DEFAULT NULL COMMENT '学校名称',
  `developer_name` varchar(20) DEFAULT NULL COMMENT '系统开发者',
  `information` text COMMENT '系统简介',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_information
-- ----------------------------

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `content` text COMMENT '公告内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
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
) ENGINE=InnoDB AUTO_INCREMENT=991 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色权限关联表';

-- ----------------------------
-- Records of sys_role_authorities
-- ----------------------------
INSERT INTO `sys_role_authorities` VALUES ('141', '1', '1', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('142', '1', '2', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('143', '1', '3', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('144', '1', '4', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('145', '1', '5', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('146', '1', '6', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('147', '1', '7', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('148', '1', '8', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('149', '1', '9', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('150', '1', '10', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('151', '1', '11', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('152', '1', '12', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('153', '1', '13', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('154', '1', '14', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('155', '1', '15', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('156', '1', '16', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('157', '1', '17', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('160', '1', '27', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('161', '1', '30', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('162', '1', '28', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('163', '1', '29', '2018-10-27 15:31:55');
INSERT INTO `sys_role_authorities` VALUES ('626', '3', '39', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('627', '3', '40', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('634', '3', '47', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('636', '3', '1', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('638', '3', '2', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('639', '3', '3', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('640', '3', '7', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('641', '3', '8', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('642', '3', '13', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('643', '3', '14', '2019-03-01 17:01:05');
INSERT INTO `sys_role_authorities` VALUES ('951', '2', '39', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('952', '2', '40', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('953', '2', '47', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('954', '2', '67', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('955', '2', '1', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('956', '2', '2', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('957', '2', '3', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('958', '2', '4', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('959', '2', '5', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('960', '2', '6', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('961', '2', '7', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('962', '2', '8', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('963', '2', '9', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('964', '2', '10', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('965', '2', '11', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('966', '2', '12', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('967', '2', '13', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('968', '2', '14', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('969', '2', '15', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('970', '2', '16', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('971', '2', '17', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('972', '2', '31', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('973', '2', '32', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('974', '2', '33', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('975', '2', '34', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('976', '2', '35', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('977', '2', '36', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('978', '2', '37', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('979', '2', '38', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('980', '2', '50', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('981', '2', '51', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('982', '2', '52', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('983', '2', '53', '2019-04-25 14:08:58');
INSERT INTO `sys_role_authorities` VALUES ('984', '4', '39', '2019-04-25 14:26:52');
INSERT INTO `sys_role_authorities` VALUES ('985', '4', '40', '2019-04-25 14:26:52');
INSERT INTO `sys_role_authorities` VALUES ('986', '4', '47', '2019-04-25 14:26:52');
INSERT INTO `sys_role_authorities` VALUES ('987', '4', '67', '2019-04-25 14:26:52');
INSERT INTO `sys_role_authorities` VALUES ('988', '4', '1', '2019-04-25 14:26:52');
INSERT INTO `sys_role_authorities` VALUES ('989', '4', '2', '2019-04-25 14:26:52');
INSERT INTO `sys_role_authorities` VALUES ('990', '4', '3', '2019-04-25 14:26:52');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'superadmin', '2d4d426bb63055bfdc17bed0d10792b7', '超级管理员', null, '男', '12345678901', null, null, '0', '0', '0', '0', '0', '2018-07-21 10:03:40', '2019-02-21 18:58:05');
INSERT INTO `sys_user` VALUES ('2', 'admin', '3fed7a346e430ea4c2aa10250928f4de', '管理员', null, '男', '12345678901', null, null, '0', '0', '0', '0', '0', '2018-07-21 10:50:18', '2018-07-21 10:55:05');
INSERT INTO `sys_user` VALUES ('6', 'teacher', '629a1bf631c5ee1e898e1cd283e5c156', '教师账号', null, '女', '18849334210', null, null, '0', '0', '0', '0', '0', '2019-04-25 15:30:49', '2019-04-25 15:30:49');
INSERT INTO `sys_user` VALUES ('7', 'student', '7b5470e577dc3c5750d1a9f49ae660a3', '学生账号', null, '男', '18849334210', null, null, '0', '0', '0', '0', '0', '2019-04-25 15:31:11', '2019-04-25 15:31:11');

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
INSERT INTO `sys_user_role` VALUES ('10', '7', '4', '2019-04-25 15:31:11');
SET FOREIGN_KEY_CHECKS=1;
