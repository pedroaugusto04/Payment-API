/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dto.PurchaseModelBoleto;
import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.InvalidBuyerException;
import com.example.demo.models.Payment;
import com.example.demo.dto.PurchaseModelCard;
import com.example.demo.exceptions.CpfNotFoundException;
import com.example.demo.exceptions.InvalidCardException;
import com.example.demo.models.Buyer;
import com.example.demo.models.Card;
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
