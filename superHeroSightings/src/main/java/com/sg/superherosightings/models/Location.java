package com.sg.superherosightings.models;

import java.lang.constant.Constable;

public class Location
{
    private int locationID;
    private String locationName;
    private String locationDescription;
    private Address address;
    private float locationLatitude;
    private float locationLongitude;

    public int getLocationID()
    {
        return locationID;
    }

    public void setLocationID(int locationID)
    {
        this.locationID = locationID;
    }

    public String getLocationName()
    {
        return locationName;
    }

    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }

    public String getLocationDescription()
    {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription)
    {
        this.locationDescription = locationDescription;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public float getLocationLatitude()
    {
        return locationLatitude;
    }

    public void setLocationLatitude(float locationLatitude)
    {
        this.locationLatitude = locationLatitude;
    }

    public float getLocationLongitude()
    {
        return locationLongitude;
    }

    public void setLocationLongitude(float locationLongitude)
    {
        this.locationLongitude = locationLongitude;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + locationID;
        result = prime * result + ((locationName == null) ? 0 : locationName.hashCode());
        result = prime * result + ((locationDescription == null) ? 0 : locationDescription.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + Float.floatToIntBits(locationLatitude);
        result = prime * result + Float.floatToIntBits(locationLongitude);
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
        Location other = (Location) obj;
        if (locationID != other.locationID)
            return false;
        if (locationName == null)
        {
            if (other.locationName != null)
                return false;
        }
        else if (!locationName.equals(other.locationName))
            return false;
        if (locationDescription == null)
        {
            if (other.locationDescription != null)
                return false;
        }
        else if (!locationDescription.equals(other.locationDescription))
            return false;
        if (address == null)
        {
            if (other.address != null)
                return false;
        }
        else if (!address.equals(other.address))
            return false;
        if (Float.floatToIntBits(locationLatitude) != Float.floatToIntBits(other.locationLatitude))
            return false;
        if (Float.floatToIntBits(locationLongitude) != Float.floatToIntBits(other.locationLongitude))
            return false;
        return true;
    }
}
