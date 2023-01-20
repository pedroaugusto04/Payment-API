/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pedro.wirecard.domain.repository;

import com.pedro.wirecard.domain.model.Card;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pedro
 */
@Repository
public interface CardRepository extends JpaRepository<Card, String> {
    
    public Optional<Card> findByCardNumber(Integer cardNumber);
}
