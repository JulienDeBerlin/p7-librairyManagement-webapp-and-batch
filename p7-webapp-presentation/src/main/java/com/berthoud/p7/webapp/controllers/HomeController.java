package com.berthoud.p7.webapp.controllers;

import com.berthoud.p7.webapp.business.managers.BookResearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import p7.webapp.model.beans.Librairy;

import java.util.List;


@Controller
@SessionAttributes(value = "librairyList")
public class HomeController {

    @Autowired
    BookResearchManager bookResearchManager;

    /**
     * Displays the home page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";

    }

    /**
     * Displays the book-research-form inside the home page
     */
    @RequestMapping(value = "/researchForm", method = RequestMethod.GET)
    public String displayResearchForm(ModelMap model) {
        model.addAttribute("toBeDisplayed", "researchForm");
        List<Librairy> librairyList = bookResearchManager.getAllLibrairies();
        model.addAttribute(librairyList);
        return "home";
    }

    /**
     * Displays the login-form inside the home page
     */
    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String displayLoginForm(ModelMap model) {
        model.addAttribute("toBeDisplayed", "loginForm");
        return "home";

    }


}
