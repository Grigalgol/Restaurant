package com.example.restaurant.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "waiter", uniqueConstraints = @UniqueConstraint(columnNames = "phone"))
@Data
@NoArgsConstructor
public class Waiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;

    public Waiter(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "waiter", cascade = CascadeType.REMOVE)
    List<Order> order;
}
