/*
Navicat MySQL Data Transfer

Source Server         : LOCALHOST
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : campos

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-12 17:14:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `campo`
-- ----------------------------
DROP TABLE IF EXISTS `campo`;
CREATE TABLE `campo` (
  `id_campo` int(11) NOT NULL AUTO_INCREMENT,
  `id_estado` int(11) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `superficie` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`id_campo`),
  KEY `campo_estado` (`id_estado`),
  CONSTRAINT `campo_estado` FOREIGN KEY (`id_estado`) REFERENCES `estado_campo` (`id_estado_campo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of campo
-- ----------------------------

-- ----------------------------
-- Table structure for `estado_campo`
-- ----------------------------
DROP TABLE IF EXISTS `estado_campo`;
CREATE TABLE `estado_campo` (
  `id_estado_campo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_estado_campo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of estado_campo
-- ----------------------------
INSERT INTO `estado_campo` VALUES ('1', 'Creado');
INSERT INTO `estado_campo` VALUES ('2', 'Parcialmente Trabajado');
INSERT INTO `estado_campo` VALUES ('3', 'Completamente Trabajado');
INSERT INTO `estado_campo` VALUES ('4', 'En Desuso');

-- ----------------------------
-- Table structure for `lote`
-- ----------------------------
DROP TABLE IF EXISTS `lote`;
CREATE TABLE `lote` (
  `id_lote` int(11) NOT NULL AUTO_INCREMENT,
  `nro_lote` int(10) NOT NULL,
  `superficie` decimal(12,2) DEFAULT NULL,
  `id_tipo_suelo` int(11) DEFAULT NULL,
  `id_campo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_lote`),
  KEY `lote_campo` (`id_campo`),
  KEY `lote_tipo_suelo` (`id_tipo_suelo`),
  CONSTRAINT `lote_campo` FOREIGN KEY (`id_campo`) REFERENCES `campo` (`id_campo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `lote_tipo_suelo` FOREIGN KEY (`id_tipo_suelo`) REFERENCES `tipo_suelo` (`id_tipo_suelo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lote
-- ----------------------------

-- ----------------------------
-- Table structure for `tipo_suelo`
-- ----------------------------
DROP TABLE IF EXISTS `tipo_suelo`;
CREATE TABLE `tipo_suelo` (
  `id_tipo_suelo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_suelo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tipo_suelo
-- ----------------------------
INSERT INTO `tipo_suelo` VALUES ('1', 'I');
INSERT INTO `tipo_suelo` VALUES ('2', 'II');
INSERT INTO `tipo_suelo` VALUES ('3', 'III');
INSERT INTO `tipo_suelo` VALUES ('4', 'IV');
INSERT INTO `tipo_suelo` VALUES ('5', 'V');
