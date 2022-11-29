package dao;

import models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressesDaoDB implements AddressesDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Address getAddressesByID(int ID) {
        try {
            final String SELECT_ADDRESS_BY_ID = "SELECT * FROM Address WHERE AddressID = ?";
            return jdbc.queryForObject(SELECT_ADDRESS_BY_ID, new AddressMapper(), ID);
        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<Address> getAllAddresses() {
        final String SELECT_ALL_ADDRESSES = "SELECT * FROM Location";
        return jdbc.query(SELECT_ALL_ADDRESSES, new AddressesDaoDB.AddressMapper());
        return null;
    }

    @Override
    public Address addAddresses(Address address) {
        return null;
    }

    @Override
    public void updateAddresses(Address address) {

    }

    @Override
    public void deleteAddressByID(int ID) {

    }

}
