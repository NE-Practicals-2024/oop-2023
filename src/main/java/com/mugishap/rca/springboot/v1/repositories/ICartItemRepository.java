package com.mugishap.rca.springboot.v1.repositories;

import com.mugishap.rca.springboot.v1.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, UUID> {
}
