/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dao.CardRepository;
import com.example.demo.models.Card;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
public class CardController {
    
    private CardRepository cardRepository;
    
    public CardController(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }
    
    @PostMapping("/cards")
    public Card createCard(@RequestBody Card card){
        return cardRepository.save(card);
    }
}
