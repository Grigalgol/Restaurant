package com.example.restaurant.Service;

import com.example.restaurant.dto.UserRegistrationDto;
import com.example.restaurant.models.Client;

public interface ClientService {
    Client save(UserRegistrationDto client);
    Client findClientByPhone(String phone);
}
