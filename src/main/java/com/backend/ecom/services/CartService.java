package com.backend.ecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.ecom.entities.Cart;
import com.backend.ecom.entities.Product;
import com.backend.ecom.entities.User;
import com.backend.ecom.repositories.CartRepository;
import com.backend.ecom.repositories.ProductRepository;
import com.backend.ecom.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	@Autowired private CartRepository cartRepository;
	@Autowired private UserRepository userRepository;
	@Autowired private ProductRepository productRepository;
	
	public List<Cart> getUserCart(Long userId) {
		return cartRepository.findByUserId(userId);
	}
	
	public Cart addToCart(Cart item) {
        // Fetch and set user
        if (item.getUser() != null && item.getUser().getId() != null) {
            User user = userRepository.findById(item.getUser().getId())
                                      .orElseThrow(() -> new RuntimeException("User not found"));
            item.setUser(user);
        }

        // Fetch and set product
        if (item.getProduct() != null && item.getProduct().getId() != null) {
            Product product = productRepository.findById(item.getProduct().getId())
                                               .orElseThrow(() -> new RuntimeException("Product not found"));
            item.setProduct(product);
        }

        return cartRepository.save(item);
    }
	@Transactional
	public void removeFromCart(Long userId, Long productId) {
		cartRepository.deleteByUserIdAndProductId(userId, productId);
	}
}
