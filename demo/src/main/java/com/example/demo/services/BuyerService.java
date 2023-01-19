/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.BuyerRepository;
import com.example.demo.models.Buyer;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author pedro
 */
@Service
public class BuyerService implements IBuyerService {

    private BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    @Override
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Buyer saveBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }
}
