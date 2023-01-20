
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pedro
 */
@Getter
@Setter
@Entity
public class Card {
    
    @NotNull
    private String cardHolderName;
    
    @Id
    @NotNull
    private Integer cardNumber;
    
    @Temporal(TemporalType.DATE)
    @Nullable
    private Calendar cardExpirationDate;
    
    @NotNull
    private Integer cardCvv;
    
    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    private List<Payment> payments;

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
                && card.cardCvv.equals(this.cardCvv);

    }
}
