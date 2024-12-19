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

    private String name;
    private int price;
    private String category;
    private String image;

    @JsonIgnore // Không gửi quantity khi trả về JSON
    private int quantity;

    @Transient // Không lưu thuộc tính này vào cơ sở dữ liệu
    private boolean stocked;

    public boolean isStocked() {
        return this.quantity > 0; // Tự động tính toán dựa trên quantity
    }
}