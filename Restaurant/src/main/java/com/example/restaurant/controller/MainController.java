package com.example.restaurant.controller;

import com.example.restaurant.Service.ClientService;
import com.example.restaurant.Service.OrderService;
import com.example.restaurant.Service.TableService;
import com.example.restaurant.Service.WaiterService;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.models.Client;
import com.example.restaurant.models.Waiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    private final TableService tableService;
    private Authentication authentication;
    private final ClientService clientService;
    private final WaiterService waiterService;
    private final OrderService orderService;

    @Autowired
    public MainController(TableService tableService, ClientService clientService, WaiterService waiterService, OrderService orderService) {
        this.tableService = tableService;
        this.clientService = clientService;
        this.waiterService = waiterService;
        this.orderService = orderService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/makeOrder")
    public String showNewOrderForm(Model model) {
        model.addAttribute("order", new OrderDto());
        model.addAttribute("list", tableService.getAll());
        return "new_order";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("order") OrderDto orderDto) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        Client client = clientService.findClientByPhone(phone);
        orderDto.setClient(client);
        List<Waiter> list = waiterService.getAll();
        Random random = new Random();
        Waiter waiter = list.get(random.nextInt(list.size()));
        orderDto.setWaiter(waiter);
        orderService.save(orderDto);
        return "redirect:/makeOrder?success";
    }

    @GetMapping("/myOrders")
    public String showMyOrders(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        Client client = clientService.findClientByPhone(phone);
        model.addAttribute("list", orderService.getAllByClient(client));
        return "myOrders";
    }

    @GetMapping("/showMenu/{id}")
    public String showMenuPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("list", orderService.getMenusByOrderId(id));
        return "menus";
    }
}
