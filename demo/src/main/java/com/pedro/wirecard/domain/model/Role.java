/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedro.wirecard.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author pedro
 */

@Entity
public class Role implements GrantedAuthority{
    
    @Id
    @Column(nullable = false, unique = true)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private RoleType roleType;
    
    @Override
    public String getAuthority() {
        return this.roleType.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
    
    
    
}
