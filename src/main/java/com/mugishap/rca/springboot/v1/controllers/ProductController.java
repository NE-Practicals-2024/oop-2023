package com.mugishap.rca.springboot.v1.controllers;

import com.mugishap.rca.springboot.v1.payload.request.CreateProductDTO;
import com.mugishap.rca.springboot.v1.payload.response.ApiResponse;
import com.mugishap.rca.springboot.v1.services.IProductService;
import com.mugishap.rca.springboot.v1.utils.Constants;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final Validator validator;

    @GetMapping(path = "/all")
    private ResponseEntity<ApiResponse> getAll(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = PageRequest.of(page, limit);
        return ResponseEntity.ok(ApiResponse.success("Products fetched successfully", this.productService.findAll(pageable)));
    }

    @GetMapping(path = "/search")
    private ResponseEntity<ApiResponse> getAll(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit,
            @RequestParam(value = "searchKey") String searchKey
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.Direction.ASC, "productName");
        return ResponseEntity.ok(ApiResponse.success("Products fetched successfully", this.productService.search(pageable, searchKey)));
    }

    @PostMapping(path = "/create")
    private ResponseEntity<ApiResponse> create(
            @RequestBody @Valid CreateProductDTO dto
    ) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().toString());
        return ResponseEntity.created(uri).body(ApiResponse.success("Product created successfully", this.productService.createProduct(dto)));
    }

    @DeleteMapping("/{productId}")
    private ResponseEntity<ApiResponse> deleteProduct(
            @PathVariable("productId") UUID id
    ) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully"));
    }

}
