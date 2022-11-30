package com.sg.superherosightings.dao;

import models.Address;
import models.Location;

import java.util.List;
public interface LocationsDao {

    Location getLocationByID (int ID);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationByID(int ID);

    List<Location> getLocationsForAddress(Address address);
}
