/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.models.Buyer;
import com.example.demo.services.BuyerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
public class BuyerController {
    
    private BuyerService buyerService;
    
    public BuyerController(BuyerService buyerService){
        this.buyerService = buyerService;
    }
    
    @PostMapping("/buyers")
    public Buyer createBuyer(@RequestBody Buyer buyer){
        return buyerService.saveBuyer(buyer);
    }

    
    /*@RequestMapping("/buyers")
    public String teste() {
        return "successful or not";
    }
    
    @RequestMapping("/buyers")
    public String teste2(){
        return "boletoNumber";
    }
    */
}
