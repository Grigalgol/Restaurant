package com.example.restaurant.controller;

import com.example.restaurant.Service.ClientServiceImpl;
import com.example.restaurant.Service.UserService;
import com.example.restaurant.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;
    private final ClientServiceImpl clientService;

    public UserRegistrationController(UserService userService, ClientServiceImpl clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrarionDto() {
        return new UserRegistrationDto();
    }

    @PostMapping("/save")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
        userService.save(userRegistrationDto);
        clientService.save(userRegistrationDto);
        return "redirect:/registration?success";
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }
}