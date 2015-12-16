
CREATE TABLE IF NOT EXISTS `bankentity` (
  `bankEntityID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `bankCode` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `ctc` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`bankEntityID`),
  UNIQUE KEY `bankCode` (`bankCode`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `surname` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
