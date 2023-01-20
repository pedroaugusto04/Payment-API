/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.exceptions;

/**
 *
 * @author pedro
 */
public class AlreadyRegisteredException extends Exception{

    public AlreadyRegisteredException() {
    }

    public AlreadyRegisteredException(String message) {
        super(message);
    }
    
}
