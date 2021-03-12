/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 12/03/2021 17:12:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_permission
-- ----------------------------
DROP TABLE IF EXISTS `blog_permission`;
CREATE TABLE `blog_permission`
(
    `id`                     bigint(20)                                              NOT NULL AUTO_INCREMENT,
    `permission_code`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '权限标识符',
    `permission_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '权限描述',
    `permission_url`         varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限请求地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_permission
-- ----------------------------
INSERT INTO `blog_permission`
VALUES (1, 'query_user', '查询用户权限', '/user/query');
INSERT INTO `blog_permission`
VALUES (2, 'modify_user', '修改用户权限', '/user/modify');
INSERT INTO `blog_permission`
VALUES (3, 'QQ', '测试', '/api/p1');
INSERT INTO `blog_permission`
VALUES (4, 'ww', '测试', '/api/p2');
INSERT INTO `blog_permission`
VALUES (5, 'EE', '测试', '/api/p3');
INSERT INTO `blog_permission`
VALUES (6, 'QQ', NULL, '/api/p4');
INSERT INTO `blog_permission`
VALUES (7, 'WW', NULL, '/api/p4');
INSERT INTO `blog_permission`
VALUES (8, 'EE', NULL, '/api/p4');

-- ----------------------------
-- Table structure for blog_role
-- ----------------------------
DROP TABLE IF EXISTS `blog_role`;
CREATE TABLE `blog_role`
(
    `id`               bigint(20)                                              NOT NULL AUTO_INCREMENT,
    `role_code`        varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '角色标识符',
    `role_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
    `role_create_time` datetime(0)                                             NULL DEFAULT NULL,
    `role_update_time` datetime(0)                                             NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_role
-- ----------------------------
INSERT INTO `blog_role`
VALUES (1, 'admin', '系统管理员，拥有所有权限', '2020-04-03 00:00:00', '2020-09-04 00:00:00');
INSERT INTO `blog_role`
VALUES (2, 'user', '普通用户,拥有部分权限', '2020-04-03 00:00:00', '2020-09-04 00:00:00');

-- ----------------------------
-- Table structure for blog_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `blog_role_permission_relation`;
CREATE TABLE `blog_role_permission_relation`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id`       bigint(20) NOT NULL,
    `permission_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_role_permission_relation
-- ----------------------------
INSERT INTO `blog_role_permission_relation`
VALUES (1, 1, 1);
INSERT INTO `blog_role_permission_relation`
VALUES (2, 1, 2);
INSERT INTO `blog_role_permission_relation`
VALUES (3, 1, 3);
INSERT INTO `blog_role_permission_relation`
VALUES (4, 1, 4);
INSERT INTO `blog_role_permission_relation`
VALUES (5, 2, 3);
INSERT INTO `blog_role_permission_relation`
VALUES (6, 3, 5);
INSERT INTO `blog_role_permission_relation`
VALUES (7, 3, 4);
INSERT INTO `blog_role_permission_relation`
VALUES (8, 2, 4);

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user`
(
    `id`              bigint(20)                                             NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `user_name`       varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
    `user_password`   varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
    `user_mobile`     varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
    `user_creat_time` datetime(0)                                            NULL DEFAULT '2000-01-01 00:00:00' COMMENT '用户创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_name` (`user_name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user`
VALUES (1, 'zhangsan', '$2a$10$qLTaSkK2o.ym5.mYac4L2Ow4Uf0VXekijXQ2ioxSyfUkeCCLxxNW2', '18237701342',
        '2020-01-01 05:45:33');
INSERT INTO `blog_user`
VALUES (2, 'lisi', '$2a$10$qLTaSkK2o.ym5.mYac4L2Ow4Uf0VXekijXQ2ioxSyfUkeCCLxxNW2', '1232142154', '2000-01-01 00:00:00');
INSERT INTO `blog_user`
VALUES (3, 'wangwu', '$2a$10$qLTaSkK2o.ym5.mYac4L2Ow4Uf0VXekijXQ2ioxSyfUkeCCLxxNW2', '1233124215',
        '2000-01-01 00:00:00');

-- ----------------------------
-- Table structure for blog_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `blog_user_role_relation`;
CREATE TABLE `blog_user_role_relation`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `user_id`     bigint(20)  NOT NULL,
    `role_id`     bigint(20)  NOT NULL,
    `create_time` datetime(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_user_role_relation
-- ----------------------------
INSERT INTO `blog_user_role_relation`
VALUES (1, 1, 1, '2020-04-03 00:00:00');
INSERT INTO `blog_user_role_relation`
VALUES (2, 1, 2, '2020-04-05 00:00:00');
INSERT INTO `blog_user_role_relation`
VALUES (3, 2, 2, NULL);
INSERT INTO `blog_user_role_relation`
VALUES (4, 3, 2, NULL);

SET FOREIGN_KEY_CHECKS = 1;
