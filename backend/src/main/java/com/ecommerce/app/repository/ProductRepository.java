package com.ecommerce.app.repository;

import com.ecommerce.app.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Spring Data derives the query from the method name — no @Query needed
    // for something this simple. containingIgnoreCase = SQL "LIKE %term%" case-insensitive.
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    long countByCategoryId(Long categoryId);
}