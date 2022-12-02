package com.sg.superherosightings.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization
{
    private int organizationID;

    private String organizationName;
    private String organizationDescription;

    private Address address;
  
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


    public Address getAddress()

    {
        return address;
    }


    public void setAddress(Address address)

    {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (getOrganizationID() != that.getOrganizationID()) return false;
        if (!getOrganizationName().equals(that.getOrganizationName())) return false;
        if (!getOrganizationDescription().equals(that.getOrganizationDescription())) return false;
        int addressID = getAddress().getAddressID();
        int addressID2 = that.getAddress().getAddressID();
        if (addressID != addressID2) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = getOrganizationID();
        result = 31 * result + getOrganizationName().hashCode();
        result = 31 * result + getOrganizationDescription().hashCode();
        result = 31 * result + getAddress().hashCode();
        return result;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Organization that = (Organization) o;
//        return getOrganizationName().equals(that.getOrganizationName()) && getOrganizationDescription().equals(that.getOrganizationDescription())
////                && getAddress().equals(that.getAddress())
////                && Objects.equals(getMembers(), that.getMembers())
//                ;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getOrganizationName(), getOrganizationDescription(), getAddress(), getMembers());
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Organization that = (Organization) o;
//        return getOrganizationID() == that.getOrganizationID() && getOrganizationName().equals(that.getOrganizationName()) && getOrganizationDescription().equals(that.getOrganizationDescription()) && getAddress().equals(that.getAddress()) && getMembers().equals(that.getMembers());
//
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getOrganizationID(), getOrganizationName(), getOrganizationDescription(), getAddress(), getMembers());
//
//    }
}
