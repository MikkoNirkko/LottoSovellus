DROP TABLE lottorivi;
DROP TABLE user;

CREATE TABLE lottorivi(
id INT NOT NULL AUTO_INCREMENT,
playname CHAR(30),
row CHAR(50),
wins SMALLINT,
PRIMARY KEY (id)
);


CREATE TABLE user(
id INT NOT NULL AUTO_INCREMENT,
username CHAR(30),
password CHAR(70),
role CHAR(10),
PRIMARY KEY (id)
);