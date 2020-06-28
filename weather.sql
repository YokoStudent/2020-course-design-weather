/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.29 : Database - db01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`weather` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `weather`;

/*Table structure for table `inquiry_record` */

DROP TABLE IF EXISTS `inquiry_record`;

CREATE TABLE `inquiry_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `inquiry_time` datetime DEFAULT NULL COMMENT '查询时间',
  `inquiry_city_value` varchar(20) DEFAULT NULL COMMENT '查询城市名称。湖南-常德-武陵区',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `inquiry_record` */

insert  into `inquiry_record`(`record_id`,`user_id`,`inquiry_time`,`inquiry_city_value`) values (1,9,'2020-06-26 09:33:09','深圳市'),(2,9,'2020-06-26 09:33:57','深圳市'),(3,9,'2020-06-26 09:26:06','深圳市'),(4,9,'2020-06-26 09:26:06','深圳市'),(5,9,'2020-06-26 11:06:14','深圳市'),(6,9,'2020-06-26 11:06:14','深圳市'),(7,9,'2020-06-26 11:53:01','深圳市'),(8,9,'2020-06-26 11:55:35','深圳市'),(9,9,'2020-06-26 11:55:40','深圳市'),(10,9,'2020-06-26 11:55:42','深圳市'),(11,9,'2020-06-26 11:57:09','深圳市'),(12,9,'2020-06-26 11:58:09','深圳市'),(13,12,'2020-06-26 20:29:50','长沙市'),(14,12,'2020-06-26 20:29:53','长沙市'),(15,12,'2020-06-26 20:29:54','长沙市'),(16,12,'2020-06-26 20:29:55','长沙市'),(17,12,'2020-06-26 20:47:05','长沙市'),(18,12,'2020-06-26 20:49:40','株洲市'),(19,12,'2020-06-26 20:49:42','株洲市'),(20,12,'2020-06-26 20:50:01','衡阳市'),(21,12,'2020-06-26 20:50:16','耒阳市'),(22,12,'2020-06-26 20:50:18','耒阳市'),(23,12,'2020-06-26 20:50:50','郴州市'),(24,12,'2020-06-26 20:50:53','郴州市'),(25,12,'2020-06-26 20:55:47','衡阳县'),(26,12,'2020-06-26 20:57:56','衡阳市-衡阳县');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL COMMENT 'md5密码盐',
  `age` int(11) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL COMMENT '0：男 1：女',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`salt`,`age`,`sex`,`create_time`) values (9,'admin','123456',NULL,18,1,'2020-06-25 15:07:21'),(12,'houdada','123456',NULL,18,1,'2020-06-26 12:09:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
