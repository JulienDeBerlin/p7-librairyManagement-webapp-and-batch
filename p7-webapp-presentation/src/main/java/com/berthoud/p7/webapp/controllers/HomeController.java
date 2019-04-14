package com.berthoud.p7.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";

    }


    @RequestMapping(value = "/researchForm", method = RequestMethod.GET)
    public String displayResearchForm(ModelMap model) {
        model.addAttribute("toBeDisplayed", "researchForm");
        return "home";

    }


    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String displayLoginForm(ModelMap model) {
        model.addAttribute("toBeDisplayed", "loginForm");
        return "home";

    }



}
