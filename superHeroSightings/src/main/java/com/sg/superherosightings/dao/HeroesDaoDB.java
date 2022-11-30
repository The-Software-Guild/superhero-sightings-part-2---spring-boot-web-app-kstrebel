package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
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
public class HeroesDaoDB  implements HeroesDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hero getHeroesByID(int heroID) {
        try {
            final String SELECT_HERO_BY_ID = "SELECT * FROM Hero WHERE HeroID = ?";
            return jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroMapper());
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        final String SELECT_ALL_HEROES = "SELECT * FROM heroes";
        return jdbc.query(SELECT_ALL_HEROES, new HeroMapper());
    }

    @Override
    @Transactional
    public Hero addHeroes(Hero hero) {
        final String INSERT_HERO = "INSERT INTO heroes(heroID, heroName, heroDescription, superPower)" +
                "VALUES(?,?,?,?";
        jdbc.update(INSERT_HERO,
                hero.getHeroID(),
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getSuperPower());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setHeroID(newID);
        return hero;
    }

    @Override
    public void updateHeroes(Hero hero) {
        final String UPDATE_HERO = "INSERT INTO heroes(heroID, heroName, heroDescription, superPower)" +
                "VALUES(?,?,?,?";
        jdbc.update(UPDATE_HERO,
                hero.getHeroID(),
                hero.getHeroName(),
                hero.getHeroDescription(),
                hero.getSuperPower());
    }

    @Override
    @Transactional
    public void deleteHeroesByID(int ID) {
        final String DELETE_HERO_FROM_SIGHTINGS = "DELETE FROM sightings WHERE heroID = ?";
        jdbc.update(DELETE_HERO_FROM_SIGHTINGS, ID);

        final String DELETE_HERO_FROM_MEMBERS = "DELETE FROM members WHERE heroID =?";
        jdbc.update(DELETE_HERO_FROM_MEMBERS, ID);

        final String DELETE_HERO = "DELETE FROM heroes WHERE heroID = ?";
        jdbc.update(DELETE_HERO, ID);
    }

    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroID(rs.getInt("heroID"));
            hero.setHeroName(rs.getString("heroName"));
            hero.setHeroDescription(rs.getString("heroDescription"));
            hero.setSuperPower(rs.getString("superPower"));
            return hero;
        }
    }
}
