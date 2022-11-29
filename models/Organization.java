package models;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization
{
    private int organizationID;
    private String organizationDescription;
    private int addressID;

    private List<Hero> members = new ArrayList<>();

    public List<Hero> getMembers() {
        return members;
    }

    public void setMembers(List<Hero> members) {
        this.members = members;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return getOrganizationID() == that.getOrganizationID() && getAddressID() == that.getAddressID() && Objects.equals(getOrganizationDescription(), that.getOrganizationDescription()) && Objects.equals(getMembers(), that.getMembers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrganizationID(), getOrganizationDescription(), getAddressID(), getMembers());
    }
}
