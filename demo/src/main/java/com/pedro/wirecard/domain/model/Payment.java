/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedro.wirecard.domain.model;

import com.pedro.wirecard.domain.validation.ValidationGroup.Boleto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.groups.Default;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pedro
 */
@Getter
@Setter
@Entity
public class Payment {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    
    @Positive(groups = {Default.class,Boleto.class})
    private double amount;
    
    @ManyToOne(optional= true)
    @JoinColumn(name = "cardNumber", nullable= true)
    @Valid
    private Card card;
}
