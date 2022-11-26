package com.example.restaurant.Service;

import com.example.restaurant.dto.TableDto;
import com.example.restaurant.models.Tables;

import java.util.List;

public interface TableService {
    void save(TableDto tableDto);
    List<Tables> getAll();
    void update(TableDto tableDto);
    void delete(long id);

    Tables getTableById(long id);
}
