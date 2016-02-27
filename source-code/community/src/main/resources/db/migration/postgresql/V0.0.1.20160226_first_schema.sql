
-- table member --
CREATE TABLE `member` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `birthdate` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(150) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `passwd` varchar(255) NOT NULL,
  `phoneno` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`)
) ;