/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.exceptions.CpfNotFoundException;
import com.example.demo.models.Buyer;
import com.example.demo.services.BuyerService;
import jakarta.validation.Valid;
import java.util.List;
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
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/buyers")
public class BuyerController {
    
    private BuyerService buyerService;
    
    public BuyerController(BuyerService buyerService){
        this.buyerService = buyerService;
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT','ROLE_BUYER')")
    public Buyer saveBuyer(@Valid @RequestBody Buyer buyer){
        return buyerService.saveBuyer(buyer);
    }
    
    @GetMapping("/{cardNumber}")
    public Buyer getByBuyerCpf(@PathVariable String buyerCpf) throws CpfNotFoundException {
        Buyer buyer = buyerService.findByBuyerCpf(buyerCpf);
        return buyer;
    }

    @GetMapping
    public List<Buyer> getBuyers() {
        return buyerService.getBuyers();
    }

    @DeleteMapping("/{buyerCpf}")
    public void deleteBuyer(@PathVariable String buyerCpf) throws CpfNotFoundException{
        buyerService.deleteBuyer(buyerCpf);
    }
    
    @PutMapping("/{buyerCpf}")
    public void updateBuyer(@Valid @RequestBody Buyer newBuyer,@PathVariable String buyerCpf) throws CpfNotFoundException{
        buyerService.updateBuyer(buyerCpf, newBuyer);
    }
}
