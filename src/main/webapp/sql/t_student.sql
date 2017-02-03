/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : class_manager

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-09-20 20:57:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` varchar(20) NOT NULL,
  `access` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `classid` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK39lqdkpm493dobe060nrw17d4` (`classid`),
  CONSTRAINT `FK39lqdkpm493dobe060nrw17d4` FOREIGN KEY (`classid`) REFERENCES `t_class_admin` (`classId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
