/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.PaymentRepository;
import com.example.demo.dto.PurchaseModelBoleto;
import com.example.demo.exceptions.CardNotFoundException;
import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.InvalidBuyerException;
import com.example.demo.models.Card;
import com.example.demo.models.Payment;
import com.example.demo.dto.PurchaseModelCard;
import com.example.demo.exceptions.CpfNotFoundException;
import com.example.demo.exceptions.InvalidCardException;
import com.example.demo.models.Buyer;
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
    
    private BuyerService buyerService;

    public PaymentService(PaymentRepository paymentRepository, CardService cardService, BuyerService buyerService) {
        this.paymentRepository = paymentRepository;
        this.cardService = cardService;
        this.buyerService = buyerService;
    }
    
    
    @Override
    @Transactional
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }
    
    
    @Override
    public long boletoPayment(PurchaseModelBoleto purchase) throws CpfNotFoundException {
        isBuyerValid(purchase.getBuyer());
        savePayment(purchase.getPayment());
        return IPaymentService.generateNumberBoleto();

    }

    @Override
    public String creditPayment(PurchaseModelCard purchase) throws CardNotFoundException, InvalidBuyerException, CpfNotFoundException,InvalidCardException {
        isCreditPaymentValid(purchase);
        savePayment(purchase.getPayment());
        return purchase.getPayment().getCard().getCardHolderName();

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
    public void isCreditPaymentValid(PurchaseModelCard purchase) throws CardNotFoundException, InvalidBuyerException, CpfNotFoundException,InvalidCardException{
        isCardValid(purchase.getPayment().getCard());
        isBuyerValid(purchase.getBuyer(),purchase.getPayment().getCard());
    }
    
    
    @Override
    public void isCardValid(Card purchaseCard) throws CardNotFoundException, InvalidCardException{
        Card card = cardService.findByCardNumber(purchaseCard.getCardNumber());
        if (!card.equals(purchaseCard)) {
            throw new InvalidCardException();
        }
    }
    
    @Override
    public void isBuyerValid(Buyer purchaseBuyer,Card purchaseCard) throws CpfNotFoundException, InvalidBuyerException{
        Buyer buyer = buyerService.findByBuyerCpf(purchaseBuyer.getCpf());
        if (!buyer.getName().equals(purchaseCard.getCardHolderName())){
            throw new InvalidBuyerException();
        }
    }
    
    
    @Override
    public void isBuyerValid(Buyer purchaseBuyer) throws CpfNotFoundException{
        Buyer buyer = buyerService.findByBuyerCpf(purchaseBuyer.getCpf());
    }
}
