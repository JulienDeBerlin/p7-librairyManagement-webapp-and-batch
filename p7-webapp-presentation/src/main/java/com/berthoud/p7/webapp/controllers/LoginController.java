package com.berthoud.p7.webapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = "/memberArea", method = RequestMethod.GET)
    public String getMemberArea(@RequestParam String email, @RequestParam String password) {

        return "memberAreaDemo";

    }

}
