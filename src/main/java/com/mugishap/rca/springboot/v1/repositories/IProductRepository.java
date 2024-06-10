package com.mugishap.rca.springboot.v1.repositories;

import com.mugishap.rca.springboot.v1.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {


    @Query("SELECT p FROM Product p" +
            " WHERE (lower(p.productName)  LIKE ('%' || lower(:searchKey) || '%')) " +
            " OR (lower(p.productType) LIKE ('%' || lower(:searchKey) || '%')) ")
    Page<Product> searchAll(Pageable pageable, String searchKey);

}
