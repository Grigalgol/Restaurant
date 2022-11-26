package com.example.restaurant.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StatusEnum title;

    public Status(StatusEnum title) {
        this.title = title;
    }

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "status")
    private List<Order> order;
}
