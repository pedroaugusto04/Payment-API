/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.exceptions.AlreadyRegisteredException;
import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.RoleTypeNotFoundException;
import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserModel saveUser(@Valid @RequestBody UserModel user) throws RoleTypeNotFoundException, AlreadyRegisteredException {
        return userService.saveUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{userId}")
    public UserModel getByUserId(@PathVariable UUID userId) throws IdNotFoundException {
        return userService.findById(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) throws IdNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!"); 
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserModel newUser, @PathVariable UUID userId) throws IdNotFoundException {
        return ResponseEntity.ok(userService.updateUser(userId, newUser));
    }
}
