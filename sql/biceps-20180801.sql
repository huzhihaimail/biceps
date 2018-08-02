/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : biceps

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-02 12:06:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hr_interview
-- ----------------------------
DROP TABLE IF EXISTS `hr_interview`;
CREATE TABLE `hr_interview` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `education` varchar(255) DEFAULT NULL COMMENT '学历',
  `school` varchar(255) DEFAULT NULL COMMENT '毕业院校',
  `work_year` varchar(255) DEFAULT '' COMMENT '工作年限',
  `work_city` varchar(255) DEFAULT '' COMMENT '工作城市',
  `work_on_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最快到岗时间',
  `expected_salary` decimal(10,2) DEFAULT NULL COMMENT '期望薪水',
  `test_type` varchar(255) DEFAULT NULL COMMENT '面试类型（电话面试、到场面试）',
  `offer_flag` varchar(255) DEFAULT NULL COMMENT '是否收到offer',
  `recommend_flag` varchar(255) DEFAULT NULL COMMENT '是否推荐',
  `record_user` varchar(255) DEFAULT NULL COMMENT '登记人（默认为当前登录hr用户）',
  `delete_flag` int(1) DEFAULT NULL COMMENT '是否删除',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='面试人员信息登记表';

-- ----------------------------
-- Records of hr_interview
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(20) NOT NULL AUTO_INCREMENT,
  `parent_id` int(20) DEFAULT NULL COMMENT '上级ID',
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '0', '主题', 'theme', '1', '主题123');
INSERT INTO `sys_config` VALUES ('2', '1', '橙色主题', 'readable', '1', '主题颜色');
INSERT INTO `sys_config` VALUES ('5', '1', '黄色主题', 'yellow', '1', '黄色主题');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id ',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('7', 'adminregister_success1497938021409.rar', 'http://172.16.0.199:8080/single-hbf/upload/adminregister_success1497938021409.rar');
INSERT INTO `sys_file` VALUES ('8', 'adminexclamatory1497940769628.png', 'http://172.16.0.199:8080/single-hbf/upload/adminexclamatory1497940769628.png');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `logType` char(1) COLLATE utf8_bin DEFAULT '1' COMMENT '日志类型',
  `navigation` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '日志标题',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '创建时间',
  `host` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作IP地址',
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '请求URI',
  `requestType` varchar(5) COLLATE utf8_bin DEFAULT NULL COMMENT '操作方式',
  `params` text COLLATE utf8_bin COMMENT '操作提交的数据',
  `exceptionMessage` text COLLATE utf8_bin COMMENT '异常信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '菜单排序',
  `delete_flag` int(10) DEFAULT '0' COMMENT '是否删除',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, '0', 'fa fa-cog', '0', '0', null, null);
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', 'user.html', '1', 'fa fa-user', '1', '0', '2018-04-11 15:29:16', '2018-04-11 15:29:16');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'role.html', '1', 'fa fa-user-secret', '2', '0', '2018-04-11 15:28:33', '2018-04-11 15:28:33');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'menu.html', '1', 'fa fa-th-list', '3', '0', '2018-04-11 15:28:35', '2018-04-11 15:28:35');
INSERT INTO `sys_menu` VALUES ('5', '1', '定时调度', 'quartz.html', '1', 'fa fa-th-list', '4', '0', '2018-07-25 09:36:49', '2018-07-25 09:36:51');
INSERT INTO `sys_menu` VALUES ('6', '1', '参数管理', 'config.html', '1', 'fa fa-cog', '5', '0', '2018-07-25 09:47:57', '2018-07-25 09:48:20');
INSERT INTO `sys_menu` VALUES ('7', '1', '日志管理', 'log.html', '1', 'fa fa-cog', '6', '0', '2018-07-25 09:48:38', '2018-07-25 09:48:46');
INSERT INTO `sys_menu` VALUES ('8', '1', '面试管理', '../hr/interview.html', '1', 'fa fa-cog', '7', '0', '2018-08-01 23:19:48', '2018-08-01 23:54:09');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` int(11) NOT NULL COMMENT '部门ID',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('1', '人力资源部', '0');

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `job_id` varchar(64) NOT NULL,
  `job_name` varchar(128) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(128) DEFAULT NULL COMMENT '任务分组',
  `job_status` varchar(128) DEFAULT NULL COMMENT '任务状态:(0：停用，1：启用)',
  `cron_expression` varchar(128) NOT NULL COMMENT 'cron表达式',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `bean_class` varchar(128) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `is_concurrent` varchar(128) DEFAULT NULL COMMENT '任务是否可以并行运行（0：不可以，1：可以）',
  `spring_id` varchar(128) DEFAULT NULL COMMENT 'spring bean',
  `method_name` varchar(128) NOT NULL COMMENT '任务调用的方法名',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_group` (`job_name`,`job_group`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES ('19', 'f93b8313-0039-4825-b137-5268178648cf', 'IT上报数据查询（默认查询昨天的数据）', 'ItReport', '1', '0 0 7 * * ?', '每天凌晨7点执行一次', null, '1', 'itReportTask', 'run', '2018-02-08 16:36:54', '2018-02-27 14:34:54');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `name_cn` varchar(100) DEFAULT NULL COMMENT '角色中文名称',
  `status` int(1) DEFAULT '0' COMMENT '是否删除',
  `delete_flag` varchar(1) DEFAULT NULL COMMENT '删除状态（0未删除；1已删除）',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin角色', '管理员', '0', '0', '2017-02-21 10:41:44', '2018-07-22 23:00:32');
INSERT INTO `sys_role` VALUES ('2', 'HR角色', '人力资源角色', '0', '0', '2018-08-01 23:48:26', '2018-08-01 23:48:47');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(20) DEFAULT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('2', '8');
INSERT INTO `sys_role_menu` VALUES ('2', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `nick_name` varchar(50) DEFAULT '' COMMENT '昵称',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(255) NOT NULL COMMENT '密码盐',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `mobile` varchar(100) NOT NULL COMMENT '手机号',
  `status` int(4) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `delete_flag` int(1) DEFAULT '0' COMMENT '是否删除',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '53c8cb0fe3b22b2aaa2a90c019593622', 'e372cb', 'root@renren.io', '13612345678', '1', '0', '2016-11-11 11:11:11', '2018-04-11 14:53:27');
INSERT INTO `sys_user` VALUES ('14', 'huyang', '胡大阳', '93cea367f7a9557a95bc33fe8c2468c7', 'c34e0e', 'huzhihaimail@163.com', '15295577923', '1', '0', '2018-08-01 23:52:49', '2018-08-01 23:52:49');

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `org_id` int(11) DEFAULT NULL COMMENT '部门ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------
INSERT INTO `sys_user_org` VALUES ('2', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('14', '2');
