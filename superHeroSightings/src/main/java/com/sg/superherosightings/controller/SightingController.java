package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SightingsDao;
import com.sg.superherosightings.dao.SightingsDaoDB;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class SightingController
{
    SightingsDao sightingsDao = new SightingsDaoDB();

    public SightingController(SightingsDao sightingsDao)
    {
        this.sightingsDao = sightingsDao;
    }

    @GetMapping("/sightings")
    public String viewSightings(Model model)
    {
        return "viewSightings";
    }
}
