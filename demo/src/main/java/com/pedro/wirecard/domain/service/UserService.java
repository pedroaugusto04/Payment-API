/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedro.wirecard.domain.service;

import com.pedro.wirecard.domain.exception.AlreadyRegisteredException;
import com.pedro.wirecard.domain.exception.IdNotFoundException;
import com.pedro.wirecard.domain.exception.RoleTypeNotFoundException;
import com.pedro.wirecard.domain.model.Role;
import com.pedro.wirecard.domain.model.RoleType;
import com.pedro.wirecard.domain.model.UserModel;
import com.pedro.wirecard.domain.repository.RoleRepository;
import com.pedro.wirecard.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author pedro
 */
@Service
public class UserService implements UserDetailsService, IUserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }

    @Override
    @Transactional
    public UserModel saveUser(UserModel user) throws RoleTypeNotFoundException, AlreadyRegisteredException {
        if (userAlreadyRegistered(user)){
            throw new AlreadyRegisteredException();
        }
        setRole(user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); 
        return userRepository.save(user);
    }

    @Override
    public UserModel findById(UUID userId) throws IdNotFoundException {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new IdNotFoundException()); 
        return user;
    }
    
    @Override
    public UUID findIdByUsername(String username) throws UsernameNotFoundException {
        UUID id  = userRepository.findIdByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        return id;
    }

    @Override
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserModel updateUser(UUID userId, UserModel newUser) throws IdNotFoundException {
        UserModel oldUser = userRepository.findById(userId).orElseThrow(() -> new IdNotFoundException());
        newUser.setId(oldUser.getId());
        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) throws IdNotFoundException {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new IdNotFoundException());
        userRepository.delete(user);
    }

    @Override
    public void setDefaultRole(UserModel user) throws RoleTypeNotFoundException {
        Role roleUser = roleRepository.findByRoleType(RoleType.ROLE_CLIENT).orElseThrow(() -> new RoleTypeNotFoundException());
        user.addRole(roleUser);
    }

    @Override
    public void setBuyerRole(UserModel user) throws RoleTypeNotFoundException {
        Role roleUser = roleRepository.findByRoleType(RoleType.ROLE_BUYER).orElseThrow(() -> new RoleTypeNotFoundException());
        user.addRole(roleUser);
    }

    @Override
    public void setAdminRole(UserModel user) throws RoleTypeNotFoundException {
        Role roleUser = roleRepository.findByRoleType(RoleType.ROLE_ADMIN).orElseThrow(() -> new RoleTypeNotFoundException());
        user.addRole(roleUser);
    }
    
    @Override
    public void setRole(UserModel user) throws RoleTypeNotFoundException{
        if (user.getRoleType() != null){
            setUserRole(user);    
        } else {
            setDefaultRole(user);
        }
    }

    @Override
    public void setUserRole(UserModel user) throws RoleTypeNotFoundException {
        switch (user.getRoleType().toUpperCase()) {
            case "CLIENT" -> setDefaultRole(user);
            case "BUYER" -> setBuyerRole(user);
            case "ADMIN" -> setAdminRole(user);
            default -> setDefaultRole(user);
        }
    }

    @Override
    public boolean userAlreadyRegistered(UserModel user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }
}
