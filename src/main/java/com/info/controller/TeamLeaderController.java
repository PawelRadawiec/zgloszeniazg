package com.info.controller;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.service.TeamLeaderService;
import com.info.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TeamLeaderController {

    @Autowired
    private TeamLeaderService teamLeaderService;

    @Autowired
    private TeamMemberService teamMemberService;


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
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        }else{
            teamLeaderService.save(teamLeader);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("teamLeader", new TeamLeader());
            modelAndView.setViewName("registration");
        }
        return modelAndView;

    }

    @GetMapping(value = "/teamleaderpage")
    public ModelAndView teamLeaderPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teamLeaderName", teamLeaderService.helloTeamLeader());
        modelAndView.addObject("memberlist", teamMemberService.getAllMembers());
        modelAndView.setViewName("teamleaderpage");
        return modelAndView;
    }

    @GetMapping(value = "/registerTeamMember")
    public ModelAndView registerTeamMember(){
        ModelAndView modelAndView = new ModelAndView();
        TeamMember teamMember = new TeamMember();
        modelAndView.addObject("teamMember", teamMember);
        modelAndView.setViewName("memberregistration");
        return modelAndView;
    }

    @PostMapping(value = "/registerTeamMember")
    public ModelAndView createTeamMember(@Valid TeamMember teamMember, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("memberregistration");
        }else{
            teamMemberService.save(teamMember);
            modelAndView.addObject("successMessage", "Członek drużyny został poprawnie dodany");
            modelAndView.addObject("teamMember", new TeamMember());
            modelAndView.setViewName("memberregistration");
        }
        return modelAndView;
    }

    @GetMapping(value = "/memberlist")
    public ModelAndView getListOfMembers(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("memberlist", teamMemberService.getAllMembers());
        modelAndView.setViewName("teamleaderpage");
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView changeTeamMember(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        TeamMember teamMember = this.teamMemberService.findById(id);
        modelAndView.addObject("teamMember", teamMember);
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @PostMapping(value = "/edit/{id}")
    public ModelAndView editTeamMember(@Valid TeamMember teamMember,  @PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teamMember", teamMember);
        teamMemberService.editTeamMember(teamMember, id);
        modelAndView.setViewName("edit");
        return modelAndView;
    }

}
