package com.example.sale_tech_web.feature.cart.manager;

import com.example.sale_tech_web.controller.exception.ClientException;
import com.example.sale_tech_web.feature.cart.entity.Cart;
import com.example.sale_tech_web.feature.cart.entity.CartDTO;
import com.example.sale_tech_web.feature.cart.entity.CartResponse;
import com.example.sale_tech_web.feature.cart.repository.CartRepository;
import com.example.sale_tech_web.feature.product.entity.Product;
import com.example.sale_tech_web.feature.product.manager.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartResponse getCartItems() {
        List<Cart> cartItems = cartRepository.findAll();

        int totalQuantity = cartItems.stream()
                                     .mapToInt(Cart::getQuantity)
                                        .sum();

        List<CartDTO> cartDTOS = cartItems.stream().map(cart -> {
            // Tìm product theo productId
            Product product = productService.getProductById(cart.getProductId());

            // Tạo DTO CartDTO
            CartDTO CartDTO = new CartDTO();
            CartDTO.setProductId(cart.getProductId());
            CartDTO.setCategory(product.getCategory());
            CartDTO.setCartId(cart.getCart_id());
            CartDTO.setName(product.getName());
            CartDTO.setPrice(product.getPrice());
            CartDTO.setQuantity(cart.getQuantity());
            CartDTO.setImage(product.getImage());

            return CartDTO;
        }).collect(Collectors.toList());
        return new CartResponse(totalQuantity, cartDTOS);
    }

    public String addProductToCart(Long productId, int quantity) {
        Product product = productService.getProductById(productId);

        Cart existingCartItem = cartRepository.findByProductId(productId);
        if (existingCartItem != null) {
            if (existingCartItem.getQuantity() + quantity <= product.getQuantity()) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
                cartRepository.save(existingCartItem);  // Cập nhật giỏ hàng
                return "Item quantity updated successfully!";
            } else {
                throw new ClientException("Not enough stock available.");
            }
        } else {
            Cart cart = Cart.builder()
                    .productId(productId)
                    .quantity(quantity)
                    .build();
            cartRepository.save(cart);
            return "Item added successfully!";
        }
    }
}