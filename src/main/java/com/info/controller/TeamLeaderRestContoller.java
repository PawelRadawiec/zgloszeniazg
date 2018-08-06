package com.info.controller;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TeamLeaderRestContoller {

    @Autowired
    private TeamMemberService teamMemberService;

    @GetMapping(value = "/rest/hello")
    public String helloTeamLeader(){
        return "Hello Test";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/rest/getall")
    public List<TeamMember> getAllTeamMember(){
        return teamMemberService.getAll();
    }


}
