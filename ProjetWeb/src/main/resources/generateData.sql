DROP TABLE IF EXISTS auth_user_role;
DROP TABLE IF EXISTS auth_role;
DROP TABLE IF EXISTS auth_user;
DROP TABLE IF EXISTS survey;
DROP TABLE IF EXISTS city;

CREATE TABLE auth_role (
    auth_role_id int(11) NOT NULL AUTO_INCREMENT,
    role_name varchar(255) DEFAULT NULL,
    role_desc varchar(255) DEFAULT NULL,
    PRIMARY KEY (auth_role_id)
);

INSERT INTO auth_role VALUES (1,'SUPER_USER','This user has ultimate rights for everything');
INSERT INTO auth_role VALUES (2,'ADMIN_USER','This user has admin rights for administrative work');
INSERT INTO auth_role VALUES (3,'SITE_USER','This user has access to site, after login - normal user');

CREATE TABLE auth_user (
  auth_user_id int(11) NOTNULL AUTO_INCREMENT,
  first_name varchar(255) NOTNULL,
  last_name varchar(255) NOTNULL,
  email varchar(255) NOTNULL,
  password varchar(255) NOTNULL,
  status varchar(255),
  PRIMARY KEY (auth_user_id)
);

CREATE TABLE auth_user_role (
    auth_user_id int(11) NOTNULL,
    auth_role_id int(11) NOTNULL,
    PRIMARY KEY (auth_user_id,auth_role_id),
    KEY FK_user_role (auth_role_id),
    CONSTRAINT FK_auth_user FOREIGN KEY (auth_user_id) REFERENCES auth_user (auth_user_id),
    CONSTRAINT FK_auth_user_role FOREIGN KEY (auth_role_id) REFERENCES auth_role (auth_role_id)
);

CREATE TABLE city (
    city_id int(11) NOT NULL,
    name VARCHAR(45) NULL,
    PRIMARY KEY (`city_id`)
);

CREATE TABLE survey (
  survey_id int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  date DATE NULL,
  activity VARCHAR(45) NULL,
  attendance INT NULL,
  user_id INT NULL,
  city_id INT NULL,
  PRIMARY KEY (survey_id),
  INDEX `FK_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `FK_city_id_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT FK_user_id
    FOREIGN KEY (user_id)
    REFERENCES auth_user (auth_user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT FK_city_id
    FOREIGN KEY (city_id)
    REFERENCES city (city_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
 );

 ALTER TABLE `usermanagement`.`survey`
 DROP FOREIGN KEY `FK_city_id`;
 ALTER TABLE `usermanagement`.`survey`
 ADD COLUMN `city_id_two` INT NULL AFTER `city_id_one`,
 ADD COLUMN `city_id_three` INT NULL AFTER `city_id_two`,
 ADD COLUMN `city_id_four` INT NULL AFTER `city_id_three`,
 CHANGE COLUMN `city_id` `city_id_one` INT NULL DEFAULT NULL ,
 ADD INDEX `FK_city_id_one_idx` (`city_id_two` ASC) VISIBLE,
 ADD INDEX `FK_city_id_three_idx` (`city_id_three` ASC) VISIBLE,
 ADD INDEX `FK_city_id_three_idx1` (`city_id_four` ASC) VISIBLE;
 ;
 ALTER TABLE `usermanagement`.`survey`
 ADD CONSTRAINT `FK_city_id_one`
   FOREIGN KEY (`city_id_one`)
   REFERENCES `usermanagement`.`city` (`city_id`),
 ADD CONSTRAINT `FK_city_id_two`
   FOREIGN KEY (`city_id_two`)
   REFERENCES `usermanagement`.`city` (`city_id`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
 ADD CONSTRAINT `FK_city_id_three`
   FOREIGN KEY (`city_id_three`)
   REFERENCES `usermanagement`.`city` (`city_id`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION,
 ADD CONSTRAINT `FK_city_id_four`
   FOREIGN KEY (`city_id_four`)
   REFERENCES `usermanagement`.`city` (`city_id`)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION;


 CREATE TABLE `usermanagement`.`vote` (
   `vote_id` INT NOT NULL,
   `survey_id` INT NULL,
   `nb_vote` INT NULL DEFAULT 0,
   PRIMARY KEY (`vote_id`),
   INDEX `FK_survey_id_idx` (`survey_id` ASC) VISIBLE,
   CONSTRAINT `FK_survey_id`
     FOREIGN KEY (`survey_id`)
     REFERENCES `usermanagement`.`survey` (`survey_id`)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION);


INSERT INTO auth_user (auth_user_id,first_name,last_name,email,password,status) values (1,'Ankit','Wasankar','admin@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED');

INSERT INTO auth_user_role (auth_user_id, auth_role_id) values ('1','1');
INSERT INTO auth_user_role (auth_user_id, auth_role_id) values ('1','2');
INSERT INTO auth_user_role (auth_user_id, auth_role_id) values ('1','3');

INSERT INTO city (city_id,name) values ('1','Paris');
INSERT INTO city (city_id,name) values ('2','Nice');
INSERT INTO city (city_id,name) values ('3','Lyon');
INSERT INTO city (city_id,name) values ('4','Marseille');
INSERT INTO city (city_id,name) values ('5','Lille');

INSERT INTO survey VALUES('1','Travis Scott à Paris !','2021-06-01','Concert','100','1','1','2','3','2021-06-01','2022-06-08');
INSERT INTO survey VALUES('2','Prêt pour un five ?','2021-01-07','Football','10','1','2','4','1','2021-06-01','2022-06-08');
INSERT INTO survey VALUES('3','Découverte nature','2021-03-17','Randonnée','8','1','3','5','2','2021-06-01','2022-06-08');
INSERT INTO survey VALUES('4','Concours de dunk !','2021-02-11','Basketball','8','1','1','4','2','2021-06-01','2022-06-08');
INSERT INTO survey VALUES('5','Barbapapa à volonté','2020-12-27','Fête foraine','5','1','5','3','1','2021-06-01','2022-06-08');
INSERT INTO survey VALUES('6','Révisions avant les partiels','2020-12-17','Etudes','6','1','1','2','5','2022-06-01','2021-06-08');
INSERT INTO survey VALUES('7','Marche contre la discrimination','2021-02-01','Manifestation','50','1','4','5','1','2022-06-01','2021-06-08');
INSERT INTO survey VALUES('8','Repas de Noël','2020-12-05','Dinner','15','2','1','3','4','2021-06-01','2022-06-08');
INSERT INTO survey VALUES('9','Visio sur Skype','2020-09-06','Appel','3','2','4','2','5','2021-06-01','2022-06-08');

