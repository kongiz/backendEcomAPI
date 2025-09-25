package com.backend.ecom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecom.DTO.CartDTO;
import com.backend.ecom.entities.Cart;
import com.backend.ecom.entities.Product;
import com.backend.ecom.entities.User;
import com.backend.ecom.repositories.CartRepository;
import com.backend.ecom.repositories.ProductRepository;
import com.backend.ecom.repositories.UserRepository;
import com.backend.ecom.services.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired private CartService cartService;
	@Autowired private UserRepository userRepository;
	@Autowired private ProductRepository productRepository;
	@Autowired private CartRepository cartRepository;
	
	@GetMapping("/{userId}")
	public List<CartDTO> getCartItems(@PathVariable Long userId) {
	    List<Cart> carts = cartRepository.findByUserId(userId);
	    List<CartDTO> cartItems = new ArrayList<>();

	    for (Cart cart : carts) {
	        Product product = cart.getProduct();
	        cartItems.add(new CartDTO(
	                cart.getUser().getId(),
	                product.getId(),
	                cart.getQuantity(),
	                product.getName(),
	                product.getImageUrl(),
	                product.getPrice(),
	                cart.getQuantity() * product.getPrice()
	        ));
	    }

	    return cartItems;
	}
	
	@PostMapping
	public ResponseEntity<?> addToCart(@RequestBody CartDTO cartDTO) {
		Optional<User> userOptional = userRepository.findById(cartDTO.userId);
		if (!userOptional.isPresent()) {
			return ResponseEntity.badRequest().body("User not found");
		}
		Optional<Product> productOptional = productRepository.findById(cartDTO.productId);
		if (!productOptional.isPresent()) {
			return ResponseEntity.badRequest().body("Product not found");
		}
		
		Cart item = new Cart();
		item.setUser(userOptional.get());
		item.setProduct(productOptional.get());
		item.setQuantity(cartDTO.quantity);
		
		cartRepository.save(item);
		return ResponseEntity.ok("Item added to cart successfully");
	}
	
	@DeleteMapping("/{userId}/{productId}")
	public void removeItem(@PathVariable Long userId, @PathVariable Long productId) {
		cartService.removeFromCart(userId, productId);
	}
}
