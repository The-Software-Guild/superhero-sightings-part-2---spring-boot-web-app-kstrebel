DROP DATABASE IF EXISTS superheroDB;
CREATE DATABASE superheroDB;
USE superheroDB;

DROP TABLE IF EXISTS heroes;
CREATE TABLE heroes (
    heroID INT AUTO_INCREMENT,
    heroName VARCHAR(25) NOT NULL,
    heroDescription VARCHAR(50) NOT NULL,
    superPower VARCHAR(25) NOT NULL,
    CONSTRAINT pk_heroes
        PRIMARY KEY (heroID)
);

DROP TABLE IF EXISTS addresses;
CREATE TABLE addresses (
    addressID INT AUTO_INCREMENT,
    addressLine1 VARCHAR(30) NOT NULL,
    addressLine2 VARCHAR(30),
    city VARCHAR(50) NOT NULL,
    stateAbbreviation CHAR(2) NOT NULL,
    zip CHAR(5),
    CONSTRAINT pk_addresses
        PRIMARY KEY (addressID)
);

DROP TABLE IF EXISTS locations; 
CREATE TABLE locations (
    locationID INT AUTO_INCREMENT,
    locationName VARCHAR(50) NOT NULL,
    locationDescription VARCHAR(100) NOT NULL,
    addressID INT NOT NULL,
    locationLatitude FLOAT(10,6) NOT NULL,
    locationLongitude FLOAT(10,6) NOT NULL,
    CONSTRAINT pk_locations
        PRIMARY KEY (locationID),
	CONSTRAINT fk_locations_addresses
		FOREIGN KEY (addressID)
        REFERENCES addresses (addressID)
);

DROP TABLE IF EXISTS sightings;
CREATE TABLE sightings (
    sightingsID INT AUTO_INCREMENT
    heroID INT, 
    locationID INT,
    dateOfSighting DATE,
    CONSTRAINT pk_sightings
        PRIMARY KEY (sightingsID),
    CONSTRAINT fk_sightings_heroes
        FOREIGN KEY (heroID)
        REFERENCES heroes (heroID),
    CONSTRAINT fk_sightings_locations
        FOREIGN KEY (locationID)
        REFERENCES locations (locationID)
);

DROP TABLE IF EXISTS organizations;
CREATE TABLE organizations (
    organizationID INT AUTO_INCREMENT,
    organizationName VARCHAR(50) NOT NULL,
    organizationDescription VARCHAR(100) NOT NULL,
    addressID INT NOT NULL,
    CONSTRAINT pk_organizations
        PRIMARY KEY (organizationID),
	CONSTRAINT fk_organizations_addresses
		FOREIGN KEY (addressID)
		REFERENCES addresses (addressID)
);

DROP TABLE IF EXISTS members;
CREATE TABLE members(
    heroID INT,
    organizationID INT,
    CONSTRAINT pk_members
        PRIMARY KEY (heroID, organizationID),
    CONSTRAINT fk_members_heroes
        FOREIGN KEY (heroID)
        REFERENCES heroes (heroID),
    CONSTRAINT fk_members_organizations
        FOREIGN KEY (organizationID)
        REFERENCES organizations (organizationID)
);
