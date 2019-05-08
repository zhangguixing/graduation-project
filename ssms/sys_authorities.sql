/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : ssms

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-05-08 10:54:16
*/

SET FOREIGN_KEY_CHECKS=0;

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
SET FOREIGN_KEY_CHECKS=1;
