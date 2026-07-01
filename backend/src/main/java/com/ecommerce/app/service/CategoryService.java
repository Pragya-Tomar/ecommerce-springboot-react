package com.ecommerce.app.service;

import com.ecommerce.app.dto.CategoryRequest;
import com.ecommerce.app.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Long id, CategoryRequest request);
    void delete(Long id);
    CategoryResponse getById(Long id);
    List<CategoryResponse> getAll();
}