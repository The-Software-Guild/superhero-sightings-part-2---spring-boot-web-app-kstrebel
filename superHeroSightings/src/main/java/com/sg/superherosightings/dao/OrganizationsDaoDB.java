package com.sg.superherosightings.dao;

import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrganizationsDaoDB implements OrganizationsDao {

    @Autowired
    JdbcTemplate jdbc;

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("organizationID"));
            organization.setOrganizationName(rs.getString("organizationName"));
            organization.setOrganizationDescription(rs.getString("organizationDescription"));
            return organization;

        }
    }

    @Override
    public Organization getOrganizationByID(int ID) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM organizations WHERE organizationID = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), ID);
           return organization;
        } catch (DataAccessException ex) {
        return null;
    }
    }

    @Override
    public List<Organization> getAllOrganizations() {
            final String  SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organizations";
            List<Organization> organizations = jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
            addMembersAndAddressToOrganizations(organizations);
            return organizations;
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO organizations(organizationName,organizationDescription, addressID)"
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getOrganizationID(),
                organization.getOrganizationDescription(),
                organization.getAddress().getAddressID());


        int newID = jdbc.queryForObject(("SELECT_LAST_INSERT_ID()"), Integer.class);
        organization.setOrganizationID(newID);
        insertOrganizationMember(organization);
        return organization;
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organizations SET organizationName = ?, organizationDescription = ?, addressID = ?"
                + " WHERE OrganizationID = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getOrganizationID(),
                organization.getOrganizationDescription(),

                organization.getAddress().getAddressID());

    }

    @Override
    @Transactional
    public void deleteOrganizationByID(int ID) {

        final String DELETE_MEMBERS = "DELETE FROM members WHERE organizationID =?";
        jdbc.update(DELETE_MEMBERS, ID);

        final String DELETE_ORGANIZATION = "DELETE FROM organizations WHERE organizationID = ?";
        jdbc.update(DELETE_ORGANIZATION, ID);

    }

    @Override
    public Address getAddressForOrganization(Organization organization) {
        final String SELECT_ORGANIZATIONS_FOR_ADDRESS = "SELECT a.* FROM addresses a" + "" +
                "JOIN organizations o ON a.addressID = o.addressID WHERE organizationID = ?";
        return jdbc.queryForObject(SELECT_ORGANIZATIONS_FOR_ADDRESS, new AddressesDaoDB.AddressMapper(), organization.getOrganizationID());
    }



    public void addMembersAndAddressToOrganizations(List<Organization> organizationList) {
        for(Organization organization: organizationList){
            organization.setAddress(getAddressForOrganization(organization));
            organization.setMembers(getMembersForOrganization(organization));
        }

    }

    @Override
    public void insertOrganizationMember(Organization organization) {
        final String INSERT_ORGANIZATION_MEMBER = "INSERT INTO members(heroID, organizationID) VALUES (?,?)";
        for(Hero heroes : organization.getMembers()){
            jdbc.update(INSERT_ORGANIZATION_MEMBER, heroes.getHeroID(), organization.getOrganizationID());
        }

    }

    @Override
    public List<Hero> getMembersForOrganization(Organization organization) {
        final String SELECT_HEROES_FOR_ORGANIZATION = "SELECT * FROM heroes " +
                "JOIN members ON heroes.heroID = members.heroId WHERE members.organizationID =?";
        return jdbc.query(SELECT_HEROES_FOR_ORGANIZATION, new HeroesDaoDB.HeroMapper(), organization.getOrganizationID());
    }
    
    @Override
   public List<Organization> getOrganizationsForHero(Hero hero){
        return null;
    }

    @Override
    public void addMembersToOrganizations(List<Organization> organizationList) {
        for(Organization organization: organizationList){
            organization.setMembers(getMembersForOrganization(organization));
        }

    }


    }






