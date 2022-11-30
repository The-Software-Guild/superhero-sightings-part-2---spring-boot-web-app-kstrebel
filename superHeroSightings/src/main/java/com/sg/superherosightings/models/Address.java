package com.sg.superherosightings.models;

public class Address
{
    private int addressID;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String stateAbbreviation;
    private String zip;

    public int getAddressID()
    {
        return addressID;
    }

    public void setAddressID(int addressID)
    {
        this.addressID = addressID;
    }

    public String getAddressLine1()
    {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2()
    {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStateAbbreviation()
    {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation)
    {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + addressID;
        result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
        result = prime * result + ((addressLine2 == null) ? 0 : addressLine2.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((stateAbbreviation == null) ? 0 : stateAbbreviation.hashCode());
        result = prime * result + ((zip == null)? 0 : zip.hashCode());
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
        Address other = (Address) obj;
        if (addressID != other.addressID)
            return false;
        if (addressLine1 == null)
        {
            if (other.addressLine1 != null)
                return false;
        }
        else if (!addressLine1.equals(other.addressLine1))
            return false;
        if (addressLine2 == null)
        {
            if (other.addressLine2 != null)
                return false;
        }
        else if (!addressLine2.equals(other.addressLine2))
            return false;
        if (city == null)
        {
            if (other.city != null)
                return false;
        }
        else if (!city.equals(other.city))
            return false;
        if (stateAbbreviation == null)
        {
            if (other.stateAbbreviation != null)
                return false;
        }
        else if (!stateAbbreviation.equals(other.stateAbbreviation))
            return false;
        if (zip != other.zip)
            return false;
        return true;
    }
}
