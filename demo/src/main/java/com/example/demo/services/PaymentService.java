/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.PaymentRepository;
import com.example.demo.models.Payment;
import org.springframework.stereotype.Service;

/**
 *
 * @author pedro
 */
@Service
public class PaymentService implements IPaymentService {
    
    private PaymentRepository paymentRepository;
    
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    
    @Override
    public long numberBoleto() {
        return IPaymentService.generateNumberBoleto();
        
    }
    
    @Override
    public boolean credit(Payment payment) {
        /*if (autorizationOfPayment) {
            return true;
        }
        return false;

    }
         */
        return false;
    }
    
    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    
}
