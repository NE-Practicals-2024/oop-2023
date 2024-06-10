package com.mugishap.rca.springboot.v1.repositories;

import com.mugishap.rca.springboot.v1.models.Cart;
import com.mugishap.rca.springboot.v1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.OpaqueTokenDsl;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICartRepository extends JpaRepository<Cart, UUID> {

    Optional<Cart> findByUser_Id(UUID customerId);

}
