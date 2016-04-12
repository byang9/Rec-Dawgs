#
# this SQL file creates the schema for the recdawgs database
#
# remove the existing tables
#
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS membership;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS league;
DROP TABLE IF EXISTS matchup;
DROP TABLE IF EXISTS scorereport;
DROP TABLE IF EXISTS hasVenue;
DROP TABLE IF EXISTS venue;
DROP TABLE IF EXISTS round;
#
# Table definition for table 'person'
#
CREATE TABLE person (
    id              INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    firstname       VARCHAR(255) NOT NULL,
    lastname        VARCHAR(255) NOT NULL,
    username        VARCHAR(255) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    isStudent       BOOLEAN NOT NULL,
    studentID       INT UNSIGNED,
    address         VARCHAR(255),
    phone           VARCHAR(255)
);

#
# Table definition for table 'team'
#
CREATE TABLE team (
    id              INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(255) NOT NULL UNIQUE,
    leagueid        INT UNSIGNED NOT NULL,
    established     DATETIME,
    captainid       INT UNSIGNED NOT NULL,
  
    FOREIGN KEY (captainid) REFERENCES person(id), 
    FOREIGN KEY (leagueid) REFERENCES league(id)
);

#
# Table definition for table 'membership', which
# is a many-to-many relationship
# between person and team
#
CREATE TABLE membership (
    id              INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    personid        INT UNSIGNED NOT NULL,
    teamid          INT UNSIGNED NOT NULL,
  
    FOREIGN KEY (personid) REFERENCES person(id) ON DELETE CASCADE,
    FOREIGN KEY (teamid) REFERENCES team(id) ON DELETE CASCADE
);

#
# Table definition for table 'league'
#
CREATE TABLE league (
    id    	        INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name  	        VARCHAR(255) NOT NULL UNIQUE,
    winnerId        INT UNSIGNED,
    isIndoor        BOOLEAN NOT NULL,
    minTeams        INT UNSIGNED NOT NULL,
    maxTeams        INT UNSIGNED NOT NULL,
    minTeamMembers  INT UNSIGNED NOT NULL,
    maxTeamMembers  INT UNSIGNED NOT NULL,
    matchRules      TEXT NOT NULL,
    leagueRules     TEXT NOT NULL,

    FOREIGN KEY (winnerId) REFERENCES team(id) 
);

#
# Table definition for table 'matchup'
#
CREATE TABLE matchup (
    id    	        INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    homeTeamId      INT UNSIGNED NOT NULL,
    awayTeamId      INT UNSIGNED NOT NULL,
    homePoints      INT UNSIGNED,
    awayPoints      INT UNSIGNED,
    matchDate       DATETIME,
    isCompleted     BOOL NOT NULL,

    FOREIGN KEY (homeTeamId) REFERENCES team(id),
    FOREIGN KEY (awayTeamId) REFERENCES team(id)
);

#
# Table definition for table 'scorereport'
#
CREATE TABLE scorereport (
    matchId			INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    homeTeamId		INT UNSIGNED NOT NULL,
    awayTeamId		INT UNSIGNED NOT NULL,
    homePoints	    INT UNSIGNED NOT NULL,
    awayPoints		INT UNSIGNED NOT NULL,
    matchDate		DATETIME NOT NULL,
    studentID       INT UNSIGNED NOT NULL,

    FOREIGN KEY (matchId) REFERENCES matchup(id),
    FOREIGN KEY (studentID) REFERENCES person(id)
);

#
# Table definition for table 'round'
#
CREATE TABLE round (
    leagueid        INT UNSIGNED,
    roundNo         INT UNSIGNED,
    PRIMARY KEY     (leagueid, roundNo),  
    FOREIGN KEY     (leagueid) REFERENCES league(id) ON DELETE CASCADE
);

#
# Table definition for table 'venue'
#
CREATE TABLE venue (
    id   	      INT UNSIGNED PRIMARY KEY,
    name          VARCHAR(255) NOT NULL UNIQUE,
    address       VARCHAR(255) NOT NULL UNIQUE,
    isIndoor      BOOLEAN NOT NULL
);

#
# Table definition for table 'hasVenue'
#
CREATE TABLE hasVenue (
    id   	      INT UNSIGNED PRIMARY KEY,
    leagueid      INT UNSIGNED NOT NULL,
    venueid       INT UNSIGNED NOT NULL,
  
  FOREIGN KEY (leagueid) REFERENCES league(id),
  FOREIGN KEY (venueid) REFERENCES venue(id)
);