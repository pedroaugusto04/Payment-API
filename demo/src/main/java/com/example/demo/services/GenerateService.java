/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import java.util.Random;

/**
 *
 * @author pedro
 */
public class GenerateService {

    public static long generateNumberBoleto() {
        Random random = new Random();
        long number = random.nextLong() + 1000000000;
        return number;
    }
}
