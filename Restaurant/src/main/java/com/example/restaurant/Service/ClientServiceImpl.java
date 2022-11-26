package com.example.restaurant.Service;

import com.example.restaurant.dto.UserRegistrationDto;
import com.example.restaurant.models.Client;
import com.example.restaurant.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(UserRegistrationDto client) {
        Client newClient = new Client(
                client.getName(),
                client.getPhone()
        );
        return clientRepository.save(newClient);
    }

    @Override
    public Client findClientByPhone(String phone) {
        return clientRepository.findClientByPhone(phone);
    }
}
