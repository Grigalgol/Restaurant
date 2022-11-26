package com.example.restaurant.Service;

import com.example.restaurant.dto.TableDto;
import com.example.restaurant.models.Tables;
import com.example.restaurant.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService{


    private final TablesRepository tablesRepository;

    @Autowired
    public TableServiceImpl(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    @Override
    public void save(TableDto tableDto) {
        Tables table=new Tables(tableDto.getCountPlaces(), tableDto.getBookingAmount());
        tablesRepository.save(table);
    }

    @Override
    public List<Tables> getAll() {
        return tablesRepository.findAll();
    }

    @Override
    public void update(TableDto tableDto) {
        Tables table=new Tables(tableDto.getCountPlaces(), tableDto.getBookingAmount());
        table.setId(tableDto.getId());
        tablesRepository.save(table);
    }

    @Override
    public void delete(long id) {
        tablesRepository.deleteById(id);
    }

    @Override
    public Tables getTableById(long id) {
        return tablesRepository.findTablesById(id);
    }
}
