DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
                        id int(10) primary key not null auto_increment,
                        `account` varchar(40) DEFAULT NULL,
                        `time` datetime DEFAULT NULL,
                        `operation` varchar(40) DEFAULT NULL,
                        `ip` varchar(255) DEFAULT NULL,
                        `extra_info` text DEFAULT NULL,
                        INDEX(`account` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8