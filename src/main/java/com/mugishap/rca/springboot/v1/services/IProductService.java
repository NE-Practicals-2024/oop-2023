package com.mugishap.rca.springboot.v1.services;

import com.mugishap.rca.springboot.v1.models.Product;
import com.mugishap.rca.springboot.v1.payload.request.CreateProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IProductService {

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    Product createProduct(CreateProductDTO dto);

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    void deleteProduct(UUID id);

    Product findById(UUID id);

    Page<Product> findAll(Pageable pageable);

    Page<Product> search(Pageable pageable, String searchKey);
}
