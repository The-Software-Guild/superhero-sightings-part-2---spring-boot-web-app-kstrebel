package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.HeroesDao;
import com.sg.superherosightings.dao.HeroesDaoDB;
import com.sg.superherosightings.dao.LocationsDao;
import com.sg.superherosightings.dao.LocationsDaoDB;
import com.sg.superherosightings.dao.SightingsDao;
import com.sg.superherosightings.dao.SightingsDaoDB;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/Sighting")
public class SightingController
{
    SightingsDao sightingsDao = new SightingsDaoDB();
    HeroesDao heroesDao = new HeroesDaoDB();
    LocationsDao locationsDao = new LocationsDaoDB();

    public SightingController(SightingsDao sightingsDao)
    {
        this.sightingsDao = sightingsDao;
    }

    @GetMapping("/")
    public String viewSightings(Model model)
    {
        model.addAttribute("sightingList", sightingsDao.getAllSightings());

        return "viewSightings";
    }

    @GetMapping("/New")
    public String addSighting(Model model)
    {
        Sighting sighting = new Sighting();
        sighting.setSightingID(0);

        List<Hero> heroList = heroesDao.getAllHeroes();
        List<Location> locationList = locationsDao.getAllLocations();

        model.addAttribute("pagename", "New");
        model.addAttribute("sighting", sighting);
        model.addAttribute("heroList", heroList);
        model.addAttribute("locationList", locationList);

        return "editSighting";
    }

    @GetMapping("/Edit/{sightingID}")
    public String editSighting(Model model, @PathVariable int sightingID)
    {
        List<Hero> heroList = heroesDao.getAllHeroes();
        List<Location> locationList = locationsDao.getAllLocations();

        model.addAttribute("pagename", "New");
        model.addAttribute("sighting", sightingsDao.getSightingByID(sightingID));
        model.addAttribute("heroList", heroList);
        model.addAttribute("locationList", locationList);

        return "editSighting";
    }

    @GetMapping("/Delete/{sightingID}")
    public String deleteSighting(Model model, @PathVariable int sightingID)
    {
        model.addAttribute("sighting", sightingsDao.getSightingByID(sightingID));

        return "deleteSighting";
    }

    @PostMapping("/update")
    public ResponseEntity updateSighting(@RequestBody Sighting sighting, Model model)
    {
        try
        {
            if (sighting.getSightingID() == 0)
            {
                sightingsDao.addSighting(sighting);
            }
            else
            {
                if (sightingsDao.updateSighting(sighting) != 1)
                {
                    return ResponseEntity.notFound().build();
                }
            }
            return ResponseEntity.ok().build();
        }
        catch (NullPointerException e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{sightingID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSighting(@PathVariable int sightingID)
    {
        sightingsDao.deleteSightingByID(sightingID);
    }
}
