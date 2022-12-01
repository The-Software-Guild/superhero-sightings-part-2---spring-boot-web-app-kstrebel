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
public class HeroController
{
    HeroesDao heroesDao = new HeroesDaoDB();

    public HeroController(HeroesDao heroesDao)
    {
        this.heroesDao = heroesDao;
    }

    @GetMapping("/heroes")
    public String viewHeroes(Model model)
    {
        List<Hero> heroesList = heroesDao.getAllHeroes();

        model.addAttribute("heroList", heroesList);
        return "viewHeroes";
    }
}
