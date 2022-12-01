package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        List<Address> addresses = addressesDao.getAllAddresses();
        for (Address address : addresses) {
            addressesDao.deleteAddressByID(address.getId());
        }

        List<Hero> heroes = heroesDao.getAllHeroes();
        for (Hero hero : heroes) {
            heroesDao.deleteHeroesByID(hero.getHeroID());
        }

        List<Organization> organizations = organizationsDao.getAllOrganizations();
        for (Organization organization : organizations) {
            organizationsDao.deleteOrganizationByID(organization.getOrganizationID());
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
        organization2.setAddress(address);
        organization2.setOrganizationDescription("Test org description 2");
        organization2.setMembers(heroes);
        organization2 = organizationsDao.addOrganization(organization2);

        List<Organization> organizations = organizationsDao.getAllOrganizations();
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));
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
        assertEquals(organization,fromDao);
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

//    @Test
//    public void testGetOrganizationsForHero() {
//        Address address = new Address();
//        address.setAddressLine1("Test addressLine1");
//        address.setCity("Test city");
//        address.setStateAbbreviation("TE");
//        address.setZip("11111");
//        address = addressesDao.addAddresses(address);
//
//        Hero hero = new Hero();
//        hero.setHeroName("Test Hero Name");
//        hero.setHeroDescription("Test description");
//        hero.setSuperPower("Test Superpower");
//        hero = heroesDao.addHeroes(hero);
//
//        Hero hero2 = new Hero();
//        hero2.setHeroName("Test Hero Name 2");
//        hero2.setHeroDescription("Test description 2");
//        hero2.setSuperPower("Test Superpower 2");
//        hero2 = heroesDao.addHeroes(hero2);
//
//        List<Hero> heroes = new ArrayList<>();
//        heroes.add(hero);
//
//        List<Hero> heroes2 = new ArrayList<>();
//        heroes.add(hero2);
//
//        Organization organization = new Organization();
//        organization.setOrganizationName("Test org");
//        organization.setAddress(address);
//        organization.setOrganizationDescription("Test org description");
//        organization.setMembers(heroes);
//        organization = organizationsDao.addOrganization(organization);
//
//        Organization organization2 = new Organization();
//        organization2.setOrganizationName("Test org 2");
//        organization2.setAddress(address);
//        organization2.setOrganizationDescription("Test org description 2");
//        organization2.setMembers(heroes2);
//        organization2 = organizationsDao.addOrganization(organization2);
//
//        Organization organization3 = new Organization();
//        organization3.setOrganizationName("Test org 3");
//        organization3.setAddress(address);
//        organization3.setOrganizationDescription("Test org description 3");
//        organization3.setMembers(heroes);
//        organization3 = organizationsDao.addOrganization(organization3);
//
//        List<Organization> organizations = organizationsDao.getMembersForOrganization(hero);
//        assertEquals(2, organizations.size());
//        assertTrue(organizations.contains(organization));
//        assertFalse(organizations.contains(organization2));
//        assertTrue(organizations.contains(organization3));
//    }
}