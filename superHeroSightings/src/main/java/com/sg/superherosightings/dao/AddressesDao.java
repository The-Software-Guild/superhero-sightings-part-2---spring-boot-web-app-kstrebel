package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Address;

import java.util.List;

public interface AddressesDao {

    Address getAddressesByID (int ID);
    List<Address> getAllAddresses();
    Address addAddresses(Address address);
    int updateAddresses(Address address);
    void deleteAddressByID (int ID);



}
