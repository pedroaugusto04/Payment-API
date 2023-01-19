/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.CardRepository;
import com.example.demo.models.Card;
import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author pedro
 */
@Service
public class CardService implements ICardService {

    private CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;

    }

    @Override
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findCardByCardNumber(Integer cardNumber) throws ChangeSetPersister.NotFoundException {
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return card;
    }
}
