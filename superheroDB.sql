DROP DATABASE IF EXISTS superheroDB;
CREATE DATABASE superheroDB;
USE superheroDB;

DROP TABLE IF EXISTS heros;
CREATE TABLE heros (
    heroId INT AUTO_INCREMENT,
    heroName VARCHAR(25) NOT NULL,
    heroDescription VARCHAR(50) NOT NULL,
    superPower VARCHAR(25) NOT NULL,
    CONSTRAINT pk_heros
        PRIMARY KEY (heroID)
);

DROP TABLE IF EXISTS locations; 
CREATE TABLE locations (
    locationId INT AUTO_INCREMENT,
    locationName VARCHAR(50) NOT NULL,
    locationAddress VARCHAR(50) NOT NULL,
    locationLatitude FLOAT(10,6) NOT NULL,
    locationLongitude FLOAT(10,6) NOT NULL,
    CONSTRAINT pk_locations
        PRIMARY KEY (locationId)
);

DROP TABLE IF EXISTS sightings;
CREATE TABLE sightings (
    heroId INT, 
    locationId INT,
    dateOfSighting DATE NOT NULL,
    CONSTRAINT pk_sightings
        PRIMARY KEY (heroId, locationId),
    CONSTRAINT fk_sightings_heros
        FOREIGN KEY (heroID)
        REFERENCES heros (heroId),
    CONSTRAINT fk_sightings_locations
        FOREIGN KEY (locationId)
        REFERENCES locations (locationId)
);

DROP TABLE IF EXISTS organizations;
CREATE TABLE organizations (
    organizationId INT AUTO_INCREMENT,
    organizationDescription VARCHAR(50) NOT NULL,
    organizationAddress VARCHAR(50) NOT NULL,
    CONSTRAINT pk_organizations
        PRIMARY KEY (organizationId)
);

DROP TABLE IF EXISTS members;
CREATE TABLE members(
    heroId INT,
    organizationId INT,
    CONSTRAINT pk_members
        PRIMARY KEY (heroID, organizationId),
    CONSTRAINT fk_members_heros
        FOREIGN KEY (heroID)
        REFERENCES heros (heroId),
    CONSTRAINT fk_members_organizations
        FOREIGN KEY (organizationId)
        REFERENCES organizations (organizationId)
);


