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
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author pedro
 */
@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private double amount;
    private Type type;
    @ManyToOne(optional = true)
    @JoinColumn(name = "cardNumber", nullable = true)
    private Card card;
    /*@ManyToOne
    @JoinColumn( name = "clientId")
    private Client client;
    @OneToOne
    @JoinColumn(name = "buyerCpf")
    private Buyer buyer;*/
}
