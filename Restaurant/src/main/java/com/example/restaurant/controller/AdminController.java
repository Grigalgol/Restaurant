package com.example.restaurant.controller;

import com.example.restaurant.Service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String showAllOrdersPage(Model model) {
        model.addAttribute("list", orderService.getAll());
        return "allOrders";
    }

    @GetMapping("/showMenu/{id}")
    public String showMenuPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("list", orderService.getMenusByOrderId(id));
        return "menus";
    }
}
