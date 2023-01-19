/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pedro
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Error {
    
    private Integer status;
    private String description;
    private List<Field> fields;
    
}
