package com.mugishap.rca.springboot.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
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
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "cart", orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

}
