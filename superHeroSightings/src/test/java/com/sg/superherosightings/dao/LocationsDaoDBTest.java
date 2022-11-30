package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocationsDaoDBTest {
    @Autowired
    HeroesDao heroesDao;

    @Autowired
    OrganizationsDao organizationsDao;

    @Autowired
    SightingsDao sightingsDao;

    @Autowired
    AddressesDao addressesDao;

    @Autowired
    LocationsDao locationsDao;

    public LocationsDaoDBTest(){

    }

    @BeforeEach
    void setUp(){
        List<Location> locationList = locationsDao.getAllLocations();
        for(Location location : locationList){
            locationsDao.deleteLocationByID(location.getLocationID());
        }
    }

    @Test
    void testAddGetLocationByID() {
        Address address = new Address();
        address.setAddressLine1("Test Address Line 1");
        address.setAddressLine2("Test Address Line 2");
        address.setCity("Test city");
        address.setStateAbbreviation("Test");
        address.setZip("Test Zip");
        address = addressesDao.addAddresses(address);


        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Location fromDao = locationsDao.getLocationByID(location.getLocationID());
        assertEquals(location, fromDao);
    }

    @Test
    void getAllLocations() {
        Address address = new Address();
        address.setAddressLine1("Test Address Line 1");
        address.setAddressLine2("Test Address Line 2");
        address.setCity("Test city");
        address.setStateAbbreviation("Test");
        address.setZip("Test Zip");
        address = addressesDao.addAddresses(address);

        Address address2 = new Address();
        address2.setAddressLine1("2Test Address Line 1");
        address2.setAddressLine2("2Test Address Line 2");
        address2.setCity("Test city 2");
        address2.setStateAbbreviation("Te2");
        address2.setZip("Test Zip 2");
        address2 = addressesDao.addAddresses(address2);


        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Location location2 = new Location();
        location2.setAddress(address2);
        location2.setLocationName("Test Location name 2");
        location2.setLocationDescription("Test Location Description 2");
        location2.setLocationLatitude(Float.parseFloat("38.907192"));
        location2.setLocationLongitude(Float.parseFloat("-77.036873"));
        location2 = locationsDao.addLocation(location2);

        List<Location> locations = locationsDao.getAllLocations();

        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));

    }

    @Test
    void updateLocation() {
        Address address = new Address();
        address.setAddressLine1("Test Address Line 1");
        address.setAddressLine2("Test Address Line 2");
        address.setCity("Test city");
        address.setStateAbbreviation("Test");
        address.setZip("Test Zip");
        address = addressesDao.addAddresses(address);


        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Location fromDao = locationsDao.getLocationByID(location.getLocationID());
        assertEquals(location, fromDao);

        location.setLocationName("Another Test Name");
        locationsDao.updateLocation(location);

        assertNotEquals(fromDao, location);

        fromDao = locationsDao.getLocationByID(location.getLocationID());
        assertEquals(fromDao, location);
    }

    @Test
    void deleteLocationByID() {
        Hero hero = new Hero();
        hero.setHeroName("Test Hero");
        hero.setHeroDescription("Test Hero Description");
        hero.setSuperPower("Test superpower");
        hero = heroesDao.addHeroes(hero);

        Address address = new Address();
        address.setAddressLine1("Test Address Line 1");
        address.setAddressLine2("Test Address Line 2");
        address.setCity("Test city");
        address.setStateAbbreviation("Test");
        address.setZip("Test Zip");
        address = addressesDao.addAddresses(address);

        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting.setDateOfSighting(LocalDate.of(2020, 2, 2));
        sighting = sightingsDao.addSighting(sighting);

        locationsDao.deleteLocationByID(location.getLocationID());
        Location fromDao = locationsDao.getLocationByID(location.getLocationID());

        assertNull(fromDao);

    }

    @Test
    void getAddressForLocation() {
        Address address = new Address();
        address.setAddressLine1("Test Address Line 1");
        address.setAddressLine2("Test Address Line 2");
        address.setCity("Test city");
        address.setStateAbbreviation("Test");
        address.setZip("Test Zip");
        address = addressesDao.addAddresses(address);

        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        assertEquals(address, locationsDao.getAddressForLocation(location));
    }

}