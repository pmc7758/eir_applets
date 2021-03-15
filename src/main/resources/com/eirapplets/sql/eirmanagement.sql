/*
 Navicat Premium Data Transfer

 Source Server         : DESKTOP-O165SR5
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : eirmanagement

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 15/03/2021 22:10:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES (1, 'sys:y:search', '查看所有学生的上报信息');
INSERT INTO `s_permission` VALUES (2, 'sys:y:delete', '删除某一位同学的上报信息');
INSERT INTO `s_permission` VALUES (3, 'sys:y:search', '查询某一个同学的上报信息');
INSERT INTO `s_permission` VALUES (4, 'sys:y:modify', '修改某一位同学的上报信息');
INSERT INTO `s_permission` VALUES (5, 'sys:x:qeury', '查看自己的上报信息');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES (1, 'Root');
INSERT INTO `s_role` VALUES (2, '老师');
INSERT INTO `s_role` VALUES (3, '学生');

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission`  (
  `roleid` int(0) NULL DEFAULT NULL,
  `permissionid` int(0) NULL DEFAULT NULL,
  INDEX `roleid`(`roleid`) USING BTREE,
  INDEX `permissionid`(`permissionid`) USING BTREE,
  CONSTRAINT `s_role_permission_ibfk_1` FOREIGN KEY (`roleid`) REFERENCES `s_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_role_permission_ibfk_2` FOREIGN KEY (`permissionid`) REFERENCES `s_permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES (1, 1);
INSERT INTO `s_role_permission` VALUES (1, 2);
INSERT INTO `s_role_permission` VALUES (1, 3);
INSERT INTO `s_role_permission` VALUES (1, 4);
INSERT INTO `s_role_permission` VALUES (2, 1);
INSERT INTO `s_role_permission` VALUES (2, 2);
INSERT INTO `s_role_permission` VALUES (2, 3);
INSERT INTO `s_role_permission` VALUES (2, 4);
INSERT INTO `s_role_permission` VALUES (3, 5);

-- ----------------------------
-- Table structure for s_temperature
-- ----------------------------
DROP TABLE IF EXISTS `s_temperature`;
CREATE TABLE `s_temperature`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `datetime` datetime(0) NULL DEFAULT NULL,
  `userid` int(0) NULL DEFAULT NULL,
  `temperature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `s_temperature_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `s_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_temperature
-- ----------------------------

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES (30, '卢贤涛', '18003004', '123456782', '1800300421', '17607750053');
INSERT INTO `s_user` VALUES (31, '庞坚', '18003004', '60ffe97e10dbeb5f03f8dfc4a53ac466', '1800300425', '17607750063');
INSERT INTO `s_user` VALUES (32, '潘林炎', '1800300425', 'e10adc3949ba59abbe56e057f20f883e', '1800300424', '13687750063');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role`  (
  `userid` int(0) NOT NULL,
  `roleid` int(0) NOT NULL,
  INDEX `userid`(`userid`) USING BTREE,
  INDEX `roleid`(`roleid`) USING BTREE,
  CONSTRAINT `s_user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `s_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_user_role_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `s_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES (30, 2);
INSERT INTO `s_user_role` VALUES (31, 2);
INSERT INTO `s_user_role` VALUES (32, 3);

SET FOREIGN_KEY_CHECKS = 1;
