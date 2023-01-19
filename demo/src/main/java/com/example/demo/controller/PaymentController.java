/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.exceptions.CardInvalidException;
import com.example.demo.exceptions.InvalidPaymentException;
import com.example.demo.models.Payment;
import com.example.demo.models.PaymentCardDTO;
import com.example.demo.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/boleto")
    public ResponseEntity<Object> boletoPayment(@Valid @RequestBody Payment payment) throws InvalidPaymentException {
        paymentService.isBoletoPaymentValid(payment);
        Long numBoleto = paymentService.boletoPayment(payment);
        return ResponseEntity.ok(numBoleto);
    }

    @PostMapping("/credit")
    public ResponseEntity<Object> creditPayment(@Valid @RequestBody PaymentCardDTO paymentCard) throws ChangeSetPersister.NotFoundException, CardInvalidException {
        paymentService.isCreditPaymentValid(paymentCard);
        String cardHolderName;
        cardHolderName = paymentService.creditPayment(paymentCard);
        return ResponseEntity.ok("Sucessfully transaction for " + cardHolderName);
    }

}
