/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.models.Card;
import com.example.demo.services.CardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
public class CardController {
    
    private CardService cardService;
    
    public CardController(CardService cardService){
        this.cardService = cardService;
    }
    
    @PostMapping("/cards")
    public Card createCard(@RequestBody Card card){
        return cardService.saveCard(card);
    }
}
