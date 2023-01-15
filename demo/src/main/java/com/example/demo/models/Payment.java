/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author pedro
 */
@Data
@Entity
public class Payment {
    
    private double amount;
    private Type type;
    @Id
    @ManyToOne(fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    private Card card;

}
