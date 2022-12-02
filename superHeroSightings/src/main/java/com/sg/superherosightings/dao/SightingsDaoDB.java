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
import java.time.LocalDate;
import java.util.List;


@Repository
public class SightingsDaoDB implements SightingsDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    LocationsDao lDao;

    @Autowired
    AddressesDao aDao;

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sightingsID"));
            sighting.setDateOfSighting(Date.valueOf(rs.getDate("dateOfSighting").toString()).toLocalDate());
            return sighting;
        }
    }

    @Override
    public Sighting getSightingByID(int ID) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sightings WHERE sightingsID = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), ID);
            sighting.setHero(getHeroForSighting(sighting));
            sighting.setLocation(getLocationForSighting(sighting));
           return sighting;
        } catch (DataAccessException ex) {
            System.out.println("Data Access Exception");
            return null;
        }
    }

    private Location getLocationForSighting(Sighting sighting) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM locations l " +
                "JOIN sightings s ON s.locationID = l.locationID WHERE s.sightingsID = ?";
        Location location = jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationsDaoDB.LocationMapper(), sighting.getSightingID());
        location.setAddress(lDao.getAddressForLocation(location));
        assert location != null;
        return location;
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sightings";
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
        final String INSERT_SIGHTING = "INSERT INTO sightings(heroID, locationID, dateOfSighting) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getHero().getHeroID(),
                sighting.getLocation().getLocationID(),
                sighting.getDateOfSighting().toString());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(newID);
        return sighting;
    }

    @Override
    public int updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sightings SET heroID = ?, locationID =?, dateOfSighting = ? "
                + "WHERE sightingsID = ?";
        return jdbc.update(UPDATE_SIGHTING,
                sighting.getHero().getHeroID(),
                sighting.getLocation().getLocationID(),
                sighting.getDateOfSighting(),
                sighting.getSightingID());
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

}


