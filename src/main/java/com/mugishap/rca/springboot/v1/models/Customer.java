package com.mugishap.rca.springboot.v1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class Customer extends User {

    @OneToOne
    private Cart cart;

}
