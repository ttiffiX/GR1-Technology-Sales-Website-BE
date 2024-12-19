package com.example.sale_tech_web.feature.product.repository;

import com.example.sale_tech_web.feature.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
