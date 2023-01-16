/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.dao.ClientRepository;
import com.example.demo.models.Client;

/**
 *
 * @author pedro
 */
public class ClientService implements IClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
}
