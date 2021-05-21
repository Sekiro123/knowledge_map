SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `account` varchar(40) DEFAULT NULL,
  `SUBJECT` varchar(40) DEFAULT NULL,
  `subject_properties` varchar(40) DEFAULT NULL,
  `relation` varchar(40) DEFAULT NULL,
  `object` varchar(40) DEFAULT NULL,
  `object_properties` varchar(40) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  INDEX(`account` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
                        `id` Integer(30) primary key Auto_Increment,
                        `name` varchar(255) DEFAULT NULL,
                        `Abstract` varchar(255) DEFAULT NULL,
                        `type1` varchar(255) DEFAULT '0',
                        `type2` varchar(255) DEFAULT '0',
                        `type3` varchar(255) DEFAULT '0',
                        `type4` varchar(255) DEFAULT '0',
                        `content` TEXT DEFAULT NULL,
                        `price` INTEGER(20) DEFAULT 0,
                        `authorAccount` varchar(255) DEFAULT NULL,
                        `authorName` varchar(255) DEFAULT NULL,
                        `publishDate` datetime DEFAULT NULL,
                        `TagTimes` Integer(20) DEFAULT 0,
                        `num_read` Integer(30) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sentences`;
CREATE TABLE `sentences` (
                            `id` Integer(30) primary key Auto_Increment,
                            `type1` varchar(255) DEFAULT '0',
                            `type2` varchar(255) DEFAULT '0',
                            `type3` varchar(255) DEFAULT '0',
                            `type4` varchar(255) DEFAULT '0',
                            `content` TEXT DEFAULT NULL,
                            `TagTimes` Integer(20) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS = 1;
