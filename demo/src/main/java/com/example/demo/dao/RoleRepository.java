/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.dao;

import com.example.demo.models.Role;
import com.example.demo.models.RoleType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pedro
 */
public interface RoleRepository  extends JpaRepository<Role, Integer>{
    
    public Optional<Role> findByRoleType(RoleType roleType);
}
