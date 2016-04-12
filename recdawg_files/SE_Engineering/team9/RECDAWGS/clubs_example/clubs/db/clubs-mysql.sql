#
# this SQL file creates the schema for the clubs database
#
# remove the existing tables
#
DROP TABLE IF EXISTS membership;
DROP TABLE IF EXISTS club;
DROP TABLE IF EXISTS person;

#
# Table definition for table 'person'
#
CREATE TABLE person (
  id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(255) NOT NULL UNIQUE,
  userpass    VARCHAR(255) NOT NULL,
  email       VARCHAR(255) NOT NULL,
  firstname   VARCHAR(255) NOT NULL,
  lastname    VARCHAR(255) NOT NULL,
  address     VARCHAR(255),
  phone       VARCHAR(255)
);

#
# Table definition for table 'club'
#
CREATE TABLE club (
  id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(255) NOT NULL UNIQUE,
  address     VARCHAR(255),
  established DATETIME,
  founderid   INT UNSIGNED NOT NULL,
  
  FOREIGN KEY (founderid) REFERENCES person(id) 
);

#
# Table definition for table 'membership', which
# is a many-to-many relationship
# between person and club
#
CREATE TABLE membership (
  id          INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  personid    INT UNSIGNED NOT NULL,
  clubid      INT UNSIGNED NOT NULL,
  joined      DATETIME,
  
  FOREIGN KEY (personid) REFERENCES person(id) ON DELETE CASCADE,
  FOREIGN KEY (clubid) REFERENCES club(id) ON DELETE CASCADE
);

#
# insert an administrator person
#
INSERT INTO person ( username, userpass, email, firstname, lastname, address, phone )
VALUES ( 'admin', 'admin', 'admin@host.com', 
         'Joe', 'Administrator', 'Administrative Bldg.', '(555) 333-1234' );