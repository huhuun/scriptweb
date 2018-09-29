/*
 Navicat Premium Data Transfer

 Source Server         : script
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : script

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 30/09/2018 00:41:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client_info
-- ----------------------------
DROP TABLE IF EXISTS `client_info`;
CREATE TABLE `client_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for client_script
-- ----------------------------
DROP TABLE IF EXISTS `client_script`;
CREATE TABLE `client_script`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `script_id` int(11) NULL DEFAULT NULL,
  `client_id` int(11) NULL DEFAULT NULL,
  `expire_date` int(11) NULL DEFAULT NULL,
  `active_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for script_info
-- ----------------------------
DROP TABLE IF EXISTS `script_info`;
CREATE TABLE `script_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `script_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `detail` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100012 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of script_info
-- ----------------------------
INSERT INTO `script_info` VALUES (99999, '百度脚本', '很厉害的百度脚本', 'http://baidu.com', '2018-09-19 22:45:15', '略');
INSERT INTO `script_info` VALUES (100000, 'QQ脚本', '牛x', 'http://qq.com', '2018-09-20 22:29:05', 'tentent is best');
INSERT INTO `script_info` VALUES (100001, 'QQ脚本1', '牛x', 'http://qq.com', '2018-09-20 22:29:14', 'tentent is best');
INSERT INTO `script_info` VALUES (100002, 'QQ脚本2', '牛x', 'http://qq.com', '2018-09-20 22:29:43', 'tentent is best');
INSERT INTO `script_info` VALUES (100003, 'QQ脚本3', '牛x', 'http://qq.com', '2018-09-20 22:29:47', 'tentent is best');
INSERT INTO `script_info` VALUES (100004, 'QQ脚本4', '牛x', 'http://qq.com', '2018-09-20 22:29:53', 'tentent is best');
INSERT INTO `script_info` VALUES (100005, 'QQ脚本5', '牛x', 'http://qq.com', '2018-09-20 22:29:57', 'tentent is best');
INSERT INTO `script_info` VALUES (100006, 'QQ脚本6', '牛x', 'http://qq.com', '2018-09-20 22:30:00', 'tentent is best');
INSERT INTO `script_info` VALUES (100007, 'QQ脚本7', '牛x', 'http://qq.com', '2018-09-20 22:30:10', 'tentent is best');
INSERT INTO `script_info` VALUES (100008, 'QQ脚本8', '牛x', 'http://qq.com', '2018-09-20 22:30:14', 'tentent is best');
INSERT INTO `script_info` VALUES (100009, 'QQ脚本9', '牛x', 'http://qq.com', '2018-09-20 22:30:18', 'tentent is best');
INSERT INTO `script_info` VALUES (100010, 'QQ脚本10', '牛x', 'http://qq.com', '2018-09-20 22:30:23', 'tentent is best');
INSERT INTO `script_info` VALUES (100011, 'QQ脚本11', '牛x', 'http://qq.com', '2018-09-20 22:30:27', 'tentent is best');

SET FOREIGN_KEY_CHECKS = 1;
