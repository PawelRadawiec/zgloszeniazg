package com.info.controller;

import com.info.model.Admin;
import com.info.model.TeamLeader;
import com.info.repository.AdminRepository;
import com.info.repository.TeamLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HomeController {

    @Autowired
    TeamLeaderRepository teamLeaderRepository;

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/default")
    public ModelAndView deafultAfterLogin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = adminRepository.findAdminByEmail(authentication.getName());
        TeamLeader teamLeader =  teamLeaderRepository.findByEmail(authentication.getName());
        if(admin != null){
            return new ModelAndView("redirect:" + "/admin");
        } else if(teamLeader != null && teamLeader.getRole().equals("TEAM_LEADER")) {
            return new ModelAndView("redirect:" + "/teamleader");
        } else{
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

    @GetMapping(value = "/access-denied")
    public ModelAndView access(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accesdenimed");
        return modelAndView;
    }

}
