
CREATE TABLE `auth_role` (
  `auth_role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`auth_role_id`)
);

INSERT INTO auth_role VALUES (1,'SUPER_USER','This user has ultimate rights for everything');
INSERT INTO auth_role VALUES (2,'ADMIN_USER','This user has admin rights for administrative work');
INSERT INTO auth_role VALUES (3,'SITE_USER','This user has access to site, after login - normal user');


CREATE TABLE `auth_user` (
  `auth_user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`auth_user_id`)
);

CREATE TABLE `auth_user_role` (
  `auth_user_id` int NOT NULL,
  `auth_role_id` int NOT NULL,
  PRIMARY KEY (`auth_user_id`,`auth_role_id`),
  KEY `FK_user_role` (`auth_role_id`),
  CONSTRAINT `FK_auth_user` FOREIGN KEY (`auth_user_id`) REFERENCES `auth_user` (`auth_user_id`),
  CONSTRAINT `FK_auth_user_role` FOREIGN KEY (`auth_role_id`) REFERENCES `auth_role` (`auth_role_id`)
);

INSERT INTO auth_user (auth_user_id,first_name,last_name,email,password,status) values (1,'Jean','Dupont','admin@gmail.com','$2a$10$S7u40Wh58vH3kp5kmvx7WeX.JpuPubvFd9BTIGyVPAL/ngfRXSxDq','VERIFIED');

INSERT INTO auth_user_role (auth_user_id, auth_role_id) values ('1','1');
INSERT INTO auth_user_role (auth_user_id, auth_role_id) values ('1','2');
INSERT INTO auth_user_role (auth_user_id, auth_role_id) values ('1','3');

CREATE TABLE `city` (
  `city_id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`city_id`)
);

INSERT INTO city (city_id,name) values ('1','Paris');
INSERT INTO city (city_id,name) values ('2','Nice');
INSERT INTO city (city_id,name) values ('3','Lyon');
INSERT INTO city (city_id,name) values ('4','Marseille');
INSERT INTO city (city_id,name) values ('5','Lille');

CREATE TABLE `survey` (
  `survey_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `date_one` date DEFAULT NULL,
  `activity` varchar(45) DEFAULT NULL,
  `attendance` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `city_id_one` int DEFAULT NULL,
  `city_id_two` int DEFAULT NULL,
  `city_id_three` int DEFAULT NULL,
  `date_two` date DEFAULT NULL,
  `date_three` date DEFAULT NULL,
  PRIMARY KEY (`survey_id`),
  KEY `FK_user_id_idx` (`user_id`),
  KEY `FK_city_id_idx` (`city_id_one`),
  KEY `FK_city_id_one_idx` (`city_id_two`),
  KEY `FK_city_id_three_idx` (`city_id_three`),
  CONSTRAINT `FK_city_id_one` FOREIGN KEY (`city_id_one`) REFERENCES `city` (`city_id`),
  CONSTRAINT `FK_city_id_three` FOREIGN KEY (`city_id_three`) REFERENCES `city` (`city_id`),
  CONSTRAINT `FK_city_id_two` FOREIGN KEY (`city_id_two`) REFERENCES `city` (`city_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`auth_user_id`) ON DELETE CASCADE
);

CREATE TABLE `vote` (
  `vote_id` int NOT NULL AUTO_INCREMENT,
  `survey_id` int DEFAULT NULL,
  `date_one_vote` int DEFAULT '0',
  `date_two_vote` int DEFAULT '0',
  `date_three_vote` int DEFAULT '0',
  `city_one_vote` int DEFAULT '0',
  `city_two_vote` int DEFAULT '0',
  `city_three_vote` int DEFAULT '0',
  PRIMARY KEY (`vote_id`),
  KEY `FK_survey_id_idx` (`survey_id`),
  CONSTRAINT `FK_survey_id` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`survey_id`) ON DELETE CASCADE
);

INSERT INTO survey VALUES('1','Travis Scott à Paris !','2021-06-01','Concert','100','1','1','2','3','2021-06-01','2022-06-08');
INSERT INTO survey VALUES('2','Révisions avant les partiels','2020-12-17','Etudes','6','1','1','2','5','2022-06-01','2021-06-08');
INSERT INTO survey VALUES('3','Marche contre la discrimination','2021-02-01','Manifestation','50','1','4','5','1','2022-06-01','2021-06-08');
INSERT INTO survey VALUES('4','Repas de Noël','2020-12-05','Dinner','15','2','1','3','4','2021-06-01','2022-06-08');


INSERT INTO vote VALUES('1','1','0','0','0','0','0','0');
INSERT INTO vote VALUES('2','2','0','0','0','0','0','0');
INSERT INTO vote VALUES('3','3','0','0','0','0','0','0');
INSERT INTO vote VALUES('4','4','0','0','0','0','0','0');

