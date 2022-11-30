package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;
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
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sightings WHERE sightingsID = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), ID);
           return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Location getLocationForSighting(Sighting sighting) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM Location l" +
                "JOIN Sighting s ON s.LocationID = l.LocationID WHERE s.SightingID = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationsDaoDB.LocationMapper(), sighting.getLocation());
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        addLocationsAndHeroForSightings(sightings);
        return sightings;
    }

    void addLocationsAndHeroForSightings(List<Sighting> sightings){
        for(Sighting sighting : sightings) {
            sighting.setHero(getHeroForSighting(sighting));
            sighting.setLocation(getLocationForSighting(sighting));
        }
    }

    private Hero getHeroForSighting(Sighting sighting) {
        final String SELECT_HERO_FOR_SIGHTING = "SELECT h.* FROM heroes h " +
                "JOIN sightings s ON h.heroID = s.heroID WHERE s.sightingsID = ?";
        return jdbc.queryForObject(SELECT_HERO_FOR_SIGHTING, new HeroesDaoDB.HeroMapper(), sighting.getSightingID());
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
        final String DELETE_SIGHTING = "DELETE FROM sightings WHERE sightingsID = ?";
        jdbc.update(DELETE_SIGHTING, ID);
    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location) {
        final String SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM sightings WHERE locationID = ?";
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_FOR_LOCATION,
                new SightingMapper(), location.getLocationID());
        addLocationsAndHeroForSightings(sightings);
        return sightings;
    }

public static final class SightingMapper implements RowMapper<Sighting> {

@Override
public Sighting mapRow(ResultSet rs, int index) throws SQLException {
    Sighting sighting = new Sighting();
    sighting.setDateOfSighting(Date.valueOf(rs.getDate("dateOfSighting").toString()).toLocalDate());
    return sighting;
        }
    }
}


