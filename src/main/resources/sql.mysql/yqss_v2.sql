USE `yqss`;

/*Table structure for table `ads` */

DROP TABLE IF EXISTS `tb_moneymanager`;

CREATE TABLE `tb_moneymanager` (
  `id` INT(32) NOT NULL AUTO_INCREMENT,
  `type` INT(2) DEFAULT 1 COMMENT '1:活期宝 2:定期宝',
  `name` VARCHAR(32) DEFAULT '' COMMENT '理财方式名称 : 活期宝 定期宝',
  `introduce` VARCHAR(50) DEFAULT '' COMMENT '理财方式介绍 : 100%本息保障',
  `year_yield` VARCHAR(50) DEFAULT '' COMMENT '年华收益率',
  `time_limit` VARCHAR(50) DEFAULT '' COMMENT '期限',
  `create_date` BIGINT,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

INSERT INTO `tb_moneymanager` (`name`,`type`,`introduce`,`year_yield`,`time_limit`) VALUES 
('活期宝',1,'100%本息保障','5%','随存随取'),
('定期宝',2,'100%本息保障','8-12%','3-12个月');


DROP TABLE IF EXISTS `tb_moneymanager_due_on_demand`;

CREATE TABLE `tb_moneymanager_due_on_demand` (
  `id` INT(32) NOT NULL AUTO_INCREMENT,
  `month` INT(2) DEFAULT 2 COMMENT '月份 3,6,9,12',
  `type` INT(2) DEFAULT 1 COMMENT '1:活期宝 2:定期宝',
  `year_yield` DOUBLE DEFAULT 0 COMMENT '年华收益率 5%',
  `money_limit` DOUBLE DEFAULT 0 COMMENT '最大额度',
  `create_date` BIGINT,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

INSERT INTO `tb_moneymanager_due_on_demand`(`month`,`type`,`year_yield`,`money_limit`,`create_date`) VALUES
(3,2,5,100000,NOW()),
(6,2,10,100000,NOW()),
(9,2,15,100000,NOW()),
(12,2,20,100000,NOW()),
(0,1,5,100000,NOW());

DROP TABLE IF EXISTS `tb_moneymanager_user`;

CREATE TABLE `tb_moneymanager_user` (
  `id` INT(32) NOT NULL AUTO_INCREMENT,
  `user_id` INT(32) NOT NULL COMMENT '用户ID',
  `name` VARCHAR(50) DEFAULT '' COMMENT '认证姓名',
  `trading_password` VARCHAR(50) DEFAULT '' COMMENT '交易密码',
  `id_card` VARCHAR(50) DEFAULT '' COMMENT '身份证',
  `bank_card` VARCHAR(50) DEFAULT '' COMMENT '银行卡号',
  `create_date` BIGINT,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_moneymanager_trading_record`;

CREATE TABLE `tb_moneymanager_trading_record` (
  `id` INT(32) NOT NULL AUTO_INCREMENT,
  `user_id` INT(32) NOT NULL COMMENT '用户ID',
  `dod_id` int(32) NOT NULL comment '理财产品ID',
  `money` DOUBLE DEFAULT 0 COMMENT '转入金额',
  `pay_way` VARCHAR(50) DEFAULT '' COMMENT '支付方式 WEIXIN:微信,ALIPAY:支付宝',
  `create_date` BIGINT,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_moneymanager_common`;

CREATE TABLE `tb_moneymanager_common` (
  `id` INT(32) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) default '' comment '内容',
  `type` int(2) default 0 comment '类型 1:关于理财 2.协议',
  `create_date` BIGINT,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

insert into `tb_moneymanager_common` (`content`,`type`) values 
('关于理财介绍',2),
('购入协议',2);

