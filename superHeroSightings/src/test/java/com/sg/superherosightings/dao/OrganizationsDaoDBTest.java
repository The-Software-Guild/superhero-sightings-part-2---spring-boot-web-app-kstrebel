package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrganizationsDaoDBTest {

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
    void testAddAndGetOrganizationByID() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero Name");
        hero.setHeroDescription("Test description");
        hero.setSuperPower("Test Superpower");
        hero = heroesDao.addHeroes(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization organization = new Organization();
        organization.setOrganizationName("Test org");
        organization.setAddress(address);
        organization.setOrganizationDescription("Test org description");
        organization.setMembers(heroes);
        organization = organizationsDao.addOrganization(organization);

        Organization fromDao = organizationsDao.getOrganizationByID(organization.getOrganizationID());
        int memberNum = fromDao.getMembers().size();
        assertEquals(1, memberNum);
        assertEquals("Test org", fromDao.getOrganizationName());
        assertEquals(organization, fromDao);
    }

    @Test
    void testGetAllOrganizations() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Address address2 = new Address();
        address2.setAddressLine1("Test addressLine1 2");
        address2.setCity("Test city 2");
        address2.setStateAbbreviation("T2");
        address2.setZip("11112");
        address2 = addressesDao.addAddresses(address2);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero Name");
        hero.setHeroDescription("Test description");
        hero.setSuperPower("Test Superpower");
        hero = heroesDao.addHeroes(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization organization = new Organization();
        organization.setOrganizationName("Test org");
        organization.setAddress(address);
        organization.setOrganizationDescription("Test org description");
        organization.setMembers(heroes);
        organization = organizationsDao.addOrganization(organization);

        Organization organization2 = new Organization();
        organization2.setOrganizationName("Test org 2");
        organization2.setAddress(address2);
        organization2.setOrganizationDescription("Test org description 2");
        organization2.setMembers(heroes);
        organization2 = organizationsDao.addOrganization(organization2);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        organizations.add(organization2);

        List<Organization> fromDao = organizationsDao.getAllOrganizations();
        assertEquals(2, fromDao.size());
        assertTrue(fromDao.contains(organization));
        assertTrue(fromDao.contains(organization2));
    }

    @Test
    void updateOrganization() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero Name");
        hero.setHeroDescription("Test description");
        hero.setSuperPower("Test Superpower");
        hero = heroesDao.addHeroes(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization organization = new Organization();
        organization.setOrganizationName("Test org");
        organization.setAddress(address);
        organization.setOrganizationDescription("Test org description");
        organization.setMembers(heroes);
        organization = organizationsDao.addOrganization(organization);

        Organization fromDao = organizationsDao.getOrganizationByID(organization.getOrganizationID());
        assertEquals(organization, fromDao);

        organization.setOrganizationName("New organization name");
        Hero hero2 = new Hero();
        hero.setHeroName("Test Hero Name 2");
        hero.setHeroDescription("Test description 2");
        hero.setSuperPower("Test Superpower 2");
        heroes.add(hero2);
        organization.setMembers(heroes);

        organizationsDao.updateOrganization(organization);

        assertNotEquals(organization, fromDao);

        fromDao = organizationsDao.getOrganizationByID(organization.getOrganizationID());
        assertEquals(organization, fromDao);
    }

    @Test
    void deleteOrganizationByID() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero Name");
        hero.setHeroDescription("Test description");
        hero.setSuperPower("Test Superpower");
        hero = heroesDao.addHeroes(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization organization = new Organization();
        organization.setOrganizationName("Test org");
        organization.setAddress(address);
        organization.setOrganizationDescription("Test org description");
        organization.setMembers(heroes);
        organization = organizationsDao.addOrganization(organization);

        Organization fromDao = organizationsDao.getOrganizationByID(organization.getOrganizationID());
        assertEquals(organization, fromDao);

        organizationsDao.deleteOrganizationByID(organization.getOrganizationID());

        fromDao = organizationsDao.getOrganizationByID(organization.getOrganizationID());
        assertNull(fromDao);
    }

    @Test
    public void testGetAddressForOrganization() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero Name");
        hero.setHeroDescription("Test description");
        hero.setSuperPower("Test Superpower");
        hero = heroesDao.addHeroes(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization organization = new Organization();
        organization.setOrganizationName("Test org");
        organization.setAddress(address);
        organization.setOrganizationDescription("Test org description");
        organization.setMembers(heroes);
        organization = organizationsDao.addOrganization(organization);

        Organization fromDao = organizationsDao.getOrganizationByID(organization.getOrganizationID());
        assertEquals(organization, fromDao);

        Address fromDaoAddress = organizationsDao.getAddressForOrganization(fromDao);

        assertEquals(address, fromDaoAddress);
    }

    @Test
    public void testGetMembersForOrganization() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero Name");
        hero.setHeroDescription("Test description");
        hero.setSuperPower("Test Superpower");
        hero = heroesDao.addHeroes(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization organization = new Organization();
        organization.setOrganizationName("Test org");
        organization.setAddress(address);
        organization.setOrganizationDescription("Test org description");
        organization.setMembers(heroes);
        organization = organizationsDao.addOrganization(organization);

        Organization fromDao = organizationsDao.getOrganizationByID(organization.getOrganizationID());
        assertEquals(organization, fromDao);

        List<Hero> fromDaoMembers = organizationsDao.getMembersForOrganization(fromDao);

        assertEquals(heroes, fromDaoMembers);
    }

    @Test
    public void testAddMembersAndAddressToOrganization() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Hero hero = new Hero();
        hero.setHeroName("Test Hero Name");
        hero.setHeroDescription("Test description");
        hero.setSuperPower("Test Superpower");
        hero = heroesDao.addHeroes(hero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization organization = new Organization();
        organization.setOrganizationName("Test org");
        organization.setAddress(address);
        organization.setOrganizationDescription("Test org description");
        organization.setMembers(heroes);
        organization = organizationsDao.addOrganization(organization);

        Organization fromDao = organizationsDao.getOrganizationByID(organization.getOrganizationID());
        assertEquals(organization, fromDao);

        Address fromDaoAddress = organizationsDao.getAddressForOrganization(fromDao);

        assertEquals(address, fromDaoAddress);

        List<Hero> fromDaoMembers = organizationsDao.getMembersForOrganization(fromDao);

        assertEquals(heroes, fromDaoMembers);


    }
}