package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

@Repository
public class AddressesDaoDB implements AddressesDao {
    //previous; does not compile
    //private final JdbcTemplate jdbc;
    //
    //@Autowired
    //public AddressesDaoDB(JdbcTemplate jdbc){
    //    this.jdbc = jdbc;
    //}
    
    @Autowired
    JdbcTemplate jdbc;


    public static final class AddressMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet rs, int index) throws SQLException {
            Address address = new Address();
            address.setAddressID(rs.getInt("addressID"));
            address.setAddressLine1(rs.getString("addressLine1"));
            address.setAddressLine2(rs.getString("addressLine2"));
            address.setCity(rs.getString("city"));
            address.setStateAbbreviation(rs.getString("stateAbbreviation"));
            address.setZip(rs.getString("zip"));
            return address;
        }
    }


    @Override
    public Address getAddressesByID(int ID) {
        try {
            final String SELECT_ADDRESS_BY_ID = "SELECT * FROM addresses WHERE addressID = ?";
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
        final String INSERT_ADDRESS = "INSERT INTO addresses(addressLine1, addressLine2, city, stateAbbreviation, zip )"
                + "VALUES(?,?,?,?,?,?";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    INSERT_ADDRESS,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, address.getAddressID());
            statement.setString(2, address.getAddressLine1());
            statement.setString(3, address.getAddressLine2());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getStateAbbreviation());
            statement.setString(6, address.getZip());
            return statement;
        }, keyHolder);

        address.setAddressID(keyHolder.getKey().intValue());
        return address;
    }

    @Override
    public void updateAddresses(Address address) {
        final String UPDATE_ADDRESS = "UPDATE addresses SET addressLine1 = ?, addressLine2 = ?, city = ?, stateAbbreviation = ?, zip = ?"
                + "WHERE addressID = ?";
        jdbc.update(UPDATE_ADDRESS,
                address.getAddressID(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getStateAbbreviation(),
                address.getZip());
    }

    @Override
    @Transactional
    public void deleteAddressByID(int ID) {

        //first see if the address is a foreign key for a location or an organization
        Location location;
        Organization organization;
        try{
            final String FIND_LOCATION_FOR_ADDRESS = "SELECT * FROM locations WHERE addressID = ?";
            location = jdbc.queryForObject(FIND_LOCATION_FOR_ADDRESS, new LocationsDaoDB.LocationMapper(), ID);
        }catch (DataAccessException ex){
            location = null;
        }
        try{
            final String FIND_ORGANIZATION_FOR_ADDRESS = "SELECT * FROM organizations WHERE addressID =?";
            organization = jdbc.queryForObject(FIND_ORGANIZATION_FOR_ADDRESS, new OrganizationsDaoDB.OrganizationMapper(), ID);
        } catch (DataAccessException ex){
            organization = null;
        }

        // remove the location/organization where the address is a foreign key 2 steps
        // Then remove sightings or members where location/organization is a foreign key
        //FInally remove the location/organization
       if (location != null) {
           final String REMOVE_LOCATION_FROM_SIGHTINGS = "SELECT * FROM sightings WHERE locationID =?";
           jdbc.update(REMOVE_LOCATION_FROM_SIGHTINGS,location.getLocationID());
           final String REMOVE_LOCATION = "SELECT * from locations WHERE addressID = ?";
           jdbc.update(REMOVE_LOCATION, ID);
       }
       if (organization != null){
           final String REMOVE_ORGANIZATION_FROM_MEMBERS = "SELECT * FROM members WHERE organizationID =?";
           jdbc.update(REMOVE_ORGANIZATION_FROM_MEMBERS, organization.getOrganizationID());
           final String REMOVE_ORGANIZATION = "SELECT * FROM organizations WHERE addressID =?";
           jdbc.update(REMOVE_ORGANIZATION, ID);
       }

        //lastly remove the address
        final String DELETE_ADDRESS = "DELETE FROM addresses WHERE addressID = ?";
        jdbc.update(DELETE_ADDRESS, ID);
    }



}

