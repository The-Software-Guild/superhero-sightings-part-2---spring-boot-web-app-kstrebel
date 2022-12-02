package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.AddressesDao;
import com.sg.superherosightings.dao.AddressesDaoDB;
import com.sg.superherosightings.dao.HeroesDao;
import com.sg.superherosightings.dao.HeroesDaoDB;
import com.sg.superherosightings.dao.OrganizationsDao;
import com.sg.superherosightings.dao.OrganizationsDaoDB;
import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.models.Hero;
import com.sg.superherosightings.models.Organization;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Organization")
public class OrganizationController
{
    OrganizationsDao organizationsDao = new OrganizationsDaoDB();
    AddressesDao addressesDao = new AddressesDaoDB();
    HeroesDao heroesDao = new HeroesDaoDB();

    public OrganizationController(OrganizationsDao organizationsDao)
    {
        this.organizationsDao = organizationsDao;
    }

    @GetMapping("/")
    public String viewOrganizations(Model model)
    {
        model.addAttribute("organizationList", organizationsDao.getAllOrganizations());

        return "organizations";
        // return "viewOrganizations";
    }

    @GetMapping("/New")
    public String addOrganization(Model model)
    {
        Organization organization = new Organization();
        organization.setOrganizationID(0);

        List<Hero> heroList = heroesDao.getAllHeroes();
        List<Address> addressList = addressesDao.getAllAddresses();

        model.addAttribute("pagename", "New");
        model.addAttribute("organization", organization);
        model.addAttribute("heroList", heroList);
        model.addAttribute("addressList", addressList);

        return "editOrganization";
    }

    @GetMapping("/Edit/{organizationID}")
    public String editOrganization(Model model, @PathVariable int organizationID)
    {
        List<Hero> heroList = heroesDao.getAllHeroes();
        List<Address> addressList = addressesDao.getAllAddresses();

        model.addAttribute("pagename", "Edit");
        model.addAttribute("organization", organizationsDao.getOrganizationByID(organizationID));
        model.addAttribute("heroList", heroList);
        model.addAttribute("addressList", addressList);

        return "editOrganization";
    }

    @GetMapping("/Delete/{organizationID}")
    public String deleteOrganization(Model model, @PathVariable int organizationID)
    {
        model.addAttribute("organization", organizationsDao.getOrganizationByID(organizationID));

        return "deleteOrganization";
    }
}
