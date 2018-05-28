package com.info.controller;

import com.info.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping(value = "/admin/getTeamLeaders")
    public ModelAndView getAllTeamLeaders(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teamLeaderList", adminService.getAllTeamLeader());
        modelAndView.setViewName("adminteamleader");
        return modelAndView;
    }
}
