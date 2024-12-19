package com.example.sale_tech_web.controller;

import com.example.sale_tech_web.feature.product.entity.Product;
import com.example.sale_tech_web.feature.product.manager.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/product"})
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
public class ProductController {
//    @Autowired
    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) { // Setter Injection
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }
}
