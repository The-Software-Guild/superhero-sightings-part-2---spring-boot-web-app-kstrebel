package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Organization;

import java.util.List;

public interface HeroesDao {
    Hero getHeroesByID(int heroID);
    List<Hero> getAllHeroes();
    Hero addHeroes(Hero hero);
    void updateHeroes(Hero hero);
    void deleteHeroesByID(int ID);

    List<Organization> getOrganizationsForHero(Hero hero);

    void addOrganizationsToHero(List<Hero> heroList);


}