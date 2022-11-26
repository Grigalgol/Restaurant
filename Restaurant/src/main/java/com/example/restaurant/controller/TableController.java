package com.example.restaurant.controller;

import com.example.restaurant.Service.TableService;
import com.example.restaurant.dto.TableDto;
import com.example.restaurant.models.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tables")
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public String getTablesPage(Model model) {
        model.addAttribute("list", tableService.getAll());
        return "tables";
    }

    @GetMapping("/showNewTablesForm")
    public String getNewTablePage(Model model) {
        model.addAttribute("table", new TableDto());
        return "new_table";
    }

    @PostMapping("/saveTable")
    public String saveTable(@ModelAttribute("table") TableDto tableDto) {
        tableService.save(tableDto);
        return "redirect:/tables/showNewTablesForm?success";
    }

    @GetMapping("/deleteTable/{id}")
    public String deleteTable(@PathVariable(value = "id") long id) {
        tableService.delete(id);
        return "redirect:/tables";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Tables tables = tableService.getTableById(id);
        TableDto tableDto = new TableDto(id, tables.getCountPlaces(), tables.getBookingAmount());
        model.addAttribute("table", tableDto);
        return "update_table";
    }

    @PostMapping("/updateTable")
    public String updateTable(@ModelAttribute("table") TableDto tableDto) {
        tableService.update(tableDto);
        return "redirect:/tables/showFormForUpdate/" + tableDto.getId() + "?success";
    }

}
