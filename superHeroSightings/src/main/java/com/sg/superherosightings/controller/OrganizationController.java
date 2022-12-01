package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.OrganizationsDao;
import com.sg.superherosightings.dao.OrganizationsDaoDB;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class OrganizationController
{
    OrganizationsDao organizationsDao = new OrganizationsDaoDB();

    public OrganizationController(OrganizationsDao organizationsDao)
    {
        this.organizationsDao = organizationsDao;
    }

    @GetMapping("/organizations")
    public String viewOrganizations(Model model)
    {
        return "viewOrganizations";
    }
}
