package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Location;
import com.sg.superherosightings.models.Sighting;

import java.util.List;

public interface SightingsDao {

    Sighting getSightingByID(int ID);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    int updateSighting(Sighting sighting);
    void deleteSightingByID(int ID);

    List<Sighting> getSightingsForLocation(Location location);
}
