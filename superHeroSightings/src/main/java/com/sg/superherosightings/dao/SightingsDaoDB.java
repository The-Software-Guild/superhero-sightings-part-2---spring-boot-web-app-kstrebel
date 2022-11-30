package com.sg.superherosightings.dao;

import models.Location;
import models.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class SightingsDaoDB implements SightingsDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingByID(int ID) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM Sighting WHERE SightingID = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), ID);
           return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Location getLocationForSighting(int ID) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM Location l" +
                "JOIN Sighting s ON s.LocationID = l.LocationID WHERE s.SightingID = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationsDaoDB.LocationMapper(), ID);
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM Sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateLocationsForSightings(sightings);
        return sightings;
    }

    void associateLocationsForSightings(List<Sighting> sightings){
        for(Sighting sighting : sightings) {
            sighting.setLocationID(getLocationForSighting(sighting.getLocationID()).getLocationID());
        }
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO Sighting(HeroID, LocationID, DateOfSighting)"
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getHeroID(),
                sighting.getLocationID(),
                sighting.getDateOfSighting());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setLocationID(newID);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET HeroID = ?, LocationID =?, DateOfSighting = ?"
                + "WHERE SightingID = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getHeroID(),
                sighting.getLocationID(),
                sighting.getDateOfSighting());
    }

    @Override
    @Transactional
    public void deleteSightingByID(int ID) {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE SightingID = ?";
        jdbc.update(DELETE_SIGHTING, ID);
    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location) {
        final String SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM Sighting WHERE LocationID = ?";
        List<Sighting> sighting = jdbc.query(SELECT_SIGHTINGS_FOR_LOCATION,
                new SightingMapper(), location.getLocationID());
        associateLocationsForSightings(sighting);
        return sighting;
    }

public static final class SightingMapper implements RowMapper<Sighting> {

@Override
public Sighting mapRow(ResultSet rs, int index) throws SQLException {
    Sighting sighting = new Sighting();
    sighting.setLocationID(rs.getInt("LocationID"));
    sighting.setHeroID(rs.getInt("HeroID"));
    sighting.setDateOfSighting(Date.valueOf(rs.getDate("DateOfSighting").toString()).toLocalDate());

    return sighting;
        }
    }
}


