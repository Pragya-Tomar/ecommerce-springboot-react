package com.ecommerce.app.service;

import com.ecommerce.app.dto.ProductRequest;
import com.ecommerce.app.entity.Category;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.exception.ResourceNotFoundException;
import com.ecommerce.app.repository.CategoryRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void create_throwsResourceNotFound_whenCategoryDoesNotExist() {
        ProductRequest request = new ProductRequest();
        request.setName("Wireless Mouse");
        request.setPrice(new BigDecimal("999.00"));
        request.setStockQuantity(10);
        request.setCategoryId(99L);

        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.create(request))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void create_savesProduct_whenCategoryExists() {
        Category category = Category.builder().id(1L).name("Electronics").build();
        ProductRequest request = new ProductRequest();
        request.setName("Wireless Mouse");
        request.setPrice(new BigDecimal("999.00"));
        request.setStockQuantity(10);
        request.setCategoryId(1L);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenAnswer(inv -> {
            Product p = inv.getArgument(0);
            p.setId(1L);
            return p;
        });

        var response = productService.create(request);

        assertThat(response.getName()).isEqualTo("Wireless Mouse");
        assertThat(response.getCategoryName()).isEqualTo("Electronics");
    }
}