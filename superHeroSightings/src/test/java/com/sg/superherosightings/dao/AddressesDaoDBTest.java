package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Organization;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AddressesDaoDBTest {

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

    @BeforeEach
    void setUp() {
        List<Address> addresses = addressesDao.getAllAddresses();
        for (Address address : addresses) {
            addressesDao.deleteAddressByID(address.getAddressID());
        }

        List<Organization> organizationList = organizationsDao.getAllOrganizations();
        for(Organization organization : organizationList){
            organizationsDao.deleteOrganizationByID(organization.getOrganizationID());
        }

        List<Location> locationList = locationsDao.getAllLocations();
        for(Location location : locationList){
            locationsDao.deleteLocationByID(location.getLocationID());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddAndGetAddress() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setAddressLine2("Test addressLine2");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Address fromDao = addressesDao.getAddressesByID(address.getAddressID());

        assertEquals(address, fromDao);
    }

    @Test
    public void testGetAllAddresses() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Address address2 = new Address();
        address2.setAddressLine1("Test addressLine1 2");
        address2.setCity("Test city 2");
        address2.setStateAbbreviation("ST");
        address2.setZip("22222");
        address2 = addressesDao.addAddresses(address2);

        List<Address> addresses = addressesDao.getAllAddresses();

        assertEquals(2,addresses.size());
        assertTrue(addresses.contains(address));
        assertTrue(addresses.contains(address2));
    }

    @Test
    void testUpdateAddresses() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Address fromDao = addressesDao.getAddressesByID(address.getAddressID());
        assertEquals(address, fromDao);

        address.setAddressLine1("Test update");
        addressesDao.updateAddresses(address);

        assertNotEquals(address, fromDao);

        fromDao = addressesDao.getAddressesByID(address.getAddressID());
        assertEquals(address, fromDao);
    }

    @Test
    void deleteAddressByID() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
        address.setCity("Test city");
        address.setStateAbbreviation("TE");
        address.setZip("11111");
        address = addressesDao.addAddresses(address);

        Address fromDao = addressesDao.getAddressesByID(address.getAddressID());
        assertEquals(address, fromDao);

        addressesDao.deleteAddressByID(address.getAddressID());

        fromDao = addressesDao.getAddressesByID(address.getAddressID());
        assertNull(fromDao);
    }
}