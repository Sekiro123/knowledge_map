SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for modeling
-- ----------------------------
DROP TABLE IF EXISTS `entity`;
CREATE TABLE `entity` (
                        `entity` varchar(255) NOT NULL primary key,
                        `properties` varchar(255) DEFAULT NULL,
                        `extra` varchar(255) DEFAULT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
                          `entity1` varchar(255) DEFAULT NULL,
                          `entity2` varchar(255) DEFAULT NULL,
                          `relation` varchar(255) DEFAULT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
