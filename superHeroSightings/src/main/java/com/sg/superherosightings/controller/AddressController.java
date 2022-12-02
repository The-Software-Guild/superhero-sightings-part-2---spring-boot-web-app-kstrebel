package com.sg.superherosightings.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sg.superherosightings.dao.AddressesDao;
import com.sg.superherosightings.dao.AddressesDaoDB;
import com.sg.superherosightings.models.Address;
import com.sg.superherosightings.service.SuperheroSightingsService;
import com.sg.superherosightings.service.SuperheroSightingsServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class AddressController
{
    AddressesDao addressesDao = new AddressesDaoDB();
    SuperheroSightingsService service = new SuperheroSightingsServiceImpl();

    public AddressController(AddressesDao addressesDao)
    {
        this.addressesDao = addressesDao;
    }

    @GetMapping("/Addresses")
    public String viewAddresses(Model model)
    {
        model.addAttribute("addressList", addressesDao.getAllAddresses());

        return "viewAddresses";
    }

    
    @GetMapping("/NewAddress")
    public String addAddress(Model model)
    {
        Address address = new Address();
        address.setAddressID(0);

        model.addAttribute("pagename", "New");
        model.addAttribute("address", address);

        return "editAddress";
    }

    @GetMapping("/EditAddress/{addressID}")
    public String editAddress(Model model, @PathVariable int addressID)
    {
        model.addAttribute("pagename", "Edit");
        model.addAttribute("address", addressesDao.getAddressesByID(addressID));

        return "editAddress";
    }
    
    @GetMapping("/DeleteAddress/{addressID}")
    public String deleteAddress(Model model, @PathVariable int addressID)
    {
        model.addAttribute("address", addressesDao.getAddressesByID(addressID));
        
        return "deleteAddress";
    }

    @PostMapping("/updateAddress")
    public ResponseEntity updateAddress(@RequestBody Address address, Model model)
    {
        try
        {
            if (address.getAddressID() == 0)
            {
                addressesDao.addAddresses(address);
            }
            else
            {
                if (addressesDao.updateAddresses(address) != 1)
                {
                    return ResponseEntity.notFound().build();
                }
            }
            return ResponseEntity.ok().build();
        }
        catch (NullPointerException e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteAddress/{addressID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAddress(@PathVariable int addressID)
    {
       addressesDao.deleteAddressByID(addressID);
    }
}
