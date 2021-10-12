/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : employee

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 09/10/2021 18:12:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_info
-- ----------------------------
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `account_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `is_enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1-启用，0-禁用',
  `gmt_last_login` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次登录时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标志：0未删除1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登入信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_info
-- ----------------------------
INSERT INTO `account_info` VALUES (1, '18258243552', '$2a$10$HM9YUgLzQ5/yOycGhonwP.NqeS0adL1REdfcXxB8uo8rAW3xc3Xq6', 1, '2021-09-13 18:25:10', 1, '2021-09-13 18:25:10', NULL, '2021-09-13 18:25:10', 0);
INSERT INTO `account_info` VALUES (1441288520394727425, '18253632222', '$2a$10$9UIlCeH9d9Ib3GwnDeQPBOL1mjloys5pq2lBWaQTl9LXbYc5SYyvS', 1, '2021-09-24 14:28:55', 1, '2021-09-24 14:28:56', 1, '2021-09-24 14:28:56', 0);
INSERT INTO `account_info` VALUES (1441289344780898305, '18253632223', '$2a$10$f02ZWKiVQAhcf2HCzSMZ9.IcjAj1jgJ5.ob9capcI6x0dNKvkZAhC', 1, '2021-09-24 14:32:12', 1, '2021-09-24 14:32:12', 1, '2021-09-24 14:32:12', 0);

-- ----------------------------
-- Table structure for settlement_listing
-- ----------------------------
DROP TABLE IF EXISTS `settlement_listing`;
CREATE TABLE `settlement_listing`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `address` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务地址',
  `plan_date` datetime(0) NOT NULL COMMENT '完成日期',
  `gain_by` bigint(20) NULL DEFAULT NULL COMMENT '获取人',
  `settlement_by` bigint(20) NULL DEFAULT NULL COMMENT '结算人',
  `is_settlement` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否结算：0未结算 1已结算',
  `create_by` bigint(20) NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标志：0未删除 1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '结算清单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of settlement_listing
-- ----------------------------
INSERT INTO `settlement_listing` VALUES (1, '杭州市', '2021-10-08 16:33:34', 1441288520671551489, 1441289346446036994, 0, 1, '2021-10-08 16:34:04', NULL, '2021-10-08 16:34:04', 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `name_cn` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '中文名',
  `name_en` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文名',
  `job_number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号',
  `gender` int(11) NULL DEFAULT NULL COMMENT '性别：0未知；1男；2女',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `mailbox` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `account_id` bigint(20) NULL DEFAULT NULL COMMENT '账户id',
  `create_by` bigint(20) NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标志：0未删除1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `accoutId_unique`(`account_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '00000000', 1, '//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg', '18258243552', NULL, 1, 0, '2021-09-13 18:24:43', NULL, '2021-09-13 18:24:43', 0);
INSERT INTO `sys_user` VALUES (1441288520671551489, '陈款型', NULL, NULL, NULL, '//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg', '18253632222', NULL, 1441288520394727425, 1, '2021-09-24 14:28:56', 1, '2021-09-24 14:28:56', 0);
INSERT INTO `sys_user` VALUES (1441289346446036994, '陈款型1', NULL, NULL, NULL, '//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg', '18253632223', NULL, 1441289344780898305, 1, '2021-09-24 14:32:13', 1, '2021-09-24 14:32:13', 0);

SET FOREIGN_KEY_CHECKS = 1;
