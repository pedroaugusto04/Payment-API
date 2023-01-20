/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.PaymentRepository;
import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.InvalidPaymentException;
import com.example.demo.models.Card;
import com.example.demo.models.Payment;
import com.example.demo.dto.PaymentCardDTO;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 *
 * @author pedro
 */
@Service
public class PaymentService implements IPaymentService {

    private PaymentRepository paymentRepository;

    private CardService cardService;

    public PaymentService(PaymentRepository paymentRepository, CardService cardService) {
        this.paymentRepository = paymentRepository;
        this.cardService = cardService;
    }

    @Override
    public long boletoPayment(Payment payment) {
        savePayment(payment);
        return IPaymentService.generateNumberBoleto();

    }

    @Override
    public String creditPayment(PaymentCardDTO paymentCard) {
        savePayment(paymentCard.getPayment());
        return paymentCard.getCard().getCardHolderName();

    }

    @Override
    @Transactional
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Payment findByPaymentId(UUID paymentId) throws IdNotFoundException {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new IdNotFoundException()); // exceptionHandler
        return payment;
    }

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    @Override
    @Transactional
    public Payment updatePayment(UUID paymentId, Payment newPayment) throws IdNotFoundException {
        Payment oldPayment = paymentRepository.findById(paymentId).orElseThrow(() -> new IdNotFoundException());
        newPayment.setId(oldPayment.getId());
        return paymentRepository.save(newPayment);
    }

    @Override
    @Transactional
    public void deletePayment(UUID paymentId) throws IdNotFoundException {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new IdNotFoundException());
        paymentRepository.delete(payment);
    }

    @Override
    public void isCreditPaymentValid(PaymentCardDTO paymentCard) throws CardNotFoundException, InvalidPaymentException {
        Card card = cardService.findByCardNumber(paymentCard.getCard().getCardNumber());
        if (!card.equals(paymentCard.getCard())) {
            throw new InvalidPaymentException();
        }

    }

    @Override
    public void isBoletoPaymentValid(Payment payment) throws InvalidPaymentException {
        // logic 
        boolean valid = true;
        if (!valid) {
            throw new InvalidPaymentException();
        }
    }
}
