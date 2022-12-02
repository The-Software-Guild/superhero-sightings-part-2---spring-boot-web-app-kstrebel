package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    void testAddAndGetSighting() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setAddressLine2("Test addressLine2");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero");
        hero.setHeroDescription("Test Hero Description");
        hero.setSuperPower("Test superpower");
        hero = heroesDao.addHeroes(hero);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting.setDateOfSighting(LocalDate.parse("2022-01-01"));
        sighting = sightingsDao.addSighting(sighting);

        Sighting fromDao = sightingsDao.getSightingByID(sighting.getSightingID());

//        assertEquals(sighting.getSightingID(),fromDao.getSightingID());
//        assertEquals(sighting.getLocation().getLocationID(),fromDao.getLocation().getLocationID());
//        assertEquals(sighting.getHero(),fromDao.getHero());
        assertEquals(sighting,fromDao);
    }

    @Test
    void getAllSightings() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setAddressLine2("Test addressLine2");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero");
        hero.setHeroDescription("Test Hero Description");
        hero.setSuperPower("Test superpower");
        hero = heroesDao.addHeroes(hero);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting.setDateOfSighting(LocalDate.parse("2022-01-01"));
        sighting = sightingsDao.addSighting(sighting);

        Sighting fromDao = sightingsDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting,fromDao);

        Address address2 = new Address();
        address2.setAddressLine1("Test addressLine1 2");
        address2.setAddressLine2("Test addressLine2 2");
        address2.setCity("Test city 2");
        address2.setStateAbbreviation("T2");
        address2.setZip("11112");
        address2 = addressesDao.addAddresses(address2);

        Location location2 = new Location();
        location2.setAddress(address2);
        location2.setLocationName("Test Location name 2");
        location2.setLocationDescription("Test Location Description 2");
        location2.setLocationLatitude(Float.parseFloat("38.907192"));
        location2.setLocationLongitude(Float.parseFloat("-77.036873"));
        location2 = locationsDao.addLocation(location2);

        Sighting sighting2 = new Sighting();
        sighting2.setHero(hero);
        sighting2.setLocation(location2);
        sighting2.setDateOfSighting(LocalDate.parse("2022-01-01"));
        sighting2 = sightingsDao.addSighting(sighting2);

        Sighting fromDao2 = sightingsDao.getSightingByID(sighting2.getSightingID());
        assertEquals(sighting2,fromDao2);

        List<Sighting> sightings = sightingsDao.getAllSightings();
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    @Test
    void associateLocationsForSightings() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setAddressLine2("Test addressLine2");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero");
        hero.setHeroDescription("Test Hero Description");
        hero.setSuperPower("Test superpower");
        hero = heroesDao.addHeroes(hero);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting.setDateOfSighting(LocalDate.parse("2022-01-01"));
        sighting = sightingsDao.addSighting(sighting);

        Sighting fromDao = sightingsDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting,fromDao);
    }

    @Test
    void updateSighting() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setAddressLine2("Test addressLine2");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero");
        hero.setHeroDescription("Test Hero Description");
        hero.setSuperPower("Test superpower");
        hero = heroesDao.addHeroes(hero);

        Hero hero2 = new Hero();
        hero2.setHeroName("Test Hero 2");
        hero2.setHeroDescription("Test Hero Description");
        hero2.setSuperPower("Test superpower");
        hero2 = heroesDao.addHeroes(hero2);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting.setDateOfSighting(LocalDate.parse("2022-01-01"));
        sighting = sightingsDao.addSighting(sighting);

        Sighting fromDao = sightingsDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting,fromDao);

        sighting.setHero(hero2);
        sightingsDao.updateSighting(sighting);

        assertNotEquals(fromDao, sighting);

        fromDao = sightingsDao.getSightingByID(sighting.getSightingID());
        assertEquals(fromDao, sighting);
    }

    @Test
    void deleteSightingByID() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setAddressLine2("Test addressLine2");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero");
        hero.setHeroDescription("Test Hero Description");
        hero.setSuperPower("Test superpower");
        hero = heroesDao.addHeroes(hero);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting.setDateOfSighting(LocalDate.parse("2022-01-01"));
        sighting = sightingsDao.addSighting(sighting);

        sightingsDao.deleteSightingByID(sighting.getSightingID());
        Sighting fromDao = sightingsDao.getSightingByID(sighting.getSightingID());
        assertNull(fromDao);
    }

    @Test
    void getSightingsForLocation() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setAddressLine2("Test addressLine2");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Location location = new Location();
        location.setAddress(address);
        location.setLocationName("Test Location name");
        location.setLocationDescription("Test Location Description");
        location.setLocationLatitude(Float.parseFloat("38.907192"));
        location.setLocationLongitude(Float.parseFloat("-77.036873"));
        location = locationsDao.addLocation(location);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero");
        hero.setHeroDescription("Test Hero Description");
        hero.setSuperPower("Test superpower");
        hero = heroesDao.addHeroes(hero);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting.setDateOfSighting(LocalDate.parse("2022-01-01"));
        sighting = sightingsDao.addSighting(sighting);

        Sighting fromDao = sightingsDao.getSightingByID(sighting.getSightingID());
        assertEquals(sighting,fromDao);
    }
}