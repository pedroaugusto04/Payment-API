/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.exceptions.IdNotFoundException;
import com.example.demo.exceptions.RoleTypeNotFoundException;
import com.example.demo.models.UserModel;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author pedro
 */
public interface IUserService {

    public UserModel saveUser(UserModel user) throws RoleTypeNotFoundException;

    public List<UserModel> getUsers();
    
    public UserModel findById(UUID userId) throws IdNotFoundException;

    public UserModel updateUser(UUID userId, UserModel newUser) throws IdNotFoundException;

    public void deleteUser(UUID userId) throws IdNotFoundException;
    
    public void setUserRole(UserModel user) throws RoleTypeNotFoundException;

    public void setDefaultRole(UserModel user) throws RoleTypeNotFoundException;

    public void setBuyerRole(UserModel user) throws RoleTypeNotFoundException;

    public void setAdminRole(UserModel user) throws RoleTypeNotFoundException;
}
