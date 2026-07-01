package com.ecommerce.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    // mappedBy = "category" means Product owns the foreign key — Category
    // just holds this collection for convenience navigation (e.g. category.getProducts()).
    // We don't cascade delete: deleting a category with products in it should be blocked,
    // not silently wipe out products — see CategoryServiceImpl.
    @Builder.Default
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
}