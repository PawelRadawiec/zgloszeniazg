package com.info.controller;

import com.info.model.TeamLeader;
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

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/default")
    public ModelAndView deafultAfterLogin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TeamLeader teamLeader =  teamLeaderRepository.findByEmail(authentication.getName());
        if(teamLeader.getRole().equals("ADMIN")){
            return new ModelAndView("redirect:" + "/admin");
        } else if(teamLeader.getRole().equals("TEAM_LEADER")){
            return new ModelAndView("redirect:" + "/teamleader");
        }else{
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
