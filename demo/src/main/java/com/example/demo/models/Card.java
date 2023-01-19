/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.util.Calendar;
import lombok.Data;

/**
 *
 * @author pedro
 */
@Data
@Entity
public class Card {

    private String cardHolderName;
    @Id
    @NotNull
    private Integer cardNumber;
    @Temporal(TemporalType.DATE)
    @Nullable
    private Calendar cardExpirationDate;
    private Integer cardCvv;

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Card)) {
            return false;
        }
        Card card = (Card) object;
        return card.cardNumber.equals(this.cardNumber)
                && card.cardHolderName.equals(this.cardHolderName)
                && card.cardExpirationDate.equals(this.cardExpirationDate)
                && card.cardCvv.equals(this.cardCvv);

    }
}
