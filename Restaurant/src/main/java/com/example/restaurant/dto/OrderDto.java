package com.example.restaurant.dto;

import com.example.restaurant.models.Client;
import com.example.restaurant.models.Tables;
import com.example.restaurant.models.Waiter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String date_order;

    private Client client;

    private Waiter waiter;

    private Long tablesId;
}
