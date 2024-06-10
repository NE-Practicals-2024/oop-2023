package com.mugishap.rca.springboot.v1.models;

import com.mugishap.rca.springboot.v1.audits.InitiatorAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends InitiatorAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code")
    private UUID id;

    @Column(name = "name")
    private String productName;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "price")
    private int price;

    private LocalDateTime inDate;

    @OneToOne
    private File image;

    @OneToOne
    private Quantity quantity;

}