CREATE TABLE `logically_deleted` (
  `logically_deleted_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：默认False',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`logically_deleted_id`)
) ENGINE=InnoDB AUTO_INCREMENT=263 DEFAULT CHARSET=utf8mb4;