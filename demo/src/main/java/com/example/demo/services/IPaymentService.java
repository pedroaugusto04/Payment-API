/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.InvalidPaymentException;
import com.example.demo.models.Payment;
import com.example.demo.dto.PaymentCardDTO;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author pedro
 */
public interface IPaymentService {

    
    public String creditPayment(PaymentCardDTO paymentCard);
    
    public long boletoPayment(Payment payment);

    public static long generateNumberBoleto() {
        Random random = new Random();
        long number = Math.abs(random.nextLong() + 1000000000);
        return number;
    }
   
    public void savePayment(Payment payment);
    
    public void isCreditPaymentValid(PaymentCardDTO paymentCard) throws CardNotFoundException, InvalidPaymentException;
    
    public void isBoletoPaymentValid(Payment payment) throws InvalidPaymentException;
    
    public Payment findByPaymentId(UUID paymentId) throws IdNotFoundException;
    
    public List<Payment> getPayments();
    
    public Payment updatePayment(UUID paymentId, Payment newPayment) throws IdNotFoundException;
    
    public void deletePayment(UUID paymentId) throws IdNotFoundException;
}
