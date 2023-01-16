/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

/**
 *
 * @author pedro
 */
@Data
@Entity
public class Buyer {
    
    private String name;
    private String email;
    @Id
    private String cpf;
}
