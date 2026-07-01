package com.ecommerce.app.service;

import com.ecommerce.app.dto.CategoryRequest;
import com.ecommerce.app.entity.Category;
import com.ecommerce.app.repository.CategoryRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void delete_throwsIllegalState_whenCategoryStillHasProducts() {
        Category category = Category.builder().id(1L).name("Electronics").build();
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.countByCategoryId(1L)).thenReturn(5L);

        assertThatThrownBy(() -> categoryService.delete(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("still has 5 product(s)");

        verify(categoryRepository, never()).delete(any());
    }

    @Test
    void create_throwsDuplicateResourceException_whenNameAlreadyExists() {
        CategoryRequest request = new CategoryRequest();
        request.setName("Electronics");

        when(categoryRepository.existsByNameIgnoreCase("Electronics")).thenReturn(true);

        assertThatThrownBy(() -> categoryService.create(request))
                .isInstanceOf(com.ecommerce.app.exception.DuplicateResourceException.class);

        verify(categoryRepository, never()).save(any());
    }
}