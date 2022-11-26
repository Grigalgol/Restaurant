package com.example.restaurant.controller;

import com.example.restaurant.Service.WaiterService;
import com.example.restaurant.dto.TableDto;
import com.example.restaurant.dto.WaiterDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/waiters")
public class WaiterController {

    private final WaiterService waiterService;

    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @GetMapping
    public String getWaiterPage(Model model) {
        model.addAttribute("list", waiterService.getAll());
        return "waiters";
    }

    @GetMapping("/deleteWaiter/{id}")
    public String deleteTable(@PathVariable(value = "id") long id) {
        waiterService.delete(id);
        return "redirect:/waiters";
    }

    @GetMapping("/showNewWaiterForm")
    public String getNewWaiterPage(Model model) {
        model.addAttribute("waiter", new WaiterDto());
        return "new_waiter";
    }

    @PostMapping("/saveWaiter")
    public String saveTable(@ModelAttribute("waiter") WaiterDto waiterDto) {
        waiterService.save(waiterDto);
        return "redirect:/waiters/showNewWaiterForm?success";
    }
}
