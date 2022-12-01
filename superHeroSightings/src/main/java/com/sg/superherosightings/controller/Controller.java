package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.*;
import com.sg.superherosightings.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final AddressesDao addressesDao;

    private final HeroesDao heroesDao;

    private final LocationsDao locationsDao;

    private final OrganizationsDao organizationsDao;

    private final SightingsDao sightingsDao;

    public Controller(AddressesDao addressesDao, HeroesDao heroesDao, LocationsDao locationsDao, OrganizationsDao organizationsDao, SightingsDao sightingsDao) {
        this.addressesDao = addressesDao;
        this.heroesDao = heroesDao;
        this.locationsDao = locationsDao;
        this.organizationsDao = organizationsDao;
        this.sightingsDao = sightingsDao;
    }
    
    @PostMapping("/addHero")
    @ResponseStatus(HttpStatus.CREATED)
    public Hero createHero(@RequestBody Hero body){
        return heroesDao.addHeroes(body);
    }

    @PostMapping("/addAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody Address body){
        return addressesDao.addAddresses(body);
    }

    @PostMapping("/addLocation")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Location createLocation(@RequestBody Location body, Address address){
        Address newAddress = addressesDao.addAddresses(address);
        body.setAddress(newAddress);
        return locationsDao.addLocation(body);

    }

    @PostMapping("/addOrganization")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Organization createOrganization(@RequestBody Organization body, Address address){
        Address newAddress = addressesDao.addAddresses(address);
        body.setAddress(newAddress);
        return organizationsDao.addOrganization(body);
    }

    @PostMapping("/addSighting")
    @ResponseStatus(HttpStatus.CREATED)
    public Sighting createSighting(@RequestBody Sighting body){
        return sightingsDao.addSighting(body);
    }

    @PutMapping("/heroes/{heroID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateHero(@RequestBody Hero body){
        heroesDao.updateHeroes(body);
    }

    @PutMapping("/locations/{locationID}")
    @Transactional
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateLocation(@PathVariable int locationID, @RequestBody Location location, Address address){
        if (address != null){

            location.setAddress(addressesDao.addAddresses(address));
        }
        locationsDao.updateLocation(location);
    }

    @PutMapping("/organizations/{organizationID}")
    @Transactional
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrganization(@PathVariable int organizationID, @RequestBody Organization organization, Address address){
        if (address != null){
            organization.setAddress(addressesDao.addAddresses(address));
        }
        organizationsDao.updateOrganization(organization);
    }

    @PutMapping("/sightings/{sightingID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSightings(@PathVariable int sightingID, @RequestBody Hero hero, Location location, LocalDate newDate){
        Sighting sighting = sightingsDao.getSightingByID(sightingID);
        if (hero != null){
            sighting.setHero(hero);
        }
        if (location != null){
            sighting.setLocation(location);
        }
        if (newDate != null){
            sighting.setDateOfSighting(newDate);
        }
        sightingsDao.updateSighting(sighting);
    }

    @DeleteMapping("/heroes/{heroID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteHero(@PathVariable int heroID){
        heroesDao.deleteHeroesByID(heroID);
    }

    @DeleteMapping("/addresses/{addressID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAddress(@PathVariable int addressID){
        addressesDao.deleteAddressByID(addressID);
    }

    @DeleteMapping("/locations/{locationID}")
    public void deleteLocation(@PathVariable int locationID){
        locationsDao.deleteLocationByID(locationID);
    }

    @DeleteMapping("/organizations/{organizationID}")
    public void deleteOrganization(@PathVariable int organizationID){
        organizationsDao.deleteOrganizationByID(organizationID);
    }

    @DeleteMapping("/sightings/{sightingsID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteSighting(@PathVariable int sightingID){
        sightingsDao.deleteSightingByID(sightingID);
    }

    @GetMapping("/allHeroes")
    public List<Hero> getAllHeroes() {
        return heroesDao.getAllHeroes();
    }

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationsDao.getAllLocations();
    }

    @GetMapping("/organizations")
    public List<Organization> getAllOrganizations() {
        return organizationsDao.getAllOrganizations();
    }

    @GetMapping("/sightings")
    public List<Sighting> getAllSightings() {
        return sightingsDao.getAllSightings();
    }


}
