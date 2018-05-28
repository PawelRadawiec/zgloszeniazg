package com.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HomeController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/default")
    public String deafultAfterLogin(HttpServletRequest request){
        if(request.isUserInRole("ROLE_TEAM_LEADER")){
            return "redirect:/teamleaderpage";
        } else if(request.isUserInRole("ROLE_   ADMIN")){
            return "redirect:/adminteamleader";
        }
        return "login";

    }

}
