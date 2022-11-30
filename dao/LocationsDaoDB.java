package dao;

import models.Address;
import models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class LocationsDaoDB implements LocationsDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationByID(int ID) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM Location WHERE LocationID = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), ID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM Location";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO Location(Name, Description, Address, Latitude, Longitude)" + "VALUES(?,?,?,?,?";
        jdbc.update(INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLocationLatitude(),
                location.getLocationLongitude());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationID(newID);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE Location SET Name = ?, Description = ?, Address = ?, Latitude = ?, Longitude = ?"
                + "WHERE LocationID = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getLocationID(),
                location.getLocationName(),
                location.getLocationDescription(),
                location.getAddress(),
                location.getLocationLatitude(),
                location.getLocationLongitude());
    }

    @Override
    public void deleteLocationByID(int ID) {
        final String DELETE_ADDRESS = "DELETE FROM Address WHERE LocationID = ?";
        jdbc.update(DELETE_ADDRESS, ID);

        final String DELETE_LOCATION = "DELETE FROM Location WHERE LocationID = ?";
        jdbc.update(DELETE_LOCATION, ID);

    }

    @Override
    public List<Location> getLocationsForAddress(Address address) {
        final String SELECT_LOCATIONS_FOR_ADDRESS = "SELECT l* FROM Location l" + "WHERE s.AddressID = ?";
        List<Location> locations = jdbc.query(SELECT_LOCATIONS_FOR_ADDRESS,
                new LocationMapper(), address.getAddressID());
        return locations;
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("LocationID"));
            location.setLocationName(rs.getString("LocationName"));
            location.setLocationDescription(rs.getString("LocationDescription"));
            location.setAddress(rs.getString("Address"));
            location.setLocationLatitude(rs.getFloat("LocationLatitude"));
            location.setLocationLongitude(rs.getFloat("LocationLongitude"));
        return location;
            }
        }
    }


