/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;
import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/users")
public class UserController {
    
    private UserService  userService;
    
    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT','ROLE_BUYER')")
    @PostMapping
    public UserModel createClient(@RequestBody UserModel user) {
        return userService.saveUser(user);
    }
}
