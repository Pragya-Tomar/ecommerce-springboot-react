package com.ecommerce.app.service;

import com.ecommerce.app.dto.ProductRequest;
import com.ecommerce.app.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
    ProductResponse getById(Long id);
    Page<ProductResponse> getAll(Pageable pageable);
    Page<ProductResponse> search(String name, Pageable pageable);
    Page<ProductResponse> getByCategory(Long categoryId, Pageable pageable);
}