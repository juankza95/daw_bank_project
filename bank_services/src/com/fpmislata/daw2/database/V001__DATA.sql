
INSERT IGNORE INTO `bankentity` (`bankEntityID`, `name`, `bankCode`, `creationDate`, `address`, `ctc`) VALUES
	(3, 'prueba', 12345, '2015-09-24 13:55:46', 'prueba', 'prueba12345'),
	(4, 'abc', 67890, '2015-10-01 00:00:00', 'abc', 'abc12345'),
	(5, 'abc', 13579, '2015-10-28 00:00:00', 'abc12345', 'abc1234567890');

INSERT IGNORE INTO `user` (`userID`, `name`, `surname`, `password`, `email`, `role`) VALUES
        (3, 'admin', 'admin', 'shuradmin', 'admin@domain.com', 'ADMIN'),
        (4, 'user', 'user', 'suruser', 'user@domain.com', 'USER'),
        (5, 'guest', 'guest', 'shurguest', 'guest@domain.com', 'GUEST');
