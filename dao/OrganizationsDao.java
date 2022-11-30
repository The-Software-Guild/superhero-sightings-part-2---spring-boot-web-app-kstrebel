package dao;

import models.Address;
import models.Hero;
import models.Organization;

import java.util.List;


public interface OrganizationsDao {

    Organization getOrganizationByID(int ID);
    List<Organization> getAllOrganizations();
    Organization addOrganization(Organization organization);
    void updateOrganization(Organization organization);
    void deleteOrganizationByID(int ID);

    Address getAddressForOrganization(Organization organization);

    List<Hero> getMembersForOrganization (Organization organization);

    void addMembersAndAddressToOrganizations(List<Organization> organizationList);

    void insertOrganizationMember(Organization organization);



    List<Hero> getMembersForOrganization (Organization organization);

    void addMembersToOrganizations(List<Organization> organizationList);

    void insertOrganizationMember(Organization organization);



}
