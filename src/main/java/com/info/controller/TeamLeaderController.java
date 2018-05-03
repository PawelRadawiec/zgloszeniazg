package com.info.controller;

import com.info.model.TeamLeader;
import com.info.service.TeamLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TeamLeaderController {

    @Autowired
    private TeamLeaderService teamLeaderService;



    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        TeamLeader teamLeader = new TeamLeader();
        modelAndView.addObject("teamLeader", teamLeader);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid TeamLeader teamLeader, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

            teamLeaderService.save(teamLeader);
           // modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("teamLeader", new TeamLeader());
            modelAndView.setViewName("registration");

        return modelAndView;

    }

    @GetMapping(value = "/teamleaderpage")
    public ModelAndView teamLeaderPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teamleaderpage");
        return modelAndView;
    }

}
