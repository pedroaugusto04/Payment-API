/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.exceptions.CardInvalidException;
import com.example.demo.exceptions.InvalidPaymentException;
import com.example.demo.models.Payment;
import com.example.demo.models.PaymentCardDTO;
import java.util.Random;
import org.springframework.data.crossstore.ChangeSetPersister;

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
    
    public void isCreditPaymentValid(PaymentCardDTO paymentCard) throws ChangeSetPersister.NotFoundException,CardInvalidException;
    
    public void isBoletoPaymentValid(Payment payment) throws InvalidPaymentException;
}
