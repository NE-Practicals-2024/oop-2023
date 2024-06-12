package com.mugishap.rca.springboot.v1.models;


import com.mugishap.rca.springboot.v1.audits.InitiatorAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchased-products")
public class Purchased extends InitiatorAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private User user;

    @OneToMany
    private List<CartItem> purchasedProducts=new ArrayList<>();

    private double total;

}
