package com.example.restaurant.Service;

import com.example.restaurant.dto.UserRegistrationDto;
import com.example.restaurant.models.User;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
