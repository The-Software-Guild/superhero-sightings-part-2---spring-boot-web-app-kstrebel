package models;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization
{
    private int organizationID;

    private String organizationName;
    private String organizationDescription;
    private int addressID;

    private List<Hero> members = new ArrayList<>();

    public List<Hero> getMembers() {
        return members;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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
        return getOrganizationID() == that.getOrganizationID() && getOrganizationName().equals(that.getOrganizationName()) && getOrganizationDescription().equals(that.getOrganizationDescription()) && getAddress().equals(that.getAddress()) && getMembers().equals(that.getMembers());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrganizationID(), getOrganizationName(), getOrganizationDescription(), getAddress(), getMembers());

    }
}
