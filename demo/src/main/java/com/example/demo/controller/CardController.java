/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.models.Card;
import com.example.demo.services.CardService;
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
@RequestMapping("/cards")
public class CardController {
    
    private CardService cardService;
    
    public CardController(CardService cardService){
        this.cardService = cardService;
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
    @PostMapping
    public Card saveCard(@Valid @RequestBody Card card){
        return cardService.saveCard(card);
    }
}
