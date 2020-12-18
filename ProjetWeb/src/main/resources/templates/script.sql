CREATE TABLE auth_user (
  auth_user_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  status varchar(255) DEFAULT NULL,
  PRIMARY KEY (`auth_user_id`)
)