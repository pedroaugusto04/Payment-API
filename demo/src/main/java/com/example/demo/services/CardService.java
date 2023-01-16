/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.CardRepository;
import com.example.demo.models.Card;

/**
 *
 * @author pedro
 */
public class CardService implements ICardService {

    private CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;

    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }
}
