/*
 Navicat Premium Data Transfer

 Source Server         : shige-kkb
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 8.140.125.207:3306
 Source Schema         : kkb

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 08/05/2021 22:09:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`  (
                           `revision` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
                           `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
                           `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
                           `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                           `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '委托方ID',
                           `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '委托方名',
                           `head_image_uri` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '委托方头像URI',
                           `status` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '委托方状态 是否通过认证等',
                           `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已经删除',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE INDEX `uk_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '委托方表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for leader_comment
-- ----------------------------
DROP TABLE IF EXISTS `leader_comment`;
CREATE TABLE `leader_comment`  (
                                   `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                   `leader_id` bigint(20) UNSIGNED NOT NULL COMMENT '导师ID',
                                   `project_id` bigint(20) UNSIGNED NOT NULL COMMENT '项目ID',
                                   `comment` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论',
                                   `comment_from` bigint(20) UNSIGNED NOT NULL COMMENT '评论人id',
                                   `is_anonymous` tinyint(3) UNSIGNED NOT NULL COMMENT '是否匿名',
                                   `professional` tinyint(3) UNSIGNED NOT NULL COMMENT '专业能力 等级1-5',
                                   `organizational` tinyint(3) UNSIGNED NOT NULL COMMENT '组织能力 等级1-5',
                                   `communication` tinyint(3) UNSIGNED NOT NULL COMMENT '沟通能力 等级1-5',
                                   `avg_score` decimal(32, 10) UNSIGNED NOT NULL COMMENT '能力平均分数',
                                   `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                   `revision` int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   INDEX `idx_leaderId`(`leader_id`) USING BTREE,
                                   INDEX `idx_projectId_and_leaderId`(`project_id`, `leader_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '导师评论表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for leader_guidance
-- ----------------------------
DROP TABLE IF EXISTS `leader_guidance`;
CREATE TABLE `leader_guidance`  (
                                    `revision` int(11) NOT NULL COMMENT '乐观锁',
                                    `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
                                    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接受导师报名的管理员',
                                    `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '指导ID',
                                    `leader_id` bigint(20) UNSIGNED NOT NULL COMMENT '导师ID',
                                    `project_id` bigint(20) UNSIGNED NOT NULL COMMENT '项目ID',
                                    `project_status` tinyint(3) UNSIGNED NOT NULL COMMENT '项目状态',
                                    `is_deleted` tinyint(3) UNSIGNED NOT NULL COMMENT '是否已经删除',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE INDEX `uk_leader_id`(`leader_id`, `project_id`) USING BTREE,
                                    INDEX `uk_project_id`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '导师指导表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for leader_signup
-- ----------------------------
DROP TABLE IF EXISTS `leader_signup`;
CREATE TABLE `leader_signup`  (
                                  `revision` int(11) NOT NULL COMMENT '乐观锁',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人-接受或拒绝导师报名的管理员',
                                  `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '导师报名ID',
                                  `leader_id` bigint(20) UNSIGNED NOT NULL COMMENT '导师ID',
                                  `project_id` bigint(20) UNSIGNED NOT NULL COMMENT '项目ID',
                                  `status` tinyint(3) UNSIGNED NOT NULL COMMENT '导师报名状态 utiny int业务层解释',
                                  `is_deleted` tinyint(3) UNSIGNED NOT NULL COMMENT '是否已经删除',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE INDEX `uk_leader_id_and_project_id`(`leader_id`, `project_id`) USING BTREE,
                                  INDEX `idx_project_id`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '导师报名表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for participant_partake
-- ----------------------------
DROP TABLE IF EXISTS `participant_partake`;
CREATE TABLE `participant_partake`  (
                                        `revision` int(11) NOT NULL COMMENT '乐观锁',
                                        `create_user` bigint(20) NOT NULL COMMENT '创建人ID pick该学生的导师id',
                                        `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `update_user` bigint(20) UNSIGNED NOT NULL COMMENT '接受或拒绝该学生报名的导师id',
                                        `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                        `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '参与ID',
                                        `participant_id` bigint(20) UNSIGNED NOT NULL COMMENT '参与者ID',
                                        `project_id` bigint(20) UNSIGNED NOT NULL COMMENT '项目ID',
                                        `project_status` tinyint(3) UNSIGNED NOT NULL COMMENT '项目状态 冗余字段',
                                        `is_deleted` tinyint(3) UNSIGNED NOT NULL COMMENT '是否已经删除',
                                        PRIMARY KEY (`id`) USING BTREE,
                                        UNIQUE INDEX `uk_participant_id`(`participant_id`, `project_id`) USING BTREE,
                                        INDEX `uk_project_id`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参与者参与表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for participant_signup
-- ----------------------------
DROP TABLE IF EXISTS `participant_signup`;
CREATE TABLE `participant_signup`  (
                                       `revision` int(11) NOT NULL COMMENT '乐观锁',
                                       `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `update_user` bigint(32) UNSIGNED NOT NULL COMMENT '接受或者拒绝该学生报名的导师id',
                                       `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                       `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '参与者报名ID',
                                       `project_id` bigint(20) UNSIGNED NOT NULL COMMENT '项目ID',
                                       `status` tinyint(3) UNSIGNED NOT NULL COMMENT '参与者报名状态 utiny int在业务层解释',
                                       `is_deleted` tinyint(3) UNSIGNED NOT NULL COMMENT '是否已经删除',
                                       `participant_id` bigint(20) UNSIGNED NOT NULL COMMENT '参与者id',
                                       PRIMARY KEY (`id`) USING BTREE,
                                       UNIQUE INDEX `uk_participant_id`(`participant_id`, `project_id`) USING BTREE,
                                       INDEX `idx_project_id`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参与者报名表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
                            `revision` int(11) NOT NULL COMMENT '乐观锁',
                            `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
                            `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
                            `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                            `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '项目ID',
                            `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名',
                            `signup_leader_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '报名导师人数',
                            `signup_participant_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '报名参与者人数',
                            `client_id` bigint(20) UNSIGNED NOT NULL COMMENT '委托方ID',
                            `publish_datetime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项目发布时间',
                            `signup_deadline` datetime(0) NOT NULL COMMENT '项目报名截至日期',
                            `demand_description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目需求描述 varchar512',
                            `work_style_id` bigint(20) UNSIGNED NOT NULL COMMENT '项目工作方式 utiny int业务层解释',
                            `predicted_duration` int(11) UNSIGNED NOT NULL COMMENT '项目预期时长 表示天数',
                            `start_datetime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '项目开始时间',
                            `status` tinyint(3) UNSIGNED NOT NULL COMMENT '项目状态 utiny int 业务层解释审核中/等',
                            `is_deleted` tinyint(3) UNSIGNED NOT NULL COMMENT '是否已经删除',
                            `type_id` bigint(20) UNSIGNED NOT NULL COMMENT '项目类型',
                            `tags` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目tags',
                            `partake_participant_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '参加的学员数',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `pk_project_id`(`id`) USING BTREE,
                            INDEX `idx_signup_deadline`(`signup_deadline`) USING BTREE,
                            INDEX `idx_type`(`type_id`) USING BTREE,
                            INDEX `idx_publish_time`(`publish_datetime`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_work_style
-- ----------------------------
DROP TABLE IF EXISTS `project_work_style`;
CREATE TABLE `project_work_style`  (
                                       `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '工作方式ID',
                                       `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作方式名称',
                                       `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                       `revision` int(10) NOT NULL COMMENT '乐观锁',
                                       `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
                                       `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
                                       `is_deleted` tinyint(3) NOT NULL COMMENT '软删除',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project_work_type
-- ----------------------------
DROP TABLE IF EXISTS `project_work_type`;
CREATE TABLE `project_work_type`  (
                                      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '项目类型ID',
                                      `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目类型名称',
                                      `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `revision` int(10) NOT NULL COMMENT '乐观锁',
                                      `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
                                      `update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改人',
                                      `is_deleted` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '软删除',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_comment
-- ----------------------------
DROP TABLE IF EXISTS `student_comment`;
CREATE TABLE `student_comment`  (
                                    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                    `student_id` bigint(20) UNSIGNED NOT NULL COMMENT '学生ID',
                                    `project_id` bigint(20) UNSIGNED NOT NULL COMMENT '项目ID',
                                    `comment` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论',
                                    `comment_from` bigint(20) UNSIGNED NOT NULL COMMENT '评论人id',
                                    `is_anonymous` tinyint(3) UNSIGNED NOT NULL COMMENT '是否匿名',
                                    `professional` tinyint(3) UNSIGNED NOT NULL COMMENT '专业能力 等级1-5',
                                    `operational` tinyint(3) UNSIGNED NOT NULL COMMENT '操作能力 等级1-5',
                                    `understand` tinyint(3) UNSIGNED NOT NULL COMMENT '理解能力 等级1-5',
                                    `avg_score` decimal(32, 10) NOT NULL COMMENT '能力平均分数',
                                    `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                    `revision` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `idx_studentId`(`student_id`) USING BTREE,
                                    INDEX `idx_projectId_and_stuentId`(`project_id`, `student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生评论表 ' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
