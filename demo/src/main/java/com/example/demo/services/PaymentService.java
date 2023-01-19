/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.PaymentRepository;
import com.example.demo.exceptions.CardInvalidException;
import com.example.demo.exceptions.InvalidPaymentException;
import com.example.demo.models.Card;
import com.example.demo.models.Payment;
import com.example.demo.models.PaymentCardDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    @ResponseStatus(HttpStatus.CREATED)
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void isCreditPaymentValid(PaymentCardDTO paymentCard) throws ChangeSetPersister.NotFoundException, CardInvalidException {
        Card card = cardService.findCardByCardNumber(paymentCard.getCard().getCardNumber());
        if (!card.equals(paymentCard.getCard())) {
            throw new CardInvalidException();
        }

    }

    @Override
    public void isBoletoPaymentValid(Payment payment) throws InvalidPaymentException{
        // logic 
        boolean valid = true;
        if (!valid){
            throw new InvalidPaymentException();
        }
    }
}
