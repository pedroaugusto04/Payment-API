/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.dao.PaymentRepository;
import com.example.demo.models.Payment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
public class PaymentController {
    
    private PaymentRepository paymentRepository;
    
    public PaymentController(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/payments")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

}
