package com.example.restaurant.repository;

import com.example.restaurant.models.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter, Long> {
    Waiter findWaiterById(long id);
}
