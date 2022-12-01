package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.models.*;
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
    AddressesDao addressesDao;

    @BeforeEach
    void setUp() {
        List<Address> addresses = addressesDao.getAllAddresses();
        for (Address address : addresses) {
            addressesDao.deleteAddressByID(address.getAddressID());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddAndGetAddress() {
        Address address = new Address();
        address.setAddressLine1("Test addressLine1");
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