-- ----------------------------
-- Table structure for success_case
-- ----------------------------
CREATE TABLE `success_case`
(
    `id`           bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `project_id`   bigint(20) NULL DEFAULT NULL COMMENT '项目表id',
    `url`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '成功案例图片',
    `praise`       int(10) NULL DEFAULT 0 COMMENT '成功案例的点赞',
    `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `revision`     int(10) NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;
-- ----------------------------
-- Records of success_case
-- ----------------------------

SET
FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for carousel_image_group
-- ----------------------------
DROP TABLE IF EXISTS `carousel_image_group`;
CREATE TABLE `carousel_image_group`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
    `image_uri`    varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '轮播图片URI',
    `image_group`  tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '轮播图片分组',
    `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `is_deleted`   tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否已经删除',
    `revision`     int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `idx_image_group`(`image_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轮播图分组表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carousel_image_group
-- ----------------------------
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID ',
    `name`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '姓名',
    `wx_card`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '微信号码',
    `type`           tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户类型 0 为导师，1为学生',
    `title`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '职业身份',
    `work_year`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '工作年限',
    `project_number` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '项目数量',
    `daily_salary`   decimal(10, 0)                                                NOT NULL COMMENT '日薪',
    `manager_rank`   int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '后台设置排名 ',
    `is_deleted`     tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '名次软删除 ，',
    `image_head_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像图片地址',
    `avg_score_by_a` decimal(2, 1) NULL DEFAULT NULL,
    `avg_score`      decimal(2, 1)                                                 NOT NULL COMMENT '能力平均分数？星',
    `created_time`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time`   datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `revision`       int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX            `idx_user`(`type`, `is_deleted`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表 用户表：（导师，学生）的详细信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_experience
-- ----------------------------
DROP TABLE IF EXISTS `user_experience`;
CREATE TABLE `user_experience`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT '经验表ID',
    `project_exp_desc` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '经验描述 ',
    `user_id`          bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID ',
    `created_time`     datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time`     datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `revision`         int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX              `idx_project_exp`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目经验表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_skill
-- ----------------------------
DROP TABLE IF EXISTS `user_skill`;
CREATE TABLE `user_skill`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '技能ID',
    `skill_desc`   varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '技能描述',
    `user_id`      bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID 加索引',
    `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `revision`     int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `idx_skill`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '技能表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_work
-- ----------------------------
DROP TABLE IF EXISTS `user_work`;
CREATE TABLE `user_work`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '作品集ID ',
    `title`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作品项目标题',
    `push_date`    datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '作品发布时间',
    `sequence`     int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '作品排序 ',
    `type_id`      bigint(20) NOT NULL DEFAULT 0 COMMENT '作品集类型ID',
    `user_id`      bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
    `is_deleted`   tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '软删除标记 ',
    `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `revision`     int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `idx_work`(`user_id`, `type_id`, `is_deleted`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作品集表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for work_description
-- ----------------------------
DROP TABLE IF EXISTS `work_description`;
CREATE TABLE `work_description`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '作品项目描述表ID',
    `project_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作品项目描述',
    `user_work_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '作品集ID ',
    `user_id`      bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID',
    `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `revision`     int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `idx_work_desc`(`user_id`, `user_work_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作品项目描述表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for work_image
-- ----------------------------
DROP TABLE IF EXISTS `work_image`;
CREATE TABLE `work_image`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
    `img_url`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作品图片地址',
    `img_desc`     varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作品图片描述',
    `user_work_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '作品集ID ',
    `user_id`      bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID ',
    `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `revision`     int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `idx_user_img`(`user_work_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '图片信息表 存储图片信息。使用用户的id进行关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for work_praise
-- ----------------------------
DROP TABLE IF EXISTS `work_praise`;
CREATE TABLE `work_praise`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
    `praise_number` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数量 ',
    `user_work_id`  bigint(20) NOT NULL DEFAULT 0 COMMENT '作品集表的ID ',
    `user_id`       bigint(20) NOT NULL DEFAULT 0 COMMENT '用户ID ',
    `created_time`  datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time`  datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `revision`      int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX           `idx_praise`(`user_work_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作品点赞表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for work_type
-- ----------------------------
DROP TABLE IF EXISTS `work_type`;
CREATE TABLE `work_type`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '作品类型ID ',
    `type`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作品类型',
    `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `updated_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP (0) ON UPDATE CURRENT_TIMESTAMP (0) COMMENT '更新时间',
    `revision`     int(10) NOT NULL DEFAULT 0 COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `idx_work_type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作品类型表 ' ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;