/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pedro.wirecard.domain.service;

import com.pedro.wirecard.api.model.PurchaseModelBoleto;
import com.pedro.wirecard.domain.exception.CardNotFoundException;
import com.pedro.wirecard.domain.exception.IdNotFoundException;
import com.pedro.wirecard.domain.exception.InvalidBuyerException;
import com.pedro.wirecard.domain.model.Payment;
import com.pedro.wirecard.api.model.PurchaseModelCard;
import com.pedro.wirecard.domain.exception.CpfNotFoundException;
import com.pedro.wirecard.domain.exception.InvalidCardException;
import com.pedro.wirecard.domain.model.Buyer;
import com.pedro.wirecard.domain.model.Card;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author pedro
 */
public interface IPaymentService {

    
    public String creditPayment(PurchaseModelCard payment) throws CardNotFoundException, InvalidBuyerException, CpfNotFoundException,InvalidCardException;
    
        public long boletoPayment(PurchaseModelBoleto payment) throws CpfNotFoundException;

    public static long generateNumberBoleto() {
        Random random = new Random();
        long number = Math.abs(random.nextLong() + 1000000000);
        return number;
    }
   
    public void savePayment(Payment payment);
    
    public void isCreditPaymentValid(PurchaseModelCard payment) throws CardNotFoundException, InvalidBuyerException, CpfNotFoundException,InvalidCardException;
    
    public Payment findByPaymentId(UUID paymentId) throws IdNotFoundException;
    
    public List<Payment> getPayments();
    
    public Payment updatePayment(UUID paymentId, Payment newPayment) throws IdNotFoundException;
    
    public void deletePayment(UUID paymentId) throws IdNotFoundException;
    
    public void isCardValid(Card purchaseCard) throws CardNotFoundException, InvalidCardException;
    
    public void isBuyerValid(Buyer purchaseBuyer,Card purchaseCard) throws CpfNotFoundException, InvalidBuyerException;
    
    public void isBuyerValid(Buyer purchaseBuyer) throws CpfNotFoundException;
}
