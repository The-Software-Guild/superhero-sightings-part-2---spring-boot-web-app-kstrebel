package com.sg.superherosightings.dao;

import models.Address;

import java.util.List;

public interface AddressesDao {

    Address getAddressesByID (int ID);
    List<Address> getAllAddresses();
    Address addAddresses(Address address);
    void updateAddresses(Address address);
    void deleteAddressByID (int ID);



}
