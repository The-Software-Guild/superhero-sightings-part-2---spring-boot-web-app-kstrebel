package dao;

import models.Hero;

import java.util.List;

public interface HeroesDao {
    Hero getHeroesByID(int heroID);
    List<Hero> getAllHeroes();
    Hero addHeroes(Hero hero);
    void updateHeroes(Hero hero);
    void deleteHeroesByID(int ID);

}