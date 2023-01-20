/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.dto;

import com.example.demo.models.Card;
import com.example.demo.models.Payment;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author pedro
 */

@Getter
@Setter
public class PaymentCardDTO {
    @NotNull
    private Card card;
    @NotNull
    private Payment payment;
}
