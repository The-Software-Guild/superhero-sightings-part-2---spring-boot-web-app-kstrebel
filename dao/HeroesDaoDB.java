package dao;

import models.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class HeroesDaoDB  implements HeroesDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hero getHeroesByID(int heroID) {

        return null;
    }

    @Override
    public List<Hero> getAllHeroes() {
        return null;
    }

    @Override
    public Hero addHeroes(Hero hero) {
        return null;
    }

    @Override
    public void updateHeroes(Hero hero) {

    }

    @Override
    public void deleteHeroesByID(int ID) {

    }
}
