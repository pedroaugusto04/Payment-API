/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
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
    
    @Positive
    private double amount;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "cardNumber", nullable = true)
    private Card card;
}
