/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedro.wirecard.api.model;

import com.pedro.wirecard.domain.model.Buyer;
import com.pedro.wirecard.domain.model.Payment;
import com.pedro.wirecard.domain.validation.ValidationGroup.BuyerCpf;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author pedro
 */

@Getter
@Setter
public class PurchaseModelCard {
    
    @NotNull
    @Valid
    @ConvertGroup(from = Default.class, to = BuyerCpf.class)
    private Buyer buyer;
    
    @NotNull
    @Valid
    private Payment payment;

}
