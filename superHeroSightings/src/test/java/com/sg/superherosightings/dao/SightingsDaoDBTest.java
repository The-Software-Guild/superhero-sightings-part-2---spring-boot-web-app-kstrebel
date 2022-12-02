package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class SightingsDaoDBTest {

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
        List<Organization> organizations = organizationsDao.getAllOrganizations();
        for (Organization organization : organizations) {
            organizationsDao.deleteOrganizationByID(organization.getOrganizationID());
        }

        List<Hero> heroes = heroesDao.getAllHeroes();
        for (Hero hero : heroes) {
            heroesDao.deleteHeroesByID(hero.getHeroID());
        }

        List<Sighting> sightingList = sightingsDao.getAllSightings();
        for(Sighting sighting : sightingList){
            sightingsDao.deleteSightingByID(sighting.getSightingID());
        }

        List<Location> locationList = locationsDao.getAllLocations();
        for(Location location : locationList){
            locationsDao.deleteLocationByID(location.getLocationID());
        }

        List<Address> addresses = addressesDao.getAllAddresses();
        for (Address address : addresses) {
            addressesDao.deleteAddressByID(address.getAddressID());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSightingByID() {
    }

    @Test
    void getAllSightings() {
    }

    @Test
    void associateLocationsForSightings() {
    }

    @Test
    void addSighting() {
    }

    @Test
    void updateSighting() {
    }

    @Test
    void deleteSightingByID() {
    }

    @Test
    void getSightingsForLocation() {
    }
}