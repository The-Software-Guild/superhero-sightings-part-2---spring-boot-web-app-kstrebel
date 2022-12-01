// package com.sg.superherosightings.dao;

// import com.sg.superherosightings.TestApplicationConfiguration;
// import com.sg.superherosightings.models.*;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.runner.RunWith;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.context.junit4.SpringRunner;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = TestApplicationConfiguration.class)
// class HeroesDaoDBTest {

//     @Autowired
//     HeroesDao heroesDao;

//     @Autowired
//     OrganizationsDao organizationsDao;

//     @Autowired
//     SightingsDao sightingsDao;

//     @Autowired
//     AddressesDao addressesDao;

//     @Autowired
//     LocationsDao locationsDao;

//     public HeroesDaoDBTest(){

//     }

//     @BeforeEach
//     public void setUp(){
//         List<Hero> heroList = heroesDao.getAllHeroes();
//         for(Hero hero : heroList){
//             heroesDao.deleteHeroesByID(hero.getHeroID());
//         }

//         List<Organization> organizationList = organizationsDao.getAllOrganizations();
//         for(Organization organization : organizationList){
//             organizationsDao.deleteOrganizationByID(organization.getOrganizationID());
//         }
//     }


//     @Test
//     void testAddGetHero() {
//         Hero hero = new Hero();
//         hero.setHeroName("Test Hero");
//         hero.setHeroDescription("Test Hero Description");
//         hero.setSuperPower("Test superpower");
//         hero = heroesDao.addHeroes(hero);

//         Hero fromDao = heroesDao.getHeroesByID(hero.getHeroID());

//         assertEquals(hero, fromDao);
//     }

//     @Test
//     void getAllHeroes() {
//         Hero hero = new Hero();
//         hero.setHeroName("Test Hero");
//         hero.setHeroDescription("Test Hero Description");
//         hero.setSuperPower("Test superpower");
//         hero = heroesDao.addHeroes(hero);

//         Hero hero2 = new Hero();
//         hero2.setHeroName("Test Hero 2");
//         hero2.setHeroDescription("Test Hero Description 2");
//         hero2.setSuperPower("Test superpower 2");
//         hero2 = heroesDao.addHeroes(hero2);

//         List<Hero> heroes = heroesDao.getAllHeroes();
//         assertEquals(2, heroes.size());
//         assertTrue(heroes.contains(hero));
//         assertTrue(heroes.contains(hero2));
//     }


//     @Test
//     void updateHeroes() {
//         Hero hero = new Hero();
//         hero.setHeroName("Test Hero");
//         hero.setHeroDescription("Test Hero Description");
//         hero.setSuperPower("Test superpower");
//         hero = heroesDao.addHeroes(hero);

//         Hero fromDao = heroesDao.getHeroesByID(hero.getHeroID());

//         assertEquals(hero, fromDao);

//         hero.setHeroName("Another Test Hero");
//         heroesDao.updateHeroes(hero);

//         assertNotEquals(hero, fromDao);

//         fromDao = heroesDao.getHeroesByID(hero.getHeroID());

//         assertEquals(hero, fromDao);
//     }

//     @Test
//     void deleteHeroesByID() {
//         List<Hero> heroList = new ArrayList<>();

//         Hero hero = new Hero();
//         hero.setHeroName("Test Hero");
//         hero.setHeroDescription("Test Hero Description");
//         hero.setSuperPower("Test superpower");
//         hero = heroesDao.addHeroes(hero);

//         Hero hero2 = new Hero();
//         hero2.setHeroName("Test Hero 2");
//         hero2.setHeroDescription("Test Hero Description 2");
//         hero2.setSuperPower("Test superpower 2");
//         hero2 = heroesDao.addHeroes(hero2);

//         heroList.add(hero);
//         heroList.add(hero2);

//         Address address = new Address();
//         address.setAddressLine1("Test Address Line 1");
//         address.setAddressLine2("Test Address Line 2");
//         address.setCity("Test city");
//         address.setStateAbbreviation("Test");
//         address.setZip("Test Zip");
//         address = addressesDao.addAddresses(address);

//         Organization organization = new Organization();
//         organization.setOrganizationName("Test Organization");
//         organization.setOrganizationDescription("Test Organization Description");
//         organization.setAddress(address);
//         organization.setMembers(heroList);
//         organization = organizationsDao.addOrganization(organization);

//         Location location = new Location();
//         location.setAddress(address);
//         location.setLocationName("Test Location name");
//         location.setLocationDescription("Test Location Description");
//         location.setLocationLatitude(Float.parseFloat("38.907192"));
//         location.setLocationLongitude(Float.parseFloat("-77.036873"));
//         location = locationsDao.addLocation(location);

//         Sighting sighting = new Sighting();
//         sighting.setLocation(location);
//         sighting.setDateOfSighting(LocalDate.of(2020, 2, 10));
//         sighting.setHero(hero);
//         sighting = sightingsDao.addSighting(sighting);

//         heroesDao.deleteHeroesByID(hero.getHeroID());

//         Hero fromDao = heroesDao.getHeroesByID(hero.getHeroID());
//         assertNull(fromDao);

//     }

//     @Test
//     void testAddAndGetOrganizationsForHero() {
//         List<Hero> heroList = new ArrayList<>();
//         List<Hero> heroes = new ArrayList<>();

//         Hero hero = new Hero();
//         hero.setHeroName("Test Hero");
//         hero.setHeroDescription("Test Hero Description");
//         hero.setSuperPower("Test superpower");
//         hero = heroesDao.addHeroes(hero);

//         Hero hero2 = new Hero();
//         hero2.setHeroName("Test Hero 2");
//         hero2.setHeroDescription("Test Hero Description 2");
//         hero2.setSuperPower("Test superpower 2");
//         hero2 = heroesDao.addHeroes(hero2);

//         heroList.add(hero);
//         heroes.add(hero2);


//         Address address = new Address();
//         address.setAddressLine1("Test Address Line 1");
//         address.setAddressLine2("Test Address Line 2");
//         address.setCity("Test city");
//         address.setStateAbbreviation("Test");
//         address.setZip("Test Zip");
//         address = addressesDao.addAddresses(address);

//         Organization organization = new Organization();
//         organization.setOrganizationName("Test Organization");
//         organization.setOrganizationDescription("Test Organization Description");
//         organization.setAddress(address);
//         organization.setMembers(heroList);
//         organization = organizationsDao.addOrganization(organization);

//         Organization organization2 = new Organization();
//         organization2.setOrganizationName("Test Organization 2");
//         organization2.setOrganizationDescription("Test Organization Description 2");
//         organization2.setAddress(address);
//         organization2.setMembers(heroList);
//         organization2 = organizationsDao.addOrganization(organization2);

//         Organization organization3 = new Organization();
//         organization3.setOrganizationName("Test Organization 3");
//         organization3.setOrganizationDescription("Test Organization Description 3");
//         organization3.setAddress(address);
//         organization3.setMembers(heroes);
//         organization3 = organizationsDao.addOrganization(organization3);

//         List<Organization> organizationsForHero = heroesDao.getOrganizationsForHero(hero);
//         assertEquals(2, organizationsForHero.size());
//         assertTrue(organizationsForHero.contains(organization));
//         assertTrue(organizationsForHero.contains(organization2));
//         assertFalse(organizationsForHero.contains(organization3));
//     }
// }