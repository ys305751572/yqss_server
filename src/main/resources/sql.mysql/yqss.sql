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
  `is_list`int(2) default 0 comment '是否同发布0:未发布 1:已发布 2:下架',
  `create_date` bigint default 0 comment '发布时间'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `ads` */

insert  into `ads`(`id`,`title`,`content`,`product_id`,`image_url`,`link_url`,`type`) values (1,'广告1','广告1',1,'http://192.168.3.169:8080/upload/temp/1450666441587_866054.jpg','',1),(2,'广告2','广告2',2,'http://192.168.3.169:8080/upload/temp/1450666441569_386767.jpg','',1),(3,'广告3','广告3',3,'http://192.168.3.169:8080/upload/temp/1450666441587_866054.jpg','',1),(4,'广告4','广告4',4,'http://192.168.3.169:8080/upload/temp/1450666441587_866054.jpg','',1),(5,'商城广告1','商城广告',1,'http://192.168.3.169:8080/upload/temp/1450666441569_386767.jpg','',2),(6,'商城广告2','商城广告',1,'http://192.168.3.169:8080/upload/temp/1450666441587_866054.jpg','',2),(7,'商城广告3','商城广告',1,'http://192.168.3.169:8080/upload/temp/1450666441569_386767.jpg','',2),(8,'商城广告4','商城广告',1,'http://192.168.3.169:8080/upload/temp/1450666441587_866054.jpg','',2),(9,'二手广告1','二手广告1',39,'http://192.168.3.169:8080/upload/temp/1450666441569_386767.jpg','',3),(10,'二手广告1','二手广告1',40,'http://192.168.3.169:8080/upload/temp/1450666441587_866054.jpg','',3),(11,'二手广告1','二手广告1',41,'http://192.168.3.169:8080/upload/temp/1450666441569_386767.jpg','',3);

/*Table structure for table `bank_type` */

DROP TABLE IF EXISTS `bank_type`;

CREATE TABLE `bank_type` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(30) NOT NULL COMMENT '银行名称',
  `create_user` int(32) COMMENT '操作用户',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `bank_type` */

insert  into `bank_type`(`id`,`bank_name`,`create_user`,`create_date`) values (1,'中国银行',0,20151214153225),(2,'中国工商银行',0,20151214153225),(3,'中国建设银行',0,20151214153225),(4,'中国农业银行',0,20151214153225),(5,'招商银行',0,20151214153225);

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
  `id_card` varchar(30) DEFAULT '' COMMENT '身份证',
  `phone` varchar(15) DEFAULT '' COMMENT '手机',
  `school_name` varchar(50) DEFAULT '' COMMENT '学校',
  `address` varchar(500) DEFAULT '' COMMENT '收货地址',
  `product_id` int(32) DEFAULT '0' COMMENT '商品ID',
  `stage_id` int(32) DEFAULT '0' COMMENT '分期ID',
  `repay_date` varchar(20) DEFAULT '' COMMENT '还款时间',
  `is_list`int(2) default 0 comment '是否同意 0:未处理 1:同意 2:拒绝'
  `create_date` bigint(20) DEFAULT NULL COMMENT '借款时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `borrow_info` */

insert  into `borrow_info`(`id`,`user_id`,`limit_money`,`max_day`,`period`,`type`,`username`,`id_cartd`,`phone`,`school_name`,`address`,`product_id`,`stage_id`,`repay_date`,`create_date`) values (1,1,3000,15,0,1,'名称','14646464646','123456789','学校','地址',0,0,'2015-12-31 00:00:00',NULL),(14,6,888,15,0,1,'梅西','166444454544464646','13644949494','西班牙皇家学院','巴塞罗那足球场',0,0,'2016-01-03 00:00:00',1450410391612),(15,7,0,0,0,0,'zhouwei','2377777777777','13122222222','wuhandaxue','sdfsadfajdkfa;ljkdfakdsfja',0,0,'',1450948368754);

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
  `id_card` varchar(30) DEFAULT '' COMMENT '身份证',
  `phone` varchar(15) DEFAULT '' COMMENT '手机',
  `school_name` varchar(50) DEFAULT '' COMMENT '学校',
  `address` varchar(500) DEFAULT '' COMMENT '收货地址',
  `stage` int(32) DEFAULT '0' COMMENT '分期ID',
  `next_date` varchar(30) DEFAULT '' COMMENT '下次还款时间',
  `is_list`int(2) default 0 comment '是否同意 0:未处理 1:同意 2:拒绝'
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `buy_borrow_info` */

insert  into `buy_borrow_info`(`id`,`user_id`,`product_id`,`name`,`image_url`,`price`,`period`,`residue_money`,`username`,`id_cartd`,`phone`,`school_name`,`address`,`stage`,`next_date`,`create_date`) values (3,1,1,'苹果','',6088.00,7,NULL,'sfss','654646','13476107753','学校22','地址11111',8,'2016-01-15 00:00:00',1450233933482),(4,6,1,'苹果','',6088.00,0,NULL,'哎呦','123545988569456987','13554265422','急急急','哎呦',6,'2016-01-15 00:00:00',1450771423032),(5,6,4,'冰箱','',6088.00,0,NULL,'哎呦','123545988569456987','13554265422','急急急','哎呦',0,'2016-01-15 00:00:00',1450773381360),(6,7,1,'苹果','',6088.00,0,NULL,'zhouwei','421302888888888888','13122222222','wuhandaxue','dsdfsdfa',7,'2016-01-15 00:00:00',1450947517876),(7,7,1,'苹果','',6088.00,0,NULL,'zhowwww','32323232323412341','13233333333','ssss','sssss',7,'2016-01-15 00:00:00',1450947684197);

/*Table structure for table `buy_repay_record` */

DROP TABLE IF EXISTS `buy_repay_record`;

CREATE TABLE `buy_repay_record` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `borrow_info_id` int(32) NOT NULL COMMENT '还款记录',
  `money` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '还款额度',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间/还款时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `buy_repay_record` */

insert  into `buy_repay_record`(`id`,`borrow_info_id`,`money`,`create_date`) values (4,3,500.00,1450243248932),(5,4,11.00,1450841530510),(6,4,2.00,1450841539053),(7,4,1.00,1450841618572),(8,4,1.00,1450842110019),(9,4,1.00,1450842117491),(10,4,1.00,1450842122576),(11,5,1.00,1450851841278),(12,4,2.00,1450940690196),(13,4,1.00,1451028902964),(14,4,1.00,1451028910865),(15,5,1.00,1451028927900);

/*Table structure for table `collection` */

DROP TABLE IF EXISTS `collection`;

CREATE TABLE `collection` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '用户ID',
  `product_id` int(32) NOT NULL COMMENT '收藏商品ID',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `collection` */

insert  into `collection`(`id`,`user_id`,`product_id`,`create_date`) values (2,7,1,1450927546793);

/*Table structure for table `helper` */

DROP TABLE IF EXISTS `helper`;

CREATE TABLE `helper` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL DEFAULT '' COMMENT '问题类型',
  `question` varchar(100) DEFAULT '' COMMENT '问题',
  `answer` varchar(500) DEFAULT '' COMMENT '答案',
  `sys_user_id` int(32) ,
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `helper` */

insert  into `helper`(`id`,`type`,`question`,`answer`,`sys_user_id`,`create_date`) values (1,'','305751572','123456789',0,NULL),(2,'申请条件','问题1','答案',0,NULL),(3,'账单还款','问题1','答案',0,NULL),(4,'办理流程','问题1','答案',0,NULL),(5,'售后服务','问题1','答案',0,NULL);

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

/*Data for the table `hr_message` */

insert  into `hr_message`(`id`,`title`,`content`,`salary_min`,`salary_max`,`tel`,`mobile`,`city_id`,`area_id`,`street_id`,`address`,`company`,`position`,`counts`,`is_list`,`create_date`) values (1,'标题1','内容1',2000,5000,'','13476107756',0,0,0,'创业基地','6mai','java程序员',3,1,NULL),(2,'标题2','内容2',2000,5000,'','13476107756',0,0,0,'创业基地111','6mai11','java程序员111',3,1,NULL),(3,'标题3','内容3',2000,5000,'','13476107756',0,0,0,'创业基地111','6mai11','java程序员111',3,1,NULL),(4,'标题3','内容3',2000,5000,'','13476107756',0,0,0,'创业基地111','6mai11','java程序员111',3,1,NULL);

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

/*Data for the table `message` */

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

/*Data for the table `orders` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `content` varchar(500) DEFAULT '' COMMENT '商品描述',
  `product_type` int(32) NOT NULL COMMENT '商品所属类型',
  `url` varchar(500) DEFAULT '' COMMENT '商品链接',
  `is_list` int(2) DEFAULT '1' COMMENT '是否上架 1:上架 2:下架',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`price`,`content`,`product_type`,`url`,`is_list`,`create_date`) values (1,'苹果','6088.00','介绍',1,'',1,NULL),(2,'三星','6088.00','介绍222',1,'',1,NULL),(3,'thinkpad','6088.00','介绍222',2,'',1,NULL),(4,'冰箱','6088.00','介绍222',3,'',1,NULL),(5,'音响','6088.00','介绍222',4,'',1,NULL),(26,'手表','100.00',NULL,2,'http://www.baidu.com',1,NULL);

/*Table structure for table `product_comment` */

DROP TABLE IF EXISTS `product_comment`;

CREATE TABLE `product_comment` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `from_user_id` int(32) NOT NULL COMMENT '评论用户ID',
  `to_user_id` int(32) DEFAULT '0' COMMENT '被评论用户ID',
  `content` varchar(500) DEFAULT '' COMMENT '评论内容',
  `score` int(2) DEFAULT '5' COMMENT '评论等级 1-5',
  `is_list` int(2) default 1 comment '1:未读 2:已读',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `product_comment` */

insert  into `product_comment`(`id`,`product_id`,`from_user_id`,`to_user_id`,`content`,`score`,`create_date`) values (1,1,5,1,'评论1111',5,NULL),(2,1,5,0,'评论222',5,NULL),(3,1,5,0,'评论333',5,NULL),(4,13,6,0,'哦',5,20151216160432),(5,13,6,0,'哈哈',5,20151216162044),(6,13,6,0,'个',5,20151216165043),(7,13,6,0,'。',5,20151216165234),(8,13,6,0,'，',5,20151216165238),(9,13,6,0,'？',5,20151216165243),(10,13,6,0,'你好',5,20151216165417),(11,12,6,0,'你好',5,20151216165626),(12,12,6,0,'哈哈',5,20151216165634),(13,10,6,0,'你好',5,20151216170055),(14,14,6,0,'迷你',5,20151216173237),(15,14,6,0,'你好',5,20151216173256),(16,17,6,0,'你好',5,20151217135320),(17,17,6,6,'哎呦',5,20151217135705),(18,17,6,6,'。。。',5,20151217140020),(19,17,6,6,'以后',5,20151217140436),(20,16,6,6,'你好',5,1450406544632),(21,16,6,6,'哎呦',5,1450406549518),(22,16,6,6,'哈',5,1450406553466),(23,16,6,6,'啦',5,1450406557541),(24,16,6,6,'哪里',5,1450406562231),(25,16,6,6,'路',5,1450406566024),(26,16,6,6,'啊',5,1450406571033),(27,16,6,6,'兔',5,1450406576466),(28,16,6,6,'哈喽',5,1450406581283),(29,16,6,6,'里',5,1450406584902),(30,16,6,6,'比赛',5,1450406601456),(31,17,6,6,'哎呦',5,1450418429331),(32,35,6,5,'哎呦',5,1450684638498),(33,35,6,5,'哈哈',5,1450684643899),(34,39,6,6,'哈哈',5,1450691050670),(35,39,6,6,'哎呦',5,1450691054983),(36,42,6,6,'嗯哼',5,1450856071680);

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `product_images` */

insert  into `product_images`(`id`,`product_id`,`image_url`,`height`,`width`,`small_url`,`create_date`) values (23,26,'http://localhost:8081/upload/pi/1_20151225162426880.jpg',NULL,NULL,NULL,NULL),(26,26,'http://localhost:8081/upload/pi/2_20151225164512736.jpg',NULL,NULL,NULL,NULL);

/*Table structure for table `product_stage` */

DROP TABLE IF EXISTS `product_stage`;

CREATE TABLE `product_stage` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `product_id` int(32) NOT NULL COMMENT '商品ID',
  `stage` int(32) NOT NULL COMMENT '期数ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `product_stage` */

insert  into `product_stage`(`id`,`product_id`,`stage`) values (6,1,6),(7,1,7),(8,1,8),(9,1,9);

/*Table structure for table `product_type` */

DROP TABLE IF EXISTS `product_type`;

CREATE TABLE `product_type` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT '' COMMENT '商品类型',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `product_type` */

insert  into `product_type`(`id`,`name`,`create_date`) values (1,'手机',20151214115048),(2,'电脑',20151214115048),(3,'生活用品',20151214115048),(4,'图书音响',20151214115048);

/*Table structure for table `repay_record` */

DROP TABLE IF EXISTS `repay_record`;

CREATE TABLE `repay_record` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `borrow_info_id` int(32) NOT NULL COMMENT '还款记录',
  `money` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '还款额度',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `repay_record` */

insert  into `repay_record`(`id`,`borrow_info_id`,`money`,`create_date`) values (1,14,1.00,1450423224445),(2,14,2.00,1450423580443),(3,14,1.00,1450423868422),(4,14,2.00,1450424282201),(5,14,2.00,1450424892042),(6,14,15.00,1450682282306),(7,14,12.00,1450839709372),(8,14,2.00,1450839989469),(9,14,1.00,1450840051125),(10,14,10.00,1450840073256),(11,14,1.00,1450840094651),(12,14,1.00,1450840105996),(13,14,1.00,1450840161940),(14,14,1.00,1450840971187),(15,14,1.00,1450842046193),(16,14,1.00,1450842093962);

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

/*Data for the table `second_hand` */

insert  into `second_hand`(`id`,`title`,`content`,`price`,`user_id`,`create_date`) values (1,'哎呦555555','吧',1113.00,5,1450842921557),(2,'哎呦555555','吧',1113.00,5,1450842921557),(3,'哎呦555555','吧',1113.00,5,1450842921557),(4,'哎呦555555','吧',1113.00,5,1450842921557),(5,'哎呦555555','吧',1113.00,5,1450842921557),(6,'哎呦555555','吧',1113.00,5,1450842921557),(7,'哎呦555555','吧',1113.00,5,1450842921557),(8,'哎呦555555','吧',1113.00,5,1450842921557),(9,'哎呦555555','吧',1113.00,5,1450842921557),(10,'哎呦555555','吧',1113.00,5,1450842921557),(15,'哎呦555555','吧',1113.00,6,1450842921557),(16,'哎呦555555','吧',1113.00,6,1450842921557),(17,'哎呦555555','吧',1113.00,6,1450842921557),(21,'哎呦555555','吧',1113.00,5,1450842921557),(22,'哎呦555555','吧',1113.00,5,1450842921557),(24,'哎呦555555','吧',1113.00,5,1450842921557),(25,'哎呦555555','吧',1113.00,5,1450842921557),(27,'哎呦555555','吧',1113.00,5,1450842921557),(28,'哎呦555555','吧',1113.00,5,1450842921557),(29,'哎呦555555','吧',1113.00,5,1450842921557),(30,'哎呦555555','吧',1113.00,5,1450842921557),(31,'哎呦555555','吧',1113.00,5,1450842921557),(32,'哎呦555555','吧',1113.00,6,1450842921557),(33,'哎呦555555','吧',1113.00,5,1450842921557),(34,'哎呦555555','吧',1113.00,6,1450842921557),(35,'哎呦555555','吧',1113.00,5,1450842921557),(36,'哎呦555555','吧',1113.00,6,1450842921557),(37,'哎呦555555','吧',1113.00,6,1450842921557),(38,'哎呦555555','吧',1113.00,6,1450842921557),(39,'哎呦555555','吧',1113.00,6,1450842921557),(40,'哎呦555555','吧',1113.00,6,1450842921557),(41,'哎呦555555','吧',1113.00,6,1450842921557),(42,'哎呦111111','吧恩',1113.00,6,1450843190505);

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

/*Data for the table `second_hand_images` */

insert  into `second_hand_images`(`id`,`second_hand_id`,`image_url`,`height`,`width`,`small_url`,`create_date`) values (1,24,'',0.00,0.00,'',1450429418007),(2,24,'',0.00,0.00,'',1450429418009),(3,24,'',0.00,0.00,'',1450429418010),(4,25,'',0.00,0.00,'',1450429568637),(5,25,'',0.00,0.00,'',1450429568638),(6,25,'',0.00,0.00,'',1450429568640),(7,27,'',0.00,0.00,'',1450429792231),(8,27,'',0.00,0.00,'',1450429792536),(9,27,'',0.00,0.00,'',1450429793279),(12,31,'http://192.168.3.169:8081/upload/temp/1450430151926_128704.jpg',850.00,570.00,'',1450430152220),(13,33,'http://localhost:8080/upload/temp/1450664364348_557203.jpg',612.00,816.00,'',1450664364601),(14,33,'http://localhost:8080/upload/temp/1450664364603_588332.png',160.00,742.00,'',1450664364606),(15,35,'http://localhost:8080/upload/temp/1450664649686_90588.jpg',612.00,816.00,'',1450664649717),(16,37,'http://192.168.3.169:8080/upload/temp/1450664995011_854966.jpg',150.00,150.00,'',1450664995014),(17,38,'http://192.168.3.169:8080/upload/temp/1450665801611_719198.jpg',150.00,150.00,'',1450665801613),(18,39,'http://192.168.3.169:8080/upload/temp/1450665907161_54302.jpg',150.00,150.00,'',1450665907163),(22,41,'http://192.168.3.169:8080/upload/temp/1450666441559_104290.jpg',150.00,150.00,'',1450666441562),(23,41,'http://192.168.3.169:8080/upload/temp/1450666441564_512657.jpg',150.00,150.00,'',1450666441567),(24,41,'http://192.168.3.169:8080/upload/temp/1450666441569_386767.jpg',150.00,150.00,'',1450666441572),(25,41,'http://192.168.3.169:8080/upload/temp/1450666441574_102269.jpg',150.00,150.00,'',1450666441577),(26,41,'http://192.168.3.169:8080/upload/temp/1450666441578_828398.jpg',150.00,150.00,'',1450666441580),(27,41,'http://192.168.3.169:8080/upload/temp/1450666441581_421142.jpg',150.00,150.00,'',1450666441583),(28,41,'http://192.168.3.169:8080/upload/temp/1450666441584_345018.jpg',150.00,150.00,'',1450666441585),(29,41,'http://192.168.3.169:8080/upload/temp/1450666441587_866054.jpg',150.00,150.00,'',1450666441593),(34,42,'http://192.168.3.169:8080/upload/temp/1450684473921_831906.jpg',150.00,150.00,'',1450684473951);

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
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8;

/*Data for the table `security_log_entity` */

insert  into `security_log_entity`(`id`,`create_time`,`ip_address`,`log_level`,`message`,`username`,`createTime`,`ipAddress`,`logLevel`) values (10,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:26:44',NULL,'TRACE'),(11,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:30:37',NULL,'TRACE'),(12,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:31:28',NULL,'TRACE'),(13,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:32:15',NULL,'TRACE'),(14,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:32:40',NULL,'TRACE'),(15,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:34:55',NULL,'TRACE'),(16,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:36:17',NULL,'TRACE'),(17,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:37:05',NULL,'TRACE'),(18,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:38:03',NULL,'TRACE'),(19,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:51:43',NULL,'TRACE'),(20,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:53:39',NULL,'TRACE'),(21,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 16:55:24',NULL,'TRACE'),(22,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 17:00:38',NULL,'TRACE'),(23,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 17:05:28',NULL,'TRACE'),(24,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 17:06:17',NULL,'TRACE'),(25,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 17:06:49',NULL,'TRACE'),(26,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-22 17:13:14',NULL,'TRACE'),(27,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 14:38:03',NULL,'TRACE'),(28,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 14:42:26',NULL,'TRACE'),(29,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 14:43:20',NULL,'TRACE'),(30,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 14:45:21',NULL,'TRACE'),(31,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 14:51:59',NULL,'TRACE'),(32,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 14:55:59',NULL,'TRACE'),(33,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 14:56:58',NULL,'TRACE'),(34,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 15:04:26',NULL,'TRACE'),(35,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 15:21:45',NULL,'TRACE'),(36,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 15:22:39',NULL,'TRACE'),(37,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 15:43:48',NULL,'TRACE'),(38,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 15:49:06',NULL,'TRACE'),(39,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 15:52:51',NULL,'TRACE'),(40,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 15:56:39',NULL,'TRACE'),(41,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 16:00:00',NULL,'TRACE'),(42,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 16:03:35',NULL,'TRACE'),(43,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 16:04:47',NULL,'TRACE'),(44,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 16:29:01',NULL,'TRACE'),(45,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 16:29:01',NULL,'TRACE'),(46,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 16:33:45',NULL,'TRACE'),(47,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 16:36:14',NULL,'TRACE'),(48,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 16:36:44',NULL,'TRACE'),(49,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 16:40:09',NULL,'TRACE'),(50,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:17:10',NULL,'TRACE'),(51,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:21:44',NULL,'TRACE'),(52,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:39:02',NULL,'TRACE'),(53,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:39:02',NULL,'TRACE'),(54,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:39:03',NULL,'TRACE'),(55,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:39:03',NULL,'TRACE'),(56,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:39:03',NULL,'TRACE'),(57,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:41:09',NULL,'TRACE'),(58,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:41:59',NULL,'TRACE'),(59,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 17:43:14',NULL,'TRACE'),(60,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 18:06:02',NULL,'TRACE'),(61,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-23 18:17:26',NULL,'TRACE'),(62,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:27:21',NULL,'TRACE'),(63,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:29:47',NULL,'TRACE'),(64,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:29:47',NULL,'TRACE'),(65,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:31:07',NULL,'TRACE'),(66,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:36:38',NULL,'TRACE'),(67,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:40:36',NULL,'TRACE'),(68,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:44:06',NULL,'TRACE'),(69,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:46:18',NULL,'TRACE'),(70,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:48:25',NULL,'TRACE'),(71,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:55:47',NULL,'TRACE'),(72,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 10:59:27',NULL,'TRACE'),(73,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 11:01:07',NULL,'TRACE'),(74,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 11:01:31',NULL,'TRACE'),(75,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 11:10:05',NULL,'TRACE'),(76,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 11:11:46',NULL,'TRACE'),(77,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 11:18:39',NULL,'TRACE'),(78,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 11:23:14',NULL,'TRACE'),(79,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 11:38:50',NULL,'TRACE'),(80,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 11:48:39',NULL,'TRACE'),(81,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:01:23',NULL,'TRACE'),(82,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:02:59',NULL,'TRACE'),(83,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:03:19',NULL,'TRACE'),(84,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:10:11',NULL,'TRACE'),(85,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:15:57',NULL,'TRACE'),(86,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:20:16',NULL,'TRACE'),(87,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:20:17',NULL,'TRACE'),(88,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:20:17',NULL,'TRACE'),(89,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:20:17',NULL,'TRACE'),(90,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:21:22',NULL,'TRACE'),(91,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 12:25:41',NULL,'TRACE'),(92,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 13:36:58',NULL,'TRACE'),(93,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:29:45',NULL,'TRACE'),(94,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:29:45',NULL,'TRACE'),(95,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:29:45',NULL,'TRACE'),(96,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:34:31',NULL,'TRACE'),(97,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:44:01',NULL,'TRACE'),(98,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:52:14',NULL,'TRACE'),(99,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:53:50',NULL,'TRACE'),(100,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:53:51',NULL,'TRACE'),(101,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:53:51',NULL,'TRACE'),(102,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:54:38',NULL,'TRACE'),(103,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:54:38',NULL,'TRACE'),(104,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:56:13',NULL,'TRACE'),(105,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:56:14',NULL,'TRACE'),(106,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:56:37',NULL,'TRACE'),(107,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:56:37',NULL,'TRACE'),(108,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:57:45',NULL,'TRACE'),(109,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 14:57:46',NULL,'TRACE'),(110,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:03:31',NULL,'TRACE'),(111,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:03:32',NULL,'TRACE'),(112,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:03:50',NULL,'TRACE'),(113,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:03:51',NULL,'TRACE'),(114,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:05:18',NULL,'TRACE'),(115,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:06:14',NULL,'TRACE'),(116,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:06:46',NULL,'TRACE'),(117,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:07:40',NULL,'TRACE'),(118,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:08:03',NULL,'TRACE'),(119,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:09:04',NULL,'TRACE'),(120,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:09:15',NULL,'TRACE'),(121,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:09:41',NULL,'TRACE'),(122,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 15:15:15',NULL,'TRACE'),(123,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 16:05:36',NULL,'TRACE'),(124,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 16:10:01',NULL,'TRACE'),(125,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 16:13:23',NULL,'TRACE'),(126,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 16:14:33',NULL,'TRACE'),(127,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 16:29:28',NULL,'TRACE'),(128,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:08:28',NULL,'TRACE'),(129,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:16:17',NULL,'TRACE'),(130,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:16:54',NULL,'TRACE'),(131,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:21:06',NULL,'TRACE'),(132,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:21:46',NULL,'TRACE'),(133,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:22:28',NULL,'TRACE'),(134,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:24:31',NULL,'TRACE'),(135,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:29:47',NULL,'TRACE'),(136,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:38:07',NULL,'TRACE'),(137,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:43:23',NULL,'TRACE'),(138,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:46:36',NULL,'TRACE'),(139,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:48:05',NULL,'TRACE'),(140,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:53:43',NULL,'TRACE'),(141,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:56:29',NULL,'TRACE'),(142,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 17:58:43',NULL,'TRACE'),(143,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:00:32',NULL,'TRACE'),(144,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:06:46',NULL,'TRACE'),(145,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:10:54',NULL,'TRACE'),(146,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:13:58',NULL,'TRACE'),(147,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:19:39',NULL,'TRACE'),(148,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:20:37',NULL,'TRACE'),(149,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:21:10',NULL,'TRACE'),(150,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:22:46',NULL,'TRACE'),(151,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:27:23',NULL,'TRACE'),(152,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:31:11',NULL,'TRACE'),(153,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:31:11',NULL,'TRACE'),(154,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:31:11',NULL,'TRACE'),(155,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:31:30',NULL,'TRACE'),(156,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:33:06',NULL,'TRACE'),(157,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:43:08',NULL,'TRACE'),(158,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-24 18:44:01',NULL,'TRACE'),(159,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 01:29:07',NULL,'TRACE'),(160,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 01:33:39',NULL,'TRACE'),(161,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 01:45:38',NULL,'TRACE'),(162,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 01:48:44',NULL,'TRACE'),(163,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 01:51:19',NULL,'TRACE'),(164,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 01:52:16',NULL,'TRACE'),(165,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 02:02:49',NULL,'TRACE'),(166,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 02:06:09',NULL,'TRACE'),(167,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 02:08:11',NULL,'TRACE'),(168,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 02:26:12',NULL,'TRACE'),(169,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 02:36:45',NULL,'TRACE'),(170,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 02:50:08',NULL,'TRACE'),(171,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 03:00:13',NULL,'TRACE'),(172,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 03:09:07',NULL,'TRACE'),(173,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 03:15:58',NULL,'TRACE'),(174,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 03:17:56',NULL,'TRACE'),(175,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 03:24:53',NULL,'TRACE'),(176,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 15:34:08',NULL,'TRACE'),(177,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 15:44:35',NULL,'TRACE'),(178,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 15:51:01',NULL,'TRACE'),(179,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 15:53:28',NULL,'TRACE'),(180,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:06:44',NULL,'TRACE'),(181,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:09:29',NULL,'TRACE'),(182,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:17:09',NULL,'TRACE'),(183,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:19:03',NULL,'TRACE'),(184,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:23:52',NULL,'TRACE'),(185,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:28:57',NULL,'TRACE'),(186,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:31:39',NULL,'TRACE'),(187,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:33:25',NULL,'TRACE'),(188,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:37:27',NULL,'TRACE'),(189,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:39:18',NULL,'TRACE'),(190,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 16:43:27',NULL,'TRACE'),(191,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 17:30:28',NULL,'TRACE'),(192,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 17:55:07',NULL,'TRACE'),(193,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 17:57:17',NULL,'TRACE'),(194,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 18:01:17',NULL,'TRACE'),(195,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 18:03:06',NULL,'TRACE'),(196,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 18:06:27',NULL,'TRACE'),(197,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 18:07:28',NULL,'TRACE'),(198,NULL,NULL,NULL,'admin登录了系统。','admin','2015-12-25 18:08:01',NULL,'TRACE');

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

/*Data for the table `user` */

insert  into `user`(`user_id`,`mobile`,`email`,`account`,`password`,`headPic`,`name`,`sex`,`birthday`,`height`,`weight`,`deviceCode`,`userToken`,`iosToken`,`bak1`,`bak2`,`bak3`,`bak4`,`bak5`,`bak6`,`bak7`,`bak8`,`regcode`,`remark`,`dr`,`ts`,`city`,`maritalStatus`,`isHighOrSugar`) values ('00000000000000000000000000000000','13523147658','hao123@126.com','admin','123456','http://img.51daifu.com/elder/20101213/elder_20101213_lwj_1.jpg','总公司管理员',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'99','2014-09-22 00:00:00',NULL,NULL,NULL,NULL,'',0,NULL,NULL,NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `users_bank_card` */

insert  into `users_bank_card`(`id`,`user_id`,`bank_type`,`bank_name`,`card_no`,`mobile`,`is_default`,`create_date`) values (2,5,1,'','9876544321','13476107753',1,20151214154048),(7,5,1,'','123456','123456',1,1450863102796),(10,7,1,'','6226820011200783033','13135741287',1,1450925876531),(11,6,3,'','1234 5678 9123 4567 78','13247176223',1,1451037429800);

/*Table structure for table `users_info` */

DROP TABLE IF EXISTS `users_info`;

CREATE TABLE `users_info` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL COMMENT '用户ID',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `nickname` varchar(30) DEFAULT '' comment '昵称',
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

/*Data for the table `users_info` */

insert  into `users_info`(`id`,`user_id`,`mobile`,`name`,`gender`,`head_url`,`id_card`,`school_id`,`school_name`,`address`,`status`,`create_date`) values (1,5,'13135741287','45646798',2,'http://localhost:8081/upload/temp/1450672473161_390421.jpg','4208888888888',0,'测试学校','武汉',1,20151214121638),(2,6,'13135741287','NULL',1,'http://192.168.3.16:8080/upload/temp/1450984645655_694442.jpg','411326198401069934',0,'家里蹲大学','火星',1,20151215143102),(3,7,'13135741287','Jacky Zhou',2,'http://192.168.3.53:8080/upload/temp/1450767148413_650617.jpg','4213051995020245566',0,'wuhandaxue jiaoyube','wuhan guang gu ruanjian yuan china dong diddi street NO.999 city America',1,1450668287475),(4,8,'13135741287','',1,'','',0,'','',1,1450766751295);

/*Table structure for table `users_login` */

DROP TABLE IF EXISTS `users_login`;

CREATE TABLE `users_login` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `users_login` */

insert  into `users_login`(`id`,`username`,`password`) values (5,'13429806156','12345678'),(6,'13247176223','123456'),(7,'13135741287','1234567'),(8,'15623756692','123456');

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

/*Data for the table `video` */

insert  into `video`(`id`,`title`,`content`,`url`,`image_url`,`create_date`) values (1,'默认','默认视频','','',NULL),(2,'视频1','视频1','http://www.baidu.com','',NULL),(3,'视频2','视频2','http://www.baidu.com','',NULL),(4,'视频3','视频3','http://www.baidu.com','',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
