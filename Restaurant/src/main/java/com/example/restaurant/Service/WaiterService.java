package com.example.restaurant.Service;

import com.example.restaurant.dto.WaiterDto;
import com.example.restaurant.models.Waiter;

import java.util.List;

public interface WaiterService {
    void save(WaiterDto waiterDto);
    void delete(long id);

    List<Waiter> getAll();

    Waiter getWaiterById(long id);
}
