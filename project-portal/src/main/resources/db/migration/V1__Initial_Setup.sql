CREATE TABLE `client` (
                          `revision` int(11) NOT NULL DEFAULT '0' COMMENT '乐观锁',
                          `create_user` varchar(32) NOT NULL COMMENT '创建人',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_user` varchar(32) NOT NULL COMMENT '更新人',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                          `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '委托方ID',
                          `name` varchar(32) NOT NULL COMMENT '委托方名',
                          `head_image_uri` varchar(512) NOT NULL COMMENT '委托方头像URI',
                          `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '委托方状态 是否通过认证等',
                          `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已经删除',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='委托方表'