package com.example.restaurant.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date_order;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Waiter waiter;

    private StatusEnum status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tables tables;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "orders_menu",
            joinColumns = @JoinColumn(
                    name = "orders_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "menu_id", referencedColumnName = "id"))
    private Collection<Menu> menus;

    public Order(String date_order, Client client, Waiter waiter, StatusEnum status, Tables tables, Collection<Menu> menus) {
        this.date_order = date_order;
        this.client = client;
        this.waiter = waiter;
        this.status = status;
        this.tables = tables;
        this.menus = menus;
    }
    public long getTotalPrice() {
        long totalPrice = 0;
        totalPrice+=tables.getBookingAmount();
        for (Menu menu : menus) {
            totalPrice += menu.getPrice();
        }
        return totalPrice;
    }
}
