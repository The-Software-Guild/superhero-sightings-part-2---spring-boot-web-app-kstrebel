package models;

import java.time.LocalDate;

public class Sighting
{
    private int heroID;
    private int locationID;
    private LocalDate dateOfSighting;

    public int getHeroID()
    {
        return heroID;
    }

    public void setHeroID(int heroID)
    {
        this.heroID = heroID;
    }

    public int getLocationID()
    {
        return locationID;
    }

    public void setLocationID(int locationID)
    {
        this.locationID = locationID;
    }

    public LocalDate getDateOfSighting()
    {
        return dateOfSighting;
    }

    public void setDateOfSighting(LocalDate dateOfSighting)
    {
        this.dateOfSighting = dateOfSighting;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + heroID;
        result = prime * result + locationID;
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
        if (heroID != other.heroID)
            return false;
        if (locationID != other.locationID)
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
