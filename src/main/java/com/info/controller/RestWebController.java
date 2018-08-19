package com.info.controller;

import com.info.model.TeamMember;
import com.info.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestWebController {

    @Autowired
    private TeamMemberService teamMemberServicel;

    @GetMapping(value = "/teamleader/rest/all")
    public List<TeamMember> getAll(){
        return teamMemberServicel.getAll();
    }

}
