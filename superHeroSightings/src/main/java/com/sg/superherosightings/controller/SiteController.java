//Controller to serve web pages

package com.sg.superherosightings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class SiteController
{
    @GetMapping("/heroes")
    public String heroesPage(Model model)
    {
        return "hero";
    }

}
