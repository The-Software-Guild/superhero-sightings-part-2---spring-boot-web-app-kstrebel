package dao;

import models.Location;
import models.Sighting;

import java.util.List;

public interface SightingsDao {

    Sighting getSightingByID(int ID);
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sighting);
    void updateSighting(Sighting sighting);
    void deleteSightingByID(int ID);

    List<Sighting> getSightingsForLocation(Location location);
}
