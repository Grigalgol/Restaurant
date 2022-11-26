package com.example.restaurant.repository;

import com.example.restaurant.models.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepository extends JpaRepository<Tables, Long> {
    Tables findTablesById(long id);
}
