/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : class_manager

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-09-20 20:58:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_super_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_super_admin`;
CREATE TABLE `t_super_admin` (
  `name` varchar(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
