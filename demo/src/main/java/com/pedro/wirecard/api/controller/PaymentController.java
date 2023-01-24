/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedro.wirecard.api.controller;

import com.pedro.wirecard.api.model.PurchaseModelBoleto;
import com.pedro.wirecard.domain.exception.CardNotFoundException;
import com.pedro.wirecard.domain.exception.IdNotFoundException;
import com.pedro.wirecard.domain.exception.InvalidBuyerException;
import com.pedro.wirecard.domain.model.Payment;
import com.pedro.wirecard.api.model.PurchaseModelCard;
import com.pedro.wirecard.domain.exception.CpfNotFoundException;
import com.pedro.wirecard.domain.exception.InvalidCardException;
import com.pedro.wirecard.domain.service.IPaymentService;
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

    private IPaymentService paymentService;

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
    @PostMapping("/boleto")
    public ResponseEntity<Long> saveBoletoPayment(@Valid @RequestBody PurchaseModelBoleto payment) throws InvalidBuyerException, CpfNotFoundException {
        Long numBoleto = paymentService.boletoPayment(payment);
        return ResponseEntity.ok(numBoleto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
    @PostMapping("/credit")
    public ResponseEntity<String> saveCreditPayment(@Valid @RequestBody PurchaseModelCard payment) throws InvalidBuyerException, CardNotFoundException, CpfNotFoundException, InvalidCardException {
        String cardHolderName = paymentService.creditPayment(payment);
        return ResponseEntity.ok("Sucessfully transaction for " + cardHolderName);
    }

    @GetMapping("/{paymentId}")
    public Payment getByPaymentId(@PathVariable UUID paymentId) throws IdNotFoundException {
        return paymentService.findByPaymentId(paymentId);
    }

    @GetMapping
    public List<Payment> getPayments() {
        return paymentService.getPayments();
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable UUID paymentId) throws IdNotFoundException {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.ok("Payment deleted successfully!");
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Object> updatePayment(@Valid @RequestBody Payment newPayment, @PathVariable UUID paymentId) throws IdNotFoundException {
        return ResponseEntity.ok(paymentService.updatePayment(paymentId, newPayment));
    }
}
