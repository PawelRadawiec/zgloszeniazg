package com.info.controller;

import com.info.model.TeamMember;
import com.info.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teamleader/rest")
public class RestWebController {

    @Autowired
    private TeamMemberService teamMemberServicel;

    @GetMapping(value = "/all")
    public List<TeamMember> getAll(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return teamMemberServicel.getAllMembers(auth.getName());
    }

    @DeleteMapping(value = "/delete")
    public String deleteTeamMember(@RequestParam("id")int id){
        teamMemberServicel.deleteById(id);
        return null;
    }


}
