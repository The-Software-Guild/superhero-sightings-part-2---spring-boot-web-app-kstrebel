package com.sg.superherosightings.service;

import java.time.LocalDate;
import java.util.List;

import com.sg.superherosightings.dao.*;
import com.sg.superherosightings.models.*;

public class SuperheroSightingsServiceImpl implements SuperheroSightingsService
{
    private AddressesDao addressesDao;
    private HeroesDao heroesDao;
    private LocationsDao locationsDao;
    private OrganizationsDao organizationsDao;
    private SightingsDao sightingsDao;

    public SuperheroSightingsServiceImpl() // throws SuperheroSightingsException
    {
        this.addressesDao = new AddressesDaoDB();
        this.heroesDao = new HeroesDaoDB();
        this.locationsDao = new LocationsDaoDB();
        this.organizationsDao = new OrganizationsDaoDB();
        this.sightingsDao = new SightingsDaoDB();
    }

    public SuperheroSightingsServiceImpl(AddressesDao addressesDao, HeroesDao heroesDao, LocationsDao locationsDao,
            OrganizationsDao organizationsDao, SightingsDao sightingsDao) // throws SuperheroSightingsException
    {
        this.addressesDao = addressesDao;
        this.heroesDao = heroesDao;
        this.locationsDao = locationsDao;
        this.organizationsDao = organizationsDao;
        this.sightingsDao = sightingsDao;
    }

    // #region address
    @Override
    public Address addAddress(Address address)
    {
        return addressesDao.addAddresses(address);
    }

    @Override
    public List<Address> getAllAddresses()
    {
        return addressesDao.getAllAddresses();
    }

    @Override
    public Address getAddress(int id)
    {
        return addressesDao.getAddressesByID(id);
    }

    @Override
    public void updateAddress(Address address)
    {
        addressesDao.updateAddresses(address);
    }

    @Override
    public void deleteAddress(int id)
    {
        addressesDao.deleteAddressByID(id);
    }
    // #endregion

    // #region hero
    @Override
    public Hero addHero(Hero hero)
    {
        return heroesDao.addHeroes(hero);
    }

    @Override
    public List<Hero> getAllHeroes()
    {
        return heroesDao.getAllHeroes();
    }

    @Override
    public Hero getHero(int id)
    {
        return heroesDao.getHeroesByID(id);
    }

    @Override
    public void updateHero(Hero hero)
    {
        heroesDao.updateHeroes(hero);
    }

    @Override
    public void deleteHero(int id)
    {
        heroesDao.deleteHeroesByID(id);
    }
    // #endregion

    // #region location
    @Override
    public Location addLocation(Location location)
    {
        return locationsDao.addLocation(location);
    }

    @Override
    public List<Location> getAllLocations()
    {
        return locationsDao.getAllLocations();
    }

    @Override
    public Location getLocation(int id)
    {
        return locationsDao.getLocationByID(id);
    }

    @Override
    public List<Location> getLocationsByAddress(Address address)
    {
        return locationsDao.getLocationsForAddress(address);
    }

    @Override
    public void updateLocation(Location location)
    {
        locationsDao.updateLocation(location);
    }

    @Override
    public void deleteLocation(int id)
    {
        locationsDao.deleteLocationByID(id);
    }
    // #endregion

    // #region organization
    @Override
    public Organization addOrganization(Organization organization)
    {
        return organizationsDao.addOrganization(organization);
    }

    @Override
    public List<Organization> getAllOrganizations()
    {
        return organizationsDao.getAllOrganizations();
    }

    @Override
    public Organization getOrganization(int id)
    {
        return organizationsDao.getOrganizationByID(id);
    }

    @Override // is this used publicly? can replace with Organization.Address?
    public Address getOrganizationAddress(Organization organization)
    {
        return getOrganizationAddress(organization);
    }

    @Override
    public List<Hero> getOrganizationMembers(Organization organization)
    {
        return organizationsDao.getMembersForOrganization(organization);
    }

    @Override
    public void updateOrganization(Organization organization)
    {
        organizationsDao.updateOrganization(organization);
    }

    @Override
    public void addOrganizationMember(Organization organization, int heroID)
    {
        organizationsDao.insertOrganizationMember(organization, heroID);
    }

    @Override
    public void addMemberOrganizations(int heroID, List<Organization> organizations)
    {
        organizationsDao.addMembersToOrganizations(heroID, organizations);
    }

    @Override
    public void deleteOrganization(int id)
    {
        organizationsDao.deleteOrganizationByID(id);
    }
    // #endregion
}
