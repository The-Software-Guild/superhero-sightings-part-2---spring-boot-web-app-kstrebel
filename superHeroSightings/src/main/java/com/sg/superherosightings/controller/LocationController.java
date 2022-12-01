package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.LocationsDao;
import com.sg.superherosightings.dao.LocationsDaoDB;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class LocationController
{
    LocationsDao locationsDao = new LocationsDaoDB();

    public LocationController(LocationsDao locationsDao)
    {
        this.locationsDao = locationsDao;
    }

    @GetMapping("/locations")
    public String viewLocations(Model model)
    {
        return "viewLocations";
    }
}
