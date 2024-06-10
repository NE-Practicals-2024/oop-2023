package com.mugishap.rca.springboot.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mugishap.rca.springboot.v1.audits.TimestampAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}), @UniqueConstraint(columnNames = {"telephone"})})
public class User extends TimestampAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "names", nullable = false)
    private String names;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne
    private Cart cart;

    @JsonIgnore
    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String names, String telephone, String email, String password, Set<Role> roles) {
        this.names = names;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String names, String telephone, String email, String encodedPassword, Set<Role> roles, Cart cart) {
        super();
        this.names = names;
        this.telephone = telephone;
        this.email = email;
        this.password = encodedPassword;
        this.roles = roles;
        this.cart = cart;
    }
}
