package com.mugishap.rca.springboot.v1.models;

import com.mugishap.rca.springboot.v1.audits.TimestampAudit;
import com.mugishap.rca.springboot.v1.enums.EPurchasedStatus;
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
@Table(name = "purchased_products")
public class Purchased extends TimestampAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne()
    private Cart cart;

    @OneToOne()
    @JoinColumn(name = "product_code")
    private Product product;

    private int quantity;

    private int total;

    @Enumerated(EnumType.STRING)
    private EPurchasedStatus status = EPurchasedStatus.PENDING;
}
