/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pedro.wirecard.domain.service;

import com.pedro.wirecard.domain.exception.AlreadyRegisteredException;
import com.pedro.wirecard.domain.exception.IdNotFoundException;
import com.pedro.wirecard.domain.exception.RoleTypeNotFoundException;
import com.pedro.wirecard.domain.model.UserModel;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author pedro
 */
public interface IUserService {

    public UserModel saveUser(UserModel user) throws RoleTypeNotFoundException,AlreadyRegisteredException;

    public List<UserModel> getUsers();
    
    public UserModel findById(UUID userId) throws IdNotFoundException;
    
    public UUID findIdByUsername(String username) throws UsernameNotFoundException;

    public UserModel updateUser(UUID userId, UserModel newUser) throws IdNotFoundException;

    public void deleteUser(UUID userId) throws IdNotFoundException;
    
    public void setRole(UserModel user) throws RoleTypeNotFoundException;
    
    public void setUserRole(UserModel user) throws RoleTypeNotFoundException;

    public void setDefaultRole(UserModel user) throws RoleTypeNotFoundException;

    public void setBuyerRole(UserModel user) throws RoleTypeNotFoundException;

    public void setAdminRole(UserModel user) throws RoleTypeNotFoundException;
    
    public boolean userAlreadyRegistered(UserModel user);
}
