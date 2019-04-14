package com.berthoud.p7.webapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/memberArea", method = RequestMethod.GET)
    public String getMemberArea() {
        return "memberArea";

    }

}
