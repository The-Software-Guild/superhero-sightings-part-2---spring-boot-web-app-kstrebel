package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;


@Repository
public class LocationsDaoDB implements LocationsDao
{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationByID(int ID) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM locations WHERE locationID = ?";
            Location location = jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), ID);
            location.setAddress(getAddressForLocation(location));
            return location;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM locations";
        List<Location> locations = jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
        addAddressForLocations(locations);
        return locations;
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO locations(locationId, locationName, locationDescription, " +
                "locationAddress, locationLatitude, locationLongitude)" + " VALUES(?,?,?,?,?,?";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    INSERT_LOCATION,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, location.getLocationID());
            statement.setString(2, location.getLocationName());
            statement.setString(3, location.getLocationDescription());
            statement.setInt(4, location.getAddress().getAddressID());
            statement.setFloat(5, location.getLocationLatitude());
            statement.setFloat(6, location.getLocationLongitude());
            return statement;
        }, keyHolder);

        location.setLocationID(keyHolder.getKey().intValue());
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE locations SET locationID =? locationName = ?, locationDescription = ?, " +
                "addressID = ?, locationLatitude = ?, locationLongitude = ?"
                + "WHERE locationID = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getLocationID(),
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress().getAddressID(),
                location.getLocationLatitude(),
                location.getLocationLongitude());
    }

    @Override
    public void deleteLocationByID(int ID) {
        final String DELETE_LOCATION = "DELETE FROM locations WHERE lpcationID = ?";
        jdbc.update(DELETE_LOCATION, ID);

    }

    @Override
    public Address getAddressForLocation(Location location) {
        final String SELECT_ADDRESS_FOR_MEETING = "SELECT a.* FROM addresses a" +
                " JOIN locations l ON a.addressID = l.addressID WHERE l.locationID = ?";
        return jdbc.queryForObject(SELECT_ADDRESS_FOR_MEETING, new AddressesDaoDB.AddressMapper(), location.getLocationID());
    }

    @Override
    public void addAddressForLocations(List<Location> locations) {
        for(Location location: locations){
            location.setAddress(getAddressForLocation(location));
        }

    }


    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("locationID"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescription(rs.getString("locationDescription"));
            location.setLocationLatitude(rs.getFloat("locationLatitude"));
            location.setLocationLongitude(rs.getFloat("locationLongitude"));
        return location;
            }
        }
    }


