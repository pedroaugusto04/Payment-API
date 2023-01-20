/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.exceptions.CpfNotFoundException;
import com.example.demo.models.Buyer;
import java.util.List;

/**
 *
 * @author pedro
 */
public interface IBuyerService {
    
    public Buyer saveBuyer(Buyer buyer);
    
    public Buyer findByBuyerCpf(String buyerCpf) throws CpfNotFoundException;
    
    public List<Buyer> getBuyers();
    
    public void deleteBuyer(String buyerCpf) throws CpfNotFoundException;
    
    public Buyer updateBuyer(String buyerCpf, Buyer newBuyer) throws CpfNotFoundException;
}
