-- ----------------------------
-- Table structure for internal_recommendation_info
-- ----------------------------
CREATE TABLE `internal_recommendation_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_name` varchar(32) NOT NULL COMMENT '公司名',
  `company_position` varchar(32) NOT NULL COMMENT '职位',
  `working_year` varchar(32) NOT NULL COMMENT '工作年限',
  `education` varchar(32) NOT NULL COMMENT '学历',
  `salary` varchar(32) NOT NULL COMMENT '工资 ',
  `company_scale` varchar(32) NOT NULL COMMENT '公司规模',
  `is_listed_company` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否上市 0代表未上市 1代表已上市',
  `company_logo` varchar(255) NOT NULL DEFAULT '' COMMENT '公司logo',
  `company_tag` varchar(255) NOT NULL DEFAULT '' COMMENT '职位描述 以逗号分隔多个标签',
  `company_address` varchar(128) NOT NULL COMMENT '公司地址',
  `position_description` varchar(1024) DEFAULT '' COMMENT '职位描述',
  `publish_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '内推信息发布时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '软删除字段 0代表未删除 1代表已删除',
  `created_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `revision` int(10) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=923 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for internal_recommendation_property
-- ----------------------------
CREATE TABLE `internal_recommendation_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(1) NOT NULL COMMENT '数据类型 0: 学历 ; 1: 工作年限;  2: 月薪;  3: 发薪月数',
  `properties` varchar(32) NOT NULL DEFAULT '' COMMENT '属性值',
  `category` varchar(32) NOT NULL DEFAULT '' COMMENT '属性分类名',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除字段',
  `created_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `revision` int(10) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='职位属性表';

-- ----------------------------
-- Table structure for resource
-- ----------------------------
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '资源名',
  `url` varchar(255) NOT NULL COMMENT '资源路径',
  `description` varchar(255) NOT NULL COMMENT '资源描述',
  `role` varchar(128) NOT NULL COMMENT '所属角色',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  `created_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `revision` int(10) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='资源表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(32) NOT NULL COMMENT '角色名',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '角色描述',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除',
  `created_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `revision` int(10) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
CREATE TABLE `role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `resource_id` bigint(20) NOT NULL COMMENT '资源id',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '软删除标记',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `revision` int(11) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色资源表';

-- ----------------------------
-- Table structure for user_register
-- ----------------------------
CREATE TABLE `user_register` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `phone` varchar(32) NOT NULL COMMENT '用户手机号',
  `type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '用户类型 0 为导师，1为学生',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '软删除标记',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `revision` int(11) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_phone` (`phone`) USING BTREE COMMENT '用户手机号唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户注册信息表';