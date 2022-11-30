package dao;

import models.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        final String SELECT_ALL_ADDRESSES = "SELECT * FROM Address";
        return jdbc.query(SELECT_ALL_ADDRESSES, new AddressMapper());
    }

    @Override
    @Transactional
    public Address addAddresses(Address address) {
        final String INSERT_ADDRESS = "INSERT INTO Address(Name, Address Line 1, Address Line 2, City, State Abbreviation, Zip )"
                + "VALUES(?,?,?,?,?,?";
        jdbc.update(INSERT_ADDRESS,
                address.getAddressID(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getStateAbbreviation(),
                address.getZip());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        address.setAddressID(newID);
        return address;
    }

    @Override
    public void updateAddresses(Address address) {
        final String UPDATE_ADDRESS = "UPDATE Address SET Name = ?, Address Line 1 = ?, Address Line 2 = ?, City = ?, State Abbreviation = ?, Zip = ?"
                + "WHERE AddressID = ?";
        jdbc.update(UPDATE_ADDRESS,
                address.getAddressID(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getStateAbbreviation(),
                address.getZip());
    }

    @Override
    public void deleteAddressByID(int ID) {
        final String DELETE_ADDRESS = "DELETE FROM Address WHERE AddressID = ?";
        jdbc.update(DELETE_ADDRESS, ID);
    }


    public static final class AddressMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet rs, int index) throws SQLException {
            Address address = new Address();
            address.setAddressID(rs.getInt("Address ID"));
            address.setAddressLine1(rs.getString("Address Line1"));
            address.setAddressLine2(rs.getString("Address Line2"));
            address.setCity(rs.getString("City"));
            address.setStateAbbreviation(rs.getString("State Abbreviation"));
            address.setZip(rs.getInt("Zip"));
            return address;
        }
    }
}

