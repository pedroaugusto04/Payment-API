/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dao.BuyerRepository;
import com.example.demo.models.Buyer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
public class BuyerController {
    
    private BuyerRepository buyerRepository;
    
    public BuyerController(BuyerRepository buyerRepository){
        this.buyerRepository = buyerRepository;
    }
    
    @RequestMapping("/create/Buyer")
    public Buyer createBuyer(@RequestBody Buyer buyer){
        return buyerRepository.save(buyer);
    }
    
    @RequestMapping("/buyer/creditCard")
    public String teste() {
        return "successful or not";
    }
    
    @RequestMapping("/buyer/boleto")
    public String teste2(){
        return "boletoNumber";
    }
    
}
