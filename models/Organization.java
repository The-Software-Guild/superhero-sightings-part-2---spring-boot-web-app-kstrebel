package models;

public class Organization
{
    private int organizationID;
    private String organizationDescription;
    private String address;

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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + organizationID;
        result = prime * result + ((organizationDescription == null) ? 0 : organizationDescription.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
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
        if (address == null)
        {
            if (other.address != null)
                return false;
        }
        else if (!address.equals(other.address))
            return false;
        return true;
    }
}
