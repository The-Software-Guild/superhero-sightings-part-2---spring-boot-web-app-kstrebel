package com.sg.superherosightings.dao;

import models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

@SpringBootTest
public class AddressesDaoDBTest {

    @Autowired
    AddressesDao addressesDao;

    @Autowired
    HeroesDao heroesDao;

    @Autowired
    LocationsDao locationsDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    SightingsDao sightingsDao;

    @BeforeEach
    void setUp() {
        List<Address> addresses = addressesDao.getAllAddresses();
        for(Address address : addresses) {
            addressesDao.deleteAddressByID(address.getId());
        }

        List<Hero> heroes = heroesDao.getAllHeroes();
        for(Hero hero : heroes) {
            heroesDao.deleteHeroesByID(hero.getHeroID());
        }

        List<Location> locations = locationsDao.getAllLocations();
        for(Location location : locations) {
            locationsDao.deleteLocationByID(location.getLocationID());
        }

        List<Organization> organizations = organizationsDao.getAllOrganizations();
        for(Organization organization : organizations) {
            organizationsDao.deleteOrganizationByID(organization.getOrganizationID());
        }

        List<Sighting> sightings = sightingsDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingsDao.deleteSightingByID(sighting.getSightingID());
            // sighting doesn't have an ID - it's a composite key. We should either change it to have an ID or find a different way to delete it.
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAddressesByID() {
    }

    @Test
    void getAllAddresses() {
    }

    @Test
    void addAddresses() {
    }

    @Test
    void updateAddresses() {
    }

    @Test
    void deleteAddressByID() {
    }
}