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

INSERT INTO auth_user (auth_user_id,first_name,last_name,email,password,status) values (1,'Ankit','Wasankar','admin@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED');

INSERT INTO auth_user_role (auth_user_id, auth_role_id) values ('1','1');
INSERT INTO auth_user_role (auth_user_id, auth_role_id) values ('1','2');
INSERT INTO auth_user_role (auth_user_id, auth_role_id) values ('1','3');

INSERT INTO city (city_id,name) values ('1','Paris');
INSERT INTO city (city_id,name) values ('2','Nice');
INSERT INTO city (city_id,name) values ('3','Lyon');
INSERT INTO city (city_id,name) values ('4','Marseille');
INSERT INTO city (city_id,name) values ('5','Lille');

INSERT INTO survey (survey_id,name,date,activity,attendance,user_id,city_id) values ('1','Test',NULL,'Football','22','1','5');