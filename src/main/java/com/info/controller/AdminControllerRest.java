package com.info.controller;

import com.info.model.TeamLeader;
import com.info.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
