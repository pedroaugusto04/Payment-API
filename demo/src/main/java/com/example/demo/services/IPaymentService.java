/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.models.Payment;
import java.util.Random;

/**
 *
 * @author pedro
 */
public interface IPaymentService {

    public Payment savePayment(Payment payment);
    
    public boolean credit(Payment payment);
    
    public long numberBoleto();

    public static long generateNumberBoleto() {
        Random random = new Random();
        long number = random.nextLong() + 1000000000;
        return number;
    }
}
