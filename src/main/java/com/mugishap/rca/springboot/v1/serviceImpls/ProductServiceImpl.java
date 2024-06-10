package com.mugishap.rca.springboot.v1.serviceImpls;

import com.mugishap.rca.springboot.v1.exceptions.ResourceNotFoundException;
import com.mugishap.rca.springboot.v1.models.File;
import com.mugishap.rca.springboot.v1.models.Product;
import com.mugishap.rca.springboot.v1.models.Quantity;
import com.mugishap.rca.springboot.v1.payload.request.CreateProductDTO;
import com.mugishap.rca.springboot.v1.repositories.IProductRepository;
import com.mugishap.rca.springboot.v1.repositories.IQuantityRepository;
import com.mugishap.rca.springboot.v1.services.IFileService;
import com.mugishap.rca.springboot.v1.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IFileService fileService;
    private final IProductRepository productRepository;
    private final IQuantityRepository quantityRepository;

    @Value("${uploads.directory.images}")
    private String directory;

    @Override
    public Product createProduct(CreateProductDTO dto, File file) {
        Quantity quantity = new Quantity();
        quantity.setQuantity(dto.getQuantity());
        this.quantityRepository.save(quantity);
        Product product = new Product(dto.getProductName(), dto.getProductType(), dto.getPrice(), quantity, file);
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        this.productRepository.deleteById(id);
    }

    @Override
    public Product findById(UUID id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> search(Pageable pageable, String searchKey) {
        return this.productRepository.searchAll(pageable, searchKey);
    }
}
