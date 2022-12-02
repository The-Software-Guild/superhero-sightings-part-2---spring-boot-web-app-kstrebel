package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.AddressesDao;
import com.sg.superherosightings.dao.AddressesDaoDB;
import com.sg.superherosightings.dao.LocationsDao;
import com.sg.superherosightings.dao.LocationsDaoDB;
import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.models.Location;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Location")
public class LocationController
{
    LocationsDao locationsDao = new LocationsDaoDB();
    AddressesDao addressesDao = new AddressesDaoDB();

    public LocationController(LocationsDao locationsDao)
    {
        this.locationsDao = locationsDao;
    }

    @GetMapping("/")
    public String viewLocations(Model model)
    {
        model.addAttribute("locationList", locationsDao.getAllLocations());

        return "locations";
        // return "viewLocations";
    }

    @GetMapping("/New")
    public String addLocation(Model model)
    {
        Location location = new Location();
        location.setLocationID(0);

        List<Address> addressList = addressesDao.getAllAddresses();

        model.addAttribute("pagename", "New");
        model.addAttribute("location", location);
        model.addAttribute("addressList", addressList);

        return "editLocation";
    }

    @GetMapping("/Edit/{locationID}")
    public String editLocation(Model model, @PathVariable int locationID)
    {
        List<Address> addressList = addressesDao.getAllAddresses();

        model.addAttribute("pagename", "New");
        model.addAttribute("location", locationsDao.getLocationByID(locationID));
        model.addAttribute("addressList", addressList);

        return "editLocation";
    }

    @GetMapping("/Delete/{locationID}")
    public String deleteLocation(Model model, @PathVariable int locationID)
    {
        model.addAttribute("location", locationsDao.getLocationByID(locationID));

        return "deleteLocation";
    }
}
