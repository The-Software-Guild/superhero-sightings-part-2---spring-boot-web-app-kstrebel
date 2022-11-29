package dao;

import models.Address;
import models.Organization;
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

    @Override
    public Organization getOrganizationByID(int ID) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM Organization WHERE OrganizationID = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), ID);
           return organization;
        } catch (DataAccessException ex) {
        return null;
    }
    }

    @Override
    public List<Organization> getAllOrganizations() {
            final String  SELECT_ALL_ORGANIZATIONS = "SELECT * FROM Organization";
            List<Organization> organizations = jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
            return organizations;
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO Organization(Name,Description, Address)"
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getOrganizationID(),
                organization.getOrganizationDescription(),
                organization.getAddressID());

        int newID = jdbc.queryForObject(("SELECT_LAST_INSERT_ID()"), Integer.class);
        organization.setOrganizationID(newID);
        return organization;
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE Organization SET Name = ?, Description = ?, Address = ? + "
                + "WHERE OrganizationID = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getOrganizationID(),
                organization.getOrganizationDescription(),
                organization.getAddressID());
    }

    @Override
    public void deleteOrganizationByID(int ID) {
        final String DELETE_ADDRESS = "DELETE FROM Address WHERE OrganizationID = ?";
        jdbc.update(DELETE_ADDRESS, ID);

        final String DELETE_ORGANIZATION = "DELETE FROM Organization WHERE OrganizationID = ?";
        jdbc.update(DELETE_ORGANIZATION, ID);

    }

    @Override
    public List<Organization> getOrganizationForAddress(Address address) {
        final String SELECT_ORGANIZATIONS_FOR_ADDRESS = "SELECT l* FROM Location l" + "WHERE s.AddressID = ?";
        List<Organization> organizations = jdbc.query(SELECT_ORGANIZATIONS_FOR_ADDRESS,
                new OrganizationMapper(), address.getAddressID());
        return organizations;
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

            @Override
            public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("OrganizationID"));
            organization.setOrganizationDescription(rs.getString("Organization Description"));
            organization.setAddressID(Integer.parseInt(rs.getString("AddressID")));
            return organization;
            }
        }
}



