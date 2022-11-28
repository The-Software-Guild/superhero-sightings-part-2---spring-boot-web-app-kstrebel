package models;

public class Organization
{
    private int organizationID;
    private String organizationDescription;
    private int addressID;

    public int getOrganizationID()
    {
        return organizationID;
    }

    public void setOrganizationID(int organizationID)
    {
        this.organizationID = organizationID;
    }

    public String getOrganizationDescription()
    {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription)
    {
        this.organizationDescription = organizationDescription;
    }

    public int getAddressID()
    {
        return addressID;
    }

    public void setAddressID(int addressID)
    {
        this.addressID = addressID;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + organizationID;
        result = prime * result + ((organizationDescription == null) ? 0 : organizationDescription.hashCode());
        result = prime * result + addressID;
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
        Organization other = (Organization) obj;
        if (organizationID != other.organizationID)
            return false;
        if (organizationDescription == null)
        {
            if (other.organizationDescription != null)
                return false;
        }
        else if (!organizationDescription.equals(other.organizationDescription))
            return false;
        if (addressID != other.addressID)
            return false;
        return true;
    }
}
