package com.example.sale_tech_web.feature.product.manager;

import com.example.sale_tech_web.feature.product.entity.Product;
import com.example.sale_tech_web.feature.product.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
//    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public void setProductService(ProductRepository productRepository) { // Setter Injection
        this.productRepository = productRepository;
    }

    public ProductService() {
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }
}
