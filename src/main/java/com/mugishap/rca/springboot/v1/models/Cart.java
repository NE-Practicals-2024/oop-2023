package com.mugishap.rca.springboot.v1.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", orphanRemoval = true)
    private List<CartItem> items;

}
