SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for entity
-- ----------------------------
DROP TABLE IF EXISTS `entity`;
CREATE TABLE `entity` (
  `field` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `entity` varchar(255) NOT NULL,
  `properties` varchar(255) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `field` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `entity1` varchar(255) DEFAULT NULL,
  `entity2` varchar(255) DEFAULT NULL,
  `relation` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of entity
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
