/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.InvalidPaymentException;
import com.example.demo.models.Payment;
import com.example.demo.dto.PaymentCardDTO;
import com.example.demo.services.PaymentService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
    @PostMapping("/boleto")
    public ResponseEntity<Object> saveBoletoPayment(@Valid @RequestBody Payment payment) throws InvalidPaymentException {
        paymentService.isBoletoPaymentValid(payment);
        Long numBoleto = paymentService.boletoPayment(payment);
        return ResponseEntity.ok(numBoleto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
    @PostMapping("/credit")
    public ResponseEntity<Object> saveCreditPayment(@Valid @RequestBody PaymentCardDTO paymentCard) throws InvalidPaymentException, CardNotFoundException {
        paymentService.isCreditPaymentValid(paymentCard);
        String cardHolderName;
        cardHolderName = paymentService.creditPayment(paymentCard);
        return ResponseEntity.ok("Sucessfully transaction for " + cardHolderName);
    }

    @GetMapping("/{paymentId}")
    public Payment getByPaymentId(@PathVariable UUID paymentId) throws IdNotFoundException {
        Payment payment = paymentService.findByPaymentId(paymentId);
        return payment;
    }

    @GetMapping
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @DeleteMapping("/{paymentId}")
    public void deletePayment(@PathVariable UUID paymentId) throws IdNotFoundException {
        paymentService.deletePayment(paymentId);
    }

    @PutMapping("/{userId}")
    public void updatePayment(@Valid @RequestBody Payment newPayment, @PathVariable UUID paymentId) throws IdNotFoundException {
        paymentService.updatePayment(paymentId, newPayment);
    }
}
