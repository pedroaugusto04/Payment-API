/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pedro.wirecard.domain.service;

import com.pedro.wirecard.domain.exception.AlreadyRegisteredException;
import com.pedro.wirecard.domain.exception.CpfNotFoundException;
import com.pedro.wirecard.domain.model.Buyer;
import java.util.List;

/**
 *
 * @author pedro
 */
public interface IBuyerService {
    
    public Buyer saveBuyer(Buyer buyer) throws AlreadyRegisteredException;
    
    public Buyer findByBuyerCpf(String buyerCpf) throws CpfNotFoundException;
    
    public List<Buyer> getBuyers();
    
    public void deleteBuyer(String buyerCpf) throws CpfNotFoundException;
    
    public Buyer updateBuyer(String buyerCpf, Buyer newBuyer) throws CpfNotFoundException;
    
    public boolean buyerAlreadyRegistered(Buyer buyer);
}
