SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `name` varchar(255) DEFAULT NULL,
                        `account` varchar(255) NOT NULL primary key,
                        `password` varchar(255) DEFAULT NULL,
                        `phoneNumber` varchar(255) DEFAULT NULL,
                        `payAccount` varchar(255) DEFAULT NULL,
                        `authorities` varchar(255) DEFAULT NULL,
                        `numArticles` INTEGER (10) DEFAULT NULL,
                        `numTag` INTEGER (10) DEFAULT NULL,
                        `numUsefulTag` INTEGER (10) DEFAULT NULL,
                        INDEX(`account` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table user add numSentences int default 0;
SET FOREIGN_KEY_CHECKS = 1;

insert into `user` values('xiaoming','ming','$2a$10$nryODW38nAFko8i2fmYhlu6CjglgMO8e1shSv3MlMMkavzbZuc0Uq','15064028912','15064028912','user',0,10,10);