package com.info.controller;

import com.info.model.TeamLeader;
import com.info.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminControllerRest {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping(value = "/search/all")
    public List<TeamLeader> getResource() {
        List<TeamLeader> customerTable = adminService.getAllTeamLeader();
        return customerTable;
    }
}
