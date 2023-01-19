/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.models.Buyer;
import com.example.demo.services.BuyerService;
import jakarta.validation.Valid;
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
@RequestMapping("/buyers")
public class BuyerController {
    
    private BuyerService buyerService;
    
    public BuyerController(BuyerService buyerService){
        this.buyerService = buyerService;
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT','ROLE_BUYER')")
    @PostMapping
    public Buyer saveBuyer(@Valid @RequestBody Buyer buyer){
        return buyerService.saveBuyer(buyer);
    }
}
