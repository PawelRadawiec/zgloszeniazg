package com.info.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamLeaderRestContoller {

    @GetMapping(value = "/rest/hello")
    public String helloTeamLeader(){
        return "Hello Test";
    }
}
