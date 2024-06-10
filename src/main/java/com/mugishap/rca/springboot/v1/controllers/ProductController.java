package com.mugishap.rca.springboot.v1.controllers;

import com.mugishap.rca.springboot.v1.exceptions.BadRequestException;
import com.mugishap.rca.springboot.v1.models.File;
import com.mugishap.rca.springboot.v1.payload.request.CreateProductDTO;
import com.mugishap.rca.springboot.v1.payload.response.ApiResponse;
import com.mugishap.rca.springboot.v1.services.IFileService;
import com.mugishap.rca.springboot.v1.services.IProductService;
import com.mugishap.rca.springboot.v1.utils.Constants;
import com.mugishap.rca.springboot.v1.utils.Utility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final IFileService fileService;

    @Value("${uploads.directory.images}")
    private String directory;

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
        Pageable pageable = PageRequest.of(page, limit);
        return ResponseEntity.ok(ApiResponse.success("Products fetched successfully", this.productService.search(pageable, searchKey)));
    }

    @PostMapping(path = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private ResponseEntity<ApiResponse> create(
            @RequestPart(value = "file") MultipartFile document,
            @ModelAttribute @Valid CreateProductDTO dto
    ) {
        if (!Utility.isImageFile(document)) {
            throw new BadRequestException("Only image files are allowed");
        }
        File file = this.fileService.create(document, directory);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().toString());
        return ResponseEntity.created(uri).body(ApiResponse.success("Product created successfully", this.productService.createProduct(dto, file)));
    }

    @DeleteMapping("/{productId}")
    private ResponseEntity<ApiResponse> deleteProduct(
            @PathVariable("productId") UUID id
    ) {
        this.productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully"));
    }

}
