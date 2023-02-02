/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedro.wirecard.api.controller;

import com.pedro.wirecard.domain.exception.AlreadyRegisteredException;
import com.pedro.wirecard.domain.exception.CardNotFoundException;
import com.pedro.wirecard.domain.model.Card;
import com.pedro.wirecard.domain.service.ICardService;
import jakarta.validation.Valid;
import java.util.List;
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
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/cards")
public class CardController {

    private ICardService cardService;

    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
    public ResponseEntity<Card> saveCard(@Valid @RequestBody Card card) throws AlreadyRegisteredException{
        Card newCard = cardService.saveCard(card);
        return ResponseEntity.status(201).body(newCard);
    }

    @GetMapping("/{cardNumber}")
    public Card getByCardNumber(@PathVariable Integer cardNumber) throws CardNotFoundException {
        return cardService.findByCardNumber(cardNumber);
    }

    @GetMapping
    public List<Card> getCards() {
        return cardService.getCards();
    }

    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<String> deleteCard(@PathVariable Integer cardNumber) throws CardNotFoundException {
        cardService.deleteCard(cardNumber);
        return ResponseEntity.ok("Card deleted successfully!");
    }

    @PutMapping("/{cardNumber}")
    public ResponseEntity<Object> updateCard(@Valid @RequestBody Card newCard,@PathVariable Integer cardNumber) throws CardNotFoundException {
        return ResponseEntity.ok(cardService.updateCard(cardNumber, newCard));
    }
}
