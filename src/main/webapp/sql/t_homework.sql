/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : class_manager

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-09-20 20:57:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_homework
-- ----------------------------
DROP TABLE IF EXISTS `t_homework`;
CREATE TABLE `t_homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attach` varchar(255) DEFAULT NULL,
  `conclude` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `publish` datetime DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `authorid` varchar(255) DEFAULT NULL,
  `classid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKctidy8qj9jjnrrfrpasqu28jl` (`authorid`),
  KEY `FKle38uy8988jfnsc7hqjpfqmj8` (`classid`),
  CONSTRAINT `FKctidy8qj9jjnrrfrpasqu28jl` FOREIGN KEY (`authorid`) REFERENCES `t_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKle38uy8988jfnsc7hqjpfqmj8` FOREIGN KEY (`classid`) REFERENCES `t_class_admin` (`classId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
