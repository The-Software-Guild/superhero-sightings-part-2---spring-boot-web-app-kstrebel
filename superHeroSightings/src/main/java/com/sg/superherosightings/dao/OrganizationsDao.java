package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Organization;

import java.util.List;


public interface OrganizationsDao {

    Organization getOrganizationByID(int ID);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationByID(int ID);

    Address getAddressForOrganization(Organization organization);

    void addMembersAndAddressToOrganizations(List<Organization> organizationList);


    List<Hero> getMembersForOrganization (Organization organization);

    void addMembersToOrganizations(List<Organization> organizationList);

    void insertOrganizationMember(Organization organization);



}
