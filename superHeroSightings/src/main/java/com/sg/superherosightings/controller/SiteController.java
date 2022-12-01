//Controller to serve web pages

package com.sg.superherosightings.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sg.superherosightings.dao.HeroesDao;
import com.sg.superherosightings.dao.HeroesDaoDB;
import com.sg.superherosightings.models.Hero;

import org.springframework.ui.Model;

@Controller
public class SiteController
{
    HeroesDao heroesDao = new HeroesDaoDB();

    @GetMapping("/heroes")
    public String viewHeroes(Model model)
    {
        List<Hero> heroesList = heroesDao.getAllHeroes();

        model.addAttribute("heroList", heroesList);
        return "viewHeroes";
    }

    @GetMapping("/addresses")
    public String viewAddresses(Model model)
    {
        return "viewAddresses";
    }

    @GetMapping("/locations")
    public String viewLocations(Model model)
    {
        return "viewLocations";
    }

    @GetMapping("/organizations")
    public String viewOrganizations(Model model)
    {
        return "viewOrganizations";
    }

    @GetMapping("/sightings")
    public String viewSightings(Model model)
    {
        return "viewSightings";
    }
}
