package com.sg.superherosightings.service;

import java.util.List;

import com.sg.superherosightings.models.*;

public interface SuperheroSightingsService
{
    public Address addAddress(Address address);

    public List<Address> getAllAddresses();

    public Address getAddress(int id);

    public void updateAddress(Address address);

    public void deleteAddress(int id);

    public Hero addHero(Hero hero);

    public List<Hero> getAllHeroes();

    public Hero getHero(int id);

    public void updateHero(Hero hero);

    public void deleteHero(int id);

    public Location addLocation(Location location);

    public List<Location> getAllLocations();

    public Location getLocation(int id);

    public List<Location> getLocationsByAddress(Address address);

    public void updateLocation(Location location);

    public void deleteLocation(int id);

    public Organization addOrganization(Organization organization);

    public List<Organization> getAllOrganizations();

    public Organization getOrganization(int id);

    public Address getOrganizationAddress(Organization organization);

    public List<Hero> getOrganizationMembers(Organization organization);

    public void updateOrganization(Organization organization);

    public void addOrganizationMember(Organization organization, int heroID);

    public void addMemberOrganizations(int heroID, List<Organization> organizations);

    public void deleteOrganization(int id);

    public Sighting addSighting(Sighting sighting);

    public List<Sighting> getAllSightings();

    public List<Sighting> getSightingsForLocation(Location location);

    public void updateSighting(Sighting sighting);

    public void deleteSighting(int id);
}
