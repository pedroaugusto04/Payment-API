/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.models.Card;
import org.springframework.data.crossstore.ChangeSetPersister;

/**
 *
 * @author pedro
 */
public interface ICardService {
    
    public Card saveCard(Card card);
    
    public Card findCardByCardNumber(Integer cardNumber) throws ChangeSetPersister.NotFoundException;
}
