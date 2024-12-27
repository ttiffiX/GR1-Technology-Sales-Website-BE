package com.example.sale_tech_web.feature.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(
        name = "product"
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "image")
    private String image;

    @JsonIgnore // Không gửi quantity khi trả về JSON
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Transient // Không lưu thuộc tính này vào cơ sở dữ liệu
    private boolean stocked;

    public boolean isStocked() {
        return this.quantity > 0; // Tự động tính toán dựa trên quantity
    }
}