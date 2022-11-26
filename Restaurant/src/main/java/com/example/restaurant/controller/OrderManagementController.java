package com.example.restaurant.controller;

import com.example.restaurant.Service.OrderService;
import com.example.restaurant.Service.WaiterService;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.UserRegistrationDto;
import com.example.restaurant.models.Client;
import com.example.restaurant.models.Waiter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderManagement")
public class OrderManagementController {

    private final OrderService orderService;
    private Authentication authentication;
    private final WaiterService waiterService;

    public OrderManagementController(OrderService orderService, WaiterService waiterService) {
        this.orderService = orderService;
        this.waiterService = waiterService;
    }

    @GetMapping("/notStartedOrder")
    public String showNotStartedOrder(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        Waiter waiter = waiterService.getWaiterByPhone(phone);
        model.addAttribute("list", orderService.getAllByWaiterWithNotStartedStatus(waiter));
        return "nsorder";
    }

    @GetMapping("/makeCurrent/{id}")
    public String makeCurrent(@PathVariable(value = "id") long id) {
        orderService.setCurrentStatus(id);
        return "redirect:/orderManagement/notStartedOrder";
    }

    @GetMapping("/currentOrder")
    public String showCurrentOrder(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        Waiter waiter = waiterService.getWaiterByPhone(phone);
        model.addAttribute("list", orderService.getAllByWaiterWithCurrentStatus(waiter));
        return "curorder";
    }

    @GetMapping("/makeFinished/{id}")
    public String makeFinished(@PathVariable(value = "id") long id) {
        orderService.setFinishedStatus(id);
        return "redirect:/orderManagement/currentOrder";
    }

    @GetMapping("/finishedOrder")
    public String showFinishedOrder(Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        Waiter waiter = waiterService.getWaiterByPhone(phone);
        model.addAttribute("list", orderService.getAllByWaiterWithFinishedStatus(waiter));
        return "finorder";
    }

    @GetMapping("/showServePage/{id}")
    public String showServePage(@PathVariable(value = "id") long id, Model model) {
        MenuDto menuDto = new MenuDto();
        menuDto.setOrderId(id);
        model.addAttribute("menu", menuDto);
        return "serveForm";
    }

    @PostMapping("/saveServe")
    public String saveServe(@ModelAttribute("menu") MenuDto menuDto) {
        orderService.serveClient(menuDto);
        return "redirect:/orderManagement/showServePage/" + menuDto.getOrderId() + "?success";
    }

    @GetMapping("/showMenu/{id}")
    public String showMenuPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("list", orderService.getMenusByOrderId(id));
        return "menus";
    }

}
