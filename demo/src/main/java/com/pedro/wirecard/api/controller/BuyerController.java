/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pedro.wirecard.api.controller;

import com.pedro.wirecard.domain.exception.AlreadyRegisteredException;
import com.pedro.wirecard.domain.exception.CpfNotFoundException;
import com.pedro.wirecard.domain.model.Buyer;
import com.pedro.wirecard.domain.service.BuyerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/buyers")
public class BuyerController {

    private BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT','ROLE_BUYER')")
    public Buyer saveBuyer(@Valid @RequestBody Buyer buyer) throws AlreadyRegisteredException{
        return buyerService.saveBuyer(buyer);
    }

    @GetMapping("/{buyerCpf}")
    public Buyer getByBuyerCpf(@PathVariable String buyerCpf) throws CpfNotFoundException {
        return buyerService.findByBuyerCpf(buyerCpf);
    }

    @GetMapping
    public List<Buyer> getBuyers() {
        return buyerService.getBuyers();
    }

    @DeleteMapping("/{buyerCpf}")
    public ResponseEntity<String> deleteBuyer(@PathVariable String buyerCpf) throws CpfNotFoundException {
        buyerService.deleteBuyer(buyerCpf);
        return ResponseEntity.ok("Buyer deleted successfully!");
    }

    @PutMapping("/{buyerCpf}")
    public ResponseEntity<Object> updateBuyer(@Valid @RequestBody Buyer newBuyer, @PathVariable String buyerCpf) throws CpfNotFoundException {
        return ResponseEntity.ok(buyerService.updateBuyer(buyerCpf, newBuyer));
    }
}
