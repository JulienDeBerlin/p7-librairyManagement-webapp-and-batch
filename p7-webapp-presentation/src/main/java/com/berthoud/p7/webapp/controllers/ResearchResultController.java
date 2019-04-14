package com.berthoud.p7.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResearchResultController {

    @RequestMapping(value = "/researchResult", method = RequestMethod.GET)
    public String getResult() {
        return "researchResultOverview";
    }

    @RequestMapping(value = "/bookDetails", method = RequestMethod.GET)
    public String getBookDetails() {
        return "researchResultDetails.html";
    }



}
