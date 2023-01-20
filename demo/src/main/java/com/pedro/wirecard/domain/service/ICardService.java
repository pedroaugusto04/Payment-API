/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pedro.wirecard.domain.service;

import com.pedro.wirecard.domain.exception.AlreadyRegisteredException;
import com.pedro.wirecard.domain.exception.CardNotFoundException;
import com.pedro.wirecard.domain.model.Card;
import java.util.List;

/**
 *
 * @author pedro
 */
public interface ICardService {
    
    public Card saveCard(Card card) throws AlreadyRegisteredException;
    
    public Card findByCardNumber(Integer cardNumber) throws CardNotFoundException;
    
    public List<Card> getCards();
    
    public void deleteCard(Integer cardNumber) throws CardNotFoundException;
    
    public Card updateCard(Integer cardNumber, Card newCard) throws CardNotFoundException;
    
    public boolean cardAlreadyRegistered(Card card);
}
