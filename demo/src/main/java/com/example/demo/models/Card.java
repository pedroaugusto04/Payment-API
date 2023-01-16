/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.models;

import jakarta.persistence.Entity;
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
    private Integer cardNumber;
    @Temporal(TemporalType.DATE)
    private Calendar cardExpirationDate;
    private Integer cardCvv;
    
}
