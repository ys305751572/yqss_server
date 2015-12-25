/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.11 : Database - yqss
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yqss` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yqss`;

/*Table structure for table `ads` */

DROP TABLE IF EXISTS `ads`;

CREATE TABLE `ads` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '' COMMENT '广告标题',
  `content` varchar(500) DEFAULT '' COMMENT '广告内容',
  `product_id` int(32) DEFAULT '0' COMMENT '商品ID',
  `image_url` varchar(500) DEFAULT '' COMMENT '图片url',
  `link_url` varchar(100) DEFAULT '' COMMENT '链接地址',
  `type` int(2) DEFAULT '1' COMMENT '广告类型 1:首页 2:商城 3:二手商品',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Table structure for table `bank_type` */

DROP TABLE IF EXISTS `bank_type`;

CREATE TABLE `bank_type` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(30) NOT NULL COMMENT '银行名称',
  `create_user` int(32) NOT NULL COMMENT '操作用户',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `borrow_info` */

DROP TABLE IF EXISTS `borrow_info`;

CREATE TABLE `borrow_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '所属用户ID',
  `limit_money` int(4) DEFAULT '0' COMMENT '贷款额度',
  `max_day` int(2) DEFAULT '0' COMMENT '最大期限',
  `period` int(2) DEFAULT '0' COMMENT '期数',
  `type` int(32) DEFAULT '0' COMMENT '借款原因 ',
  `username` varchar(50) DEFAULT '' COMMENT '姓名',
  `id_cartd` varchar(30) DEFAULT '' COMMENT '身份证',
  `phone` varchar(15) DEFAULT '' COMMENT '手机',
  `school_name` varchar(50) DEFAULT '' COMMENT '学校',
  `address` varchar(500) DEFAULT '' COMMENT '收货地址',
  `product_id` int(32) DEFAULT '0' COMMENT '商品ID',
  `stage_id` int(32) DEFAULT '0' COMMENT '分期ID',
  `repay_date` varchar(20) DEFAULT '' COMMENT '还款时间',
  `create_date` bigint(20) DEFAULT NULL COMMENT '借款时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Table structure for table `buy_borrow_info` */

DROP TABLE IF EXISTS `buy_borrow_info`;

CREATE TABLE `buy_borrow_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '所属用户ID',
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `image_url` varchar(200) DEFAULT '' COMMENT '商品图片',
  `price` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `period` int(2) DEFAULT '0' COMMENT '剩余期数',
  `residue_money` double DEFAULT NULL COMMENT '剩余钱数',
  `username` varchar(50) DEFAULT '' COMMENT '姓名',
  `id_cartd` varchar(30) DEFAULT '' COMMENT '身份证',
  `phone` varchar(15) DEFAULT '' COMMENT '手机',
  `school_name` varchar(50) DEFAULT '' COMMENT '学校',
  `address` varchar(500) DEFAULT '' COMMENT '收货地址',
  `stage` int(32) DEFAULT '0' COMMENT '分期ID',
  `next_date` varchar(30) DEFAULT '' COMMENT '下次还款时间',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `buy_repay_record` */

DROP TABLE IF EXISTS `buy_repay_record`;

CREATE TABLE `buy_repay_record` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `borrow_info_id` int(32) NOT NULL COMMENT '还款记录',
  `money` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '还款额度',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间/还款时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Table structure for table `collection` */

DROP TABLE IF EXISTS `collection`;

CREATE TABLE `collection` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '用户ID',
  `product_id` int(32) NOT NULL COMMENT '收藏商品ID',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `helper` */

DROP TABLE IF EXISTS `helper`;

CREATE TABLE `helper` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '问题类型',
  `question` varchar(100) DEFAULT '' COMMENT '问题',
  `answer` varchar(500) DEFAULT '' COMMENT '答案',
  `sys_user_id` int(32) NOT NULL,
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `hr_message` */

DROP TABLE IF EXISTS `hr_message`;

CREATE TABLE `hr_message` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT '人力信息标题',
  `content` varchar(500) DEFAULT '' COMMENT '人力信息内容',
  `salary_min` int(4) DEFAULT '0' COMMENT '工资下限',
  `salary_max` int(4) DEFAULT '0' COMMENT '工资上线',
  `tel` varchar(20) DEFAULT '' COMMENT '电话',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机',
  `city_id` int(4) DEFAULT '0' COMMENT '城市ID',
  `area_id` int(4) DEFAULT '0' COMMENT '区域ID',
  `street_id` int(4) DEFAULT '0' COMMENT '街道ID',
  `address` varchar(200) DEFAULT '' COMMENT '就职地点',
  `company` varchar(50) DEFAULT '' COMMENT '公司名字',
  `position` varchar(50) DEFAULT '' COMMENT '职位',
  `counts` int(2) DEFAULT '1' COMMENT '招聘人数',
  `is_list` int(2) DEFAULT '1' COMMENT '是否发布 1:发布 2:不发布',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '' COMMENT '消息标题',
  `content` varchar(500) DEFAULT '' COMMENT '消息内容',
  `user_id` int(32) NOT NULL COMMENT '所属用户',
  `is_list` int(2) DEFAULT '1' COMMENT '是否显示 1显示 2不显示',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '所属用户',
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `username` varchar(50) DEFAULT '' COMMENT '购买人姓名',
  `id_card` varchar(50) DEFAULT '' COMMENT '购买人身份证',
  `mobile` varchar(50) DEFAULT '' COMMENT '购买人手机号',
  `school_id` int(4) DEFAULT '0' COMMENT '购买人学校ID',
  `school_name` varchar(20) DEFAULT '0' COMMENT '购买人学校名称',
  `address` varchar(200) DEFAULT '0' COMMENT '收货地址',
  `order_date` bigint(20) DEFAULT NULL COMMENT '下单时间',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `content` varchar(500) DEFAULT '' COMMENT '商品描述',
  `product_type` int(32) NOT NULL COMMENT '商品所属类型',
  `url` varchar(500) DEFAULT '' COMMENT '商品链接',
  `is_list` int(2) DEFAULT '1' COMMENT '是否上架 1:上架 2:下架',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `product_comment` */

