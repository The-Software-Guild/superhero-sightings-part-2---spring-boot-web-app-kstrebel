USE superheroDBtest;

INSERT INTO heroes (heroName, heroDescription, superPower) VALUES
	('Wonder Woman', 'All-American Amazonian Princess', 'Lasso of Truth'),
    ('Spider Man', 'Man bit by spider, now wears latex', 'Shoots webs'),
    ('Wolverine', 'Hugh Jackman with mutton chops', 'Heals instantaneously');

SELECT * FROM heroes;

INSERT INTO addresses (addressLine1, addressLine2, city, stateAbbreviation, zip) VALUES
	('4000 Wisconsin Ave', null, 'Hero City', 'MO', '11111'),
    ('3000 Texas Ave', null, 'Hero City', 'MO', '11112'),
    ('2000 California Ave', null, 'Hero City', 'MO', '11113'),
    ('1000 Nebraska St', 'Suite 6', 'Hero City', 'MO', '11114'),
    ('500 Oregon Ave', 'Suite 5', 'Hero City', 'MO', '11115'),
    ('100 Pennsylvania Ave', 'Suite 4', 'Hero City', 'MO', '11116');

SELECT * FROM addresses;

INSERT INTO locations (locationName, locationDescription, addressID, locationLatitude, locationLongitude) VALUES
	('Grocery store', 'Place to buy food', 1, 30, 30),
    ('Library', 'Place to get books', 2, 31, 31),
    ('Park', 'Place to go on a walk', 3, 32, 32);

SELECT * FROM locations;

INSERT INTO sightings (heroID, locationID, dateOfSighting) VALUES
	(1, 1, '2022-11-28'),
    (1, 2, '2022-11-28'),
    (1, 1, '2022-11-29'),
    (2, 3, '2022-11-29'),
    (3, 2, '2022-11-29');

SELECT * FROM sightings;

INSERT INTO organizations (organizationName, organizationDescription, addressID) VALUES
	('Association of Heroic Women', 'place for women who are superheroes', 4),
    ('Association of Heroes with Animal Names', 'place for heroes with animal names', 5),
    ('Association of Heroes', 'place for all heroes', 6);

SELECT * FROM organizations;

INSERT INTO members (heroID, organizationID) VALUES
	(1, 1),
    (1, 3),
    (2, 2),
    (2, 3),
    (3, 2),
    (3, 3);

SELECT * FROM members;

