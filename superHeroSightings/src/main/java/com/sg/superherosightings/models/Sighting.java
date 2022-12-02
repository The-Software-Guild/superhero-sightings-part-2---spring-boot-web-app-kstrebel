package com.sg.superherosightings.models;

import java.time.LocalDate;

public class Sighting
{
    private int sightingID;

    private Hero hero;
    private Location location;
    private LocalDate dateOfSighting;

    public Hero getHero()
    {
        return hero;
    }

    public void setHero(Hero hero)
    {
        this.hero = hero;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public LocalDate getDateOfSighting()
    {
        return dateOfSighting;
    }

    public void setDateOfSighting(LocalDate dateOfSighting)
    {
        this.dateOfSighting = dateOfSighting;
    }

    public int getSightingID() {
        return sightingID;
    }

    public void setSightingID(int sightingID) {
        this.sightingID = sightingID;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hero == null) ? 0 : hero.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((dateOfSighting == null) ? 0 : dateOfSighting.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sighting other = (Sighting) obj;
        if (hero == null)
        {
            if (other.hero != null)
                return false;
        }
        else if (!hero.equals(other.hero))
            return false;
        if (location == null)
        {
            if (other.location != null)
                return false;
        }
        else if (!location.equals(other.location))
            return false;
        if (dateOfSighting == null)
        {
            if (other.dateOfSighting != null)
                return false;
        }
        else if (!dateOfSighting.equals(other.dateOfSighting))
            return false;
        return true;
    }
}
