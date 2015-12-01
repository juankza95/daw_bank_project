
INSERT IGNORE INTO `bankentity` (`bankEntityID`, `name`, `bankCode`, `creationDate`, `address`, `ctc`) VALUES
	(3, 'prueba', 12345, '2015-09-24 13:55:46', 'prueba', 'prueba12345'),
	(4, 'abc', 12345, '2015-10-01 00:00:00', 'abc', 'abc12345'),
	(5, 'abc', 12345, '2015-10-28 00:00:00', 'abc12345', 'abc1234567890');

INSERT IGNORE INTO `user` (`userID`, `name`, `password`, `email`, `role`) VALUES
        (3, 'admin', 'admin', 'admin@domain.com', 'ADMIN'),
        (4, 'user', 'user', 'user@domain.com', 'USER'),
        (5, 'guest', 'guest', 'guest@domain.com', 'GUEST');
