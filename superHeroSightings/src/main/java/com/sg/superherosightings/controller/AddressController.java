package com.sg.superherosightings.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.sg.superherosightings.dao.AddressesDao;
import com.sg.superherosightings.dao.AddressesDaoDB;

import org.springframework.ui.Model;

public class AddressController
{
    AddressesDao addressesDao = new AddressesDaoDB();

    public AddressController(AddressesDao addressesDao)
    {
        this.addressesDao = addressesDao;
    }

    @GetMapping("/addresses")
    public String viewAddresses(Model model)
    {
        return "viewAddresses";
    }
}
