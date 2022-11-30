package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.models.Location;
import models.Address;
import models.Location;

import java.util.List;
public interface LocationsDao {

    Location getLocationByID (int ID);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationByID(int ID);

    Address getAddressForLocation(Location location);

    void addAddressForLocations(List<Location> locations);

}
