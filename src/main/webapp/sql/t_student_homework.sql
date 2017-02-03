/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : class_manager

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-09-20 20:57:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_student_homework
-- ----------------------------
DROP TABLE IF EXISTS `t_student_homework`;
CREATE TABLE `t_student_homework` (
  `date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `studentid` varchar(255) NOT NULL,
  `homeworkid` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`studentid`,`homeworkid`),
  KEY `FKnff1d32hmhq3amr3rxfudyl1s` (`homeworkid`),
  CONSTRAINT `FK1jn3u2ntaijm10mkks98n08wn` FOREIGN KEY (`studentid`) REFERENCES `t_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKnff1d32hmhq3amr3rxfudyl1s` FOREIGN KEY (`homeworkid`) REFERENCES `t_homework` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
