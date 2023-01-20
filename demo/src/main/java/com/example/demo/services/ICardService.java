/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.models.Card;
import java.util.List;

/**
 *
 * @author pedro
 */
public interface ICardService {
    
    public Card saveCard(Card card);
    
    public Card findByCardNumber(Integer cardNumber) throws CardNotFoundException;
    
    public List<Card> getCards();
    
    public void deleteCard(Integer cardNumber) throws CardNotFoundException;
    
    public Card updateCard(Integer cardNumber, Card newCard) throws CardNotFoundException;
}
