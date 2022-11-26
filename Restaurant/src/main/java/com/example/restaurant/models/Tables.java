package com.example.restaurant.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tables")
@Data
@NoArgsConstructor
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer countPlaces;
    private Integer bookingAmount;

    public Tables(Integer countPlaces, Integer bookingAmount) {
        this.countPlaces = countPlaces;
        this.bookingAmount = bookingAmount;
    }

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "tables", cascade = CascadeType.REMOVE)
    private List<Order> order;
}
