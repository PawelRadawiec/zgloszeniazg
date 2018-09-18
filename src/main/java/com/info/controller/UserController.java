package com.info.controller;

import com.info.model.User;
import com.info.model.UserDto;
import com.info.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List listUser(){
        return userService.findAll();
    }

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User create(@RequestBody UserDto user){
        return userService.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
    public User deleteUser(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return new User(id);
    }

}
