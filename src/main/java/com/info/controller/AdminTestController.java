package com.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminTestController {

    @GetMapping(value="/search")
    public String homepage(){
        return "adminsearch";
    }
}
