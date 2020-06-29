/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : weather

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-06-29 13:34:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for inquiry_record
-- ----------------------------
DROP TABLE IF EXISTS `inquiry_record`;
CREATE TABLE `inquiry_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `inquiry_time` datetime DEFAULT NULL COMMENT '查询时间',
  `inquiry_city_value` varchar(20) DEFAULT NULL COMMENT '查询城市名称。湖南-常德-武陵区',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL COMMENT '0：男 1：女',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