DROP TABLE IF EXISTS `product_comment`;

CREATE TABLE `product_comment` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `from_user_id` int(32) NOT NULL COMMENT '评论用户ID',
  `to_user_id` int(32) DEFAULT '0' COMMENT '被评论用户ID',
  `content` varchar(500) DEFAULT '' COMMENT '评论内容',
  `score` int(2) DEFAULT '5' COMMENT '评论等级 1-5',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Table structure for table `product_images` */

DROP TABLE IF EXISTS `product_images`;

CREATE TABLE `product_images` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `image_url` varchar(200) DEFAULT '' COMMENT '图片url',
  `height` double(10,2) DEFAULT '0.00' COMMENT '图片高度',
  `width` double(10,2) DEFAULT '0.00' COMMENT '图片宽度',
  `small_url` varchar(200) DEFAULT '' COMMENT '缩略图',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `product_stage` */

DROP TABLE IF EXISTS `product_stage`;

CREATE TABLE `product_stage` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `stage` int(32) NOT NULL COMMENT '期数ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `product_type` */

DROP TABLE IF EXISTS `product_type`;

CREATE TABLE `product_type` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT '' COMMENT '商品类型',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `repay_record` */

DROP TABLE IF EXISTS `repay_record`;

CREATE TABLE `repay_record` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `borrow_info_id` int(32) NOT NULL COMMENT '还款记录',
  `money` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '还款额度',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `second_hand` */

DROP TABLE IF EXISTS `second_hand`;

CREATE TABLE `second_hand` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT '' COMMENT '二手商品描述',
  `price` double(10,2) DEFAULT '0.00' COMMENT '商品价格',
  `user_id` int(32) NOT NULL COMMENT '所属用户ID',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Table structure for table `second_hand_images` */

DROP TABLE IF EXISTS `second_hand_images`;

CREATE TABLE `second_hand_images` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `second_hand_id` int(32) NOT NULL COMMENT '所属二手物品',
  `image_url` varchar(200) DEFAULT '' COMMENT '图片url',
  `height` double(10,2) DEFAULT '0.00' COMMENT '图片高度',
  `width` double(10,2) DEFAULT '0.00' COMMENT '图片宽度',
  `small_url` varchar(200) DEFAULT '' COMMENT '缩略图',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Table structure for table `security_log_entity` */

DROP TABLE IF EXISTS `security_log_entity`;

CREATE TABLE `security_log_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `ip_address` varchar(16) DEFAULT NULL,
  `log_level` varchar(16) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `ipAddress` varchar(16) DEFAULT NULL,
  `logLevel` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(32) NOT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `headPic` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `height` varchar(50) DEFAULT NULL,
  `weight` varchar(50) DEFAULT NULL,
  `deviceCode` varchar(50) DEFAULT NULL,
  `userToken` varchar(50) DEFAULT NULL,
  `iosToken` varchar(50) DEFAULT NULL,
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  `bak4` varchar(50) DEFAULT NULL,
  `bak5` timestamp NULL DEFAULT NULL,
  `bak6` timestamp NULL DEFAULT NULL,
  `bak7` double DEFAULT NULL,
  `bak8` double DEFAULT NULL,
  `regcode` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `dr` int(11) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `maritalStatus` varchar(50) DEFAULT NULL,
  `isHighOrSugar` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `users_bank_card` */

DROP TABLE IF EXISTS `users_bank_card`;

CREATE TABLE `users_bank_card` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '所属用户',
  `bank_type` int(32) DEFAULT '0' COMMENT '银行卡类型',
  `bank_name` varchar(30) DEFAULT '' COMMENT '银行名称',
  `card_no` varchar(30) NOT NULL COMMENT '卡号',
  `mobile` varchar(20) DEFAULT '' COMMENT '预留手机号',
  `is_default` int(2) DEFAULT '1' COMMENT '是否默认 1默认 2非默认',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `users_info` */

DROP TABLE IF EXISTS `users_info`;

CREATE TABLE `users_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '用户ID',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `gender` int(2) NOT NULL DEFAULT '1' COMMENT '性别 0:不限 1:男 2:女',
  `head_url` varchar(200) DEFAULT '' COMMENT '头像url',
  `id_card` varchar(30) DEFAULT '' COMMENT '身份证',
  `school_id` int(4) DEFAULT '0' COMMENT '学校ID',
  `school_name` varchar(20) DEFAULT '' COMMENT '学校名称',
  `address` varchar(500) DEFAULT '' COMMENT '详细地址',
  `status` int(2) DEFAULT '1' COMMENT '用户状态 1正常 2:禁用',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `users_login` */

DROP TABLE IF EXISTS `users_login`;

CREATE TABLE `users_login` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '视频标题',
  `content` varchar(500) DEFAULT '' COMMENT '视频介绍',
  `url` varchar(200) DEFAULT '' COMMENT '视频URL',
  `image_url` varchar(100) DEFAULT '' COMMENT '图片URL',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
