package com.example.sale_tech_web.controller;

import com.example.sale_tech_web.feature.cart.entity.CartDTO;
import com.example.sale_tech_web.feature.cart.manager.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/cart"})
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@Slf4j
public class SaveCartController {
    private final CartService cartService;

    @GetMapping
    public List<CartDTO> getAllCart() {
        return cartService.getCartItems();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody Map<String, Object> payload) {
        try {
            Long productId = Long.valueOf((Integer) payload.get("productId"));
            int quantity = (int) payload.get("quantity");

            String result = cartService.addProductToCart(productId, quantity);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(500).body("An error occurred.");
        }
    }
}
