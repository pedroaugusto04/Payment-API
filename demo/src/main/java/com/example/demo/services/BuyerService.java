/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.BuyerRepository;
import com.example.demo.exceptions.AlreadyRegisteredException;
import com.example.demo.exceptions.CpfNotFoundException;
import com.example.demo.models.Buyer;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

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
    public Buyer saveBuyer(Buyer buyer)  throws AlreadyRegisteredException {
        if (buyerAlreadyRegistered(buyer)){
            throw new AlreadyRegisteredException();
        }
        return buyerRepository.save(buyer);
    }
    
    
    @Override
    public Buyer findByBuyerCpf(String buyerCpf) throws CpfNotFoundException{
        Buyer buyer = buyerRepository.findByCpf(buyerCpf).orElseThrow(() -> new CpfNotFoundException());
        return buyer;
    }
    
    
    @Override
    public List<Buyer> getBuyers() {
        return buyerRepository.findAll();
    }
    
    @Override
    @Transactional
    public void deleteBuyer(String buyerCpf) throws CpfNotFoundException {
        Buyer buyer = buyerRepository.findByCpf(buyerCpf).orElseThrow(() -> new CpfNotFoundException());
        buyerRepository.delete(buyer);
    }
    
    @Override
    @Transactional
    public Buyer updateBuyer(String buyerCpf, Buyer newBuyer) throws CpfNotFoundException {
        Buyer oldBuyer = buyerRepository.findByCpf(buyerCpf).orElseThrow(() -> new CpfNotFoundException());
        newBuyer.setCpf(oldBuyer.getCpf());
        return buyerRepository.save(newBuyer);
    }
    
    
    @Override
    public boolean buyerAlreadyRegistered(Buyer buyer) {
        return buyerRepository.findByCpf(buyer.getCpf()).isPresent();
    }
}
