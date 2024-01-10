/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedro.wirecard.api.model;

import com.pedro.wirecard.domain.model.Buyer;
import com.pedro.wirecard.domain.model.Payment;
import com.pedro.wirecard.domain.validation.ValidationGroup.Boleto;
import com.pedro.wirecard.domain.validation.ValidationGroup.BuyerCpf;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;


/**
 *
 * @author pedro
 */

public class PurchaseModelBoleto {

    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = BuyerCpf.class)
    private Buyer buyer;

    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = Boleto.class)
    private Payment payment;

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    
}
