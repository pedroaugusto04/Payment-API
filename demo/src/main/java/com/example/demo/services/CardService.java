/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.CardRepository;
import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.models.Card;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

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
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findByCardNumber(Integer cardNumber) throws CardNotFoundException {
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException());
        return card;
    }

    @Override
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteCard(Integer cardNumber) throws CardNotFoundException {
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException());
        cardRepository.delete(card);
    }

    @Override
    @Transactional
    public Card updateCard(Integer cardNumber, Card newCard) throws CardNotFoundException {
        Card oldCard = cardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new CardNotFoundException());
        newCard.setCardNumber(oldCard.getCardNumber());
        return cardRepository.save(newCard);
    }
}
