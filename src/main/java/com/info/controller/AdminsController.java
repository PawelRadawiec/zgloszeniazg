package com.info.controller;

import com.info.model.TeamLeader;
import com.info.model.TeamMember;
import com.info.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminsController {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping(value = "/search/all")
    public List<TeamLeader> getResource() {
        return adminService.getAllTeamLeader();
    }

    @PutMapping(value = "/rest/teamleader/edit")
    public TeamLeader editTeamleader(@RequestBody TeamLeader teamLeader, @RequestParam("id") Long id){
        return adminService.editTeamLeader(teamLeader, id);
    }

    @GetMapping(value = "/rest/teamleaders")
    public List<TeamLeader> teamLeadersList(){
        return adminService.getAllTeamLeader();
    }

}
