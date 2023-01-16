/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.models.Payment;

/**
 *
 * @author pedro
 */
public class PaymentMethods {

    public long boleto() {
        return GenerateService.generateNumberBoleto();

    }

    public boolean credit(Payment payment) {
        /*if (autorizationOfPayment) {
            return true;
        }
        return false;

    }
         */
        return false;
    }
}
