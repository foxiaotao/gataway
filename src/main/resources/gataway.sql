# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.21-enterprise-commercial-advanced)
# Database: test
# Generation Time: 2016-09-30 05:14:18 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table lnk_role_menuPri
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lnk_role_menuPri`;

CREATE TABLE `lnk_role_menuPri` (
  `rid` bigint(30) NOT NULL,
  `mid` bigint(30) NOT NULL COMMENT '权限菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `lnk_role_menuPri` WRITE;
/*!40000 ALTER TABLE `lnk_role_menuPri` DISABLE KEYS */;

INSERT INTO `lnk_role_menuPri` (`rid`, `mid`)
VALUES
	(1,1),
	(1,2),
	(1,3),
	(1,4),
	(1,5),
	(1,6),
	(1,7),
	(1,8),
	(1,9),
	(1,10),
	(1,11),
	(1,12),
	(1,13),
	(1,14),
	(1,15),
	(1,16),
	(1,17),
	(1,18),
	(1,19),
	(1,20),
	(1,21),
	(1,22),
	(2,1),
	(2,2),
	(1,23),
	(1,24),
	(1,25),
	(1,26);

/*!40000 ALTER TABLE `lnk_role_menuPri` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table lnk_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lnk_user_role`;

CREATE TABLE `lnk_user_role` (
  `uid` bigint(30) DEFAULT NULL,
  `rid` bigint(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `lnk_user_role` WRITE;
/*!40000 ALTER TABLE `lnk_user_role` DISABLE KEYS */;

INSERT INTO `lnk_user_role` (`uid`, `rid`)
VALUES
	(1,1),
	(1,2),
	(2,2);

/*!40000 ALTER TABLE `lnk_user_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table menu_pri
# ------------------------------------------------------------

DROP TABLE IF EXISTS `menu_pri`;

CREATE TABLE `menu_pri` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parentid` bigint(30) DEFAULT NULL,
  `inner_order` varchar(4) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `menu_type` varchar(1) DEFAULT NULL COMMENT '菜单类型',
  `isDelete` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `menu_pri` WRITE;
/*!40000 ALTER TABLE `menu_pri` DISABLE KEYS */;

INSERT INTO `menu_pri` (`id`, `name`, `url`, `parentid`, `inner_order`, `icon`, `menu_type`, `isDelete`)
VALUES
	(1,'系统管理','#',NULL,'',NULL,'1','0'),
	(2,'用户管理','User/index.do',1,'1',NULL,'1','0'),
	(3,'用户管理_添加','User/insert.do',1,'',NULL,'2','0'),
	(4,'用户管理_修改','User/update.do',1,'',NULL,'2','0'),
	(5,'用户管理_删除','User/delete.do',1,NULL,NULL,'2','0'),
	(6,'用户管理_查询','User/findByPage.do',1,NULL,NULL,'2','0'),
	(7,'角色管理','Role/index.do',1,'2',NULL,'1','0'),
	(8,'角色管理_查询','Role/findByPage.do',1,NULL,NULL,'2','0'),
	(9,'角色管理_新增','Role/insert.do',1,NULL,NULL,'2','0'),
	(10,'角色管理_修改','Role/update.do',1,NULL,NULL,'2','0'),
	(11,'角色管理_删除','Role/delete.do',1,NULL,NULL,'2','0'),
	(12,'菜单管理','Menu/index.do',1,'3',NULL,'1','0'),
	(13,'菜单管理_查询','Menu/findByPage.do',1,NULL,NULL,'2','0'),
	(14,'菜单管理_修改','Menu/update.do',1,NULL,NULL,'2','0'),
	(15,'菜单管理_查询','Menu/delete.do',1,NULL,NULL,'2','0'),
	(16,'菜单管理_查询','Menu/insert.do',1,NULL,NULL,'2','0'),
	(17,'分类管理','#',NULL,NULL,NULL,'1','0'),
	(18,'产品管理','Product/index.do',1,'1',NULL,'1','0'),
	(19,'产品管理_查询','Product/findByPage.do',1,NULL,NULL,'2','0'),
	(20,'产品管理_新增','Product/insert.do',1,NULL,NULL,'2','0'),
	(21,'产品管理_修改','Product/update.do',1,NULL,NULL,'2','0'),
	(22,'产品管理_删除','Product/delete.do',1,NULL,NULL,'2','0'),
	(23,'分类管理1','Product/index2.do',17,'1',NULL,'1',NULL),
	(24,'分类管理2','tProduct/model2.do',17,'2',NULL,'1',NULL),
	(25,'objects_product','tProduct/index.do',17,'3',NULL,'1',NULL),
	(26,'jsp_file','tProduct/excelLook.do',17,'4',NULL,'1',NULL);

/*!40000 ALTER TABLE `menu_pri` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product` varchar(100) DEFAULT NULL,
  `unit` varchar(200) DEFAULT NULL,
  `attr` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;

INSERT INTO `product` (`id`, `product`, `unit`, `attr`, `status`)
VALUES
	(1,'1','2','2','1'),
	(2,'1','2','2','1'),
	(3,'1','2','2','1'),
	(4,'1','2','2','1'),
	(5,'1','2','2','1'),
	(6,'1','3','2','4'),
	(7,'1','2','2','1'),
	(8,'1','2','2','1'),
	(9,'1','2','2','1'),
	(10,'1','2','2','1'),
	(12,'1','2','2','1'),
	(13,'1','2','2','1'),
	(16,'1','2','2','1'),
	(17,'1','2','2','1'),
	(19,'1','2','2','1'),
	(20,'1','2','2','1'),
	(21,'1','2','2','1'),
	(22,'1','2','2','1'),
	(23,'1','2','2','1'),
	(24,'1','2','2','1'),
	(25,'1','2','2','1'),
	(26,'1','2','2','1'),
	(27,'1','2','2','1'),
	(28,'1','2','2','1'),
	(29,'1','2','2','1'),
	(30,'1','2','2','1'),
	(31,'1','2','2','1'),
	(32,'1','2','2','1'),
	(33,'1','2','2','1'),
	(34,'1','2','2','1'),
	(35,'1','2','2','1'),
	(36,'1','2','2','1'),
	(37,'1','2','2','1'),
	(46,'11','33','22','3322'),
	(48,'913','913','913','913'),
	(49,'11111','11','111','12');

/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `descp` varchar(200) DEFAULT NULL,
  `parentid` bigint(10) DEFAULT NULL,
  `isDelete` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`rid`, `name`, `descp`, `parentid`, `isDelete`)
VALUES
	(1,'超级管理员','这是第一个用户',0,'0'),
	(2,'管理员','w',1,'0'),
	(3,'1','2',NULL,NULL);

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table s_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `s_user`;

CREATE TABLE `s_user` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `birth` datetime DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `cellphone` varchar(11) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `isDelete` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `s_user` WRITE;
/*!40000 ALTER TABLE `s_user` DISABLE KEYS */;

INSERT INTO `s_user` (`id`, `name`, `addr`, `desc`, `birth`, `qq`, `gender`, `cellphone`, `username`, `password`, `isDelete`)
VALUES
	(1,'孙涛','22','222','2016-08-17 11:38:59','11','1','1111','admin','1','0'),
	(2,'test','tt','tt','2016-08-17 11:38:59','123333','1','135','test','1','0');

/*!40000 ALTER TABLE `s_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sign_ticket
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sign_ticket`;

CREATE TABLE `sign_ticket` (
  `tid` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `ticket` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `errcode` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `errmsg` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `expires` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `acquiretime` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `noncestr` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `timestamp` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

LOCK TABLES `sign_ticket` WRITE;
/*!40000 ALTER TABLE `sign_ticket` DISABLE KEYS */;

INSERT INTO `sign_ticket` (`tid`, `ticket`, `errcode`, `errmsg`, `expires`, `acquiretime`, `noncestr`, `timestamp`)
VALUES
	(X'31',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `sign_ticket` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
