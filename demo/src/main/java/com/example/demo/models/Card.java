/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cardNumber;
    @Temporal(TemporalType.DATE)
    private Calendar cardExpirationDate;
    private String cardCvv;
    
}
