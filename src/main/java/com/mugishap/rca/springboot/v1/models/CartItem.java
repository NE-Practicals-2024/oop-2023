package com.mugishap.rca.springboot.v1.models;

import com.mugishap.rca.springboot.v1.audits.TimestampAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem extends TimestampAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne()
    private Cart cart;

    @OneToOne()
    @JoinColumn(name = "product_code")
    private Product product;

    private int quantity;

    @Column(name = "total_for_product")
    private double totalForProduct;

}
