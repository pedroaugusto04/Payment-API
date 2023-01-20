/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.models.Card;
import com.example.demo.services.CardService;
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
@RequestMapping("/cards")
public class CardController {

    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
    public Card saveCard(@Valid @RequestBody Card card) {
        return cardService.saveCard(card);
    }

    @GetMapping("/{cardNumber}")
    public Card getByCardNumber(@PathVariable Integer cardNumber) throws CardNotFoundException {
        Card card = cardService.findByCardNumber(cardNumber);
        return card;
    }

    @GetMapping
    public List<Card> getCards() {
        return cardService.getCards();
    }

    @DeleteMapping("/{cardNumber}")
    public void deleteCard(@PathVariable Integer cardNumber) throws CardNotFoundException {
        cardService.deleteCard(cardNumber);
    }

    @PutMapping("/{cardNumber}")
    public void updateCard(@Valid @RequestBody Card newCard,@PathVariable Integer cardNumber) throws CardNotFoundException {
        cardService.updateCard(cardNumber, newCard);
    }
}
