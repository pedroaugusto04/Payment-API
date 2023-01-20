/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedro.wirecard.domain.model;

import com.pedro.wirecard.domain.validation.ValidationGroup.BuyerCpf;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pedro
 */
@Getter
@Setter
@Entity
public class Buyer {
    
    @Size(max = 40)
    @NotNull
    private String name;
    
    @Email
    @NotNull
    private String email;
    
    @Id
    @NotNull(groups = {Default.class,BuyerCpf.class})
    private String cpf;
}
