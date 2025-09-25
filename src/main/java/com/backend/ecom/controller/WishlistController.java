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

import com.backend.ecom.DTO.WishlistDTO;
import com.backend.ecom.entities.Product;
import com.backend.ecom.entities.User;
import com.backend.ecom.entities.Wishlist;
import com.backend.ecom.repositories.ProductRepository;
import com.backend.ecom.repositories.UserRepository;
import com.backend.ecom.repositories.WishlistRepository;
import com.backend.ecom.services.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

	@Autowired private WishlistService wishlistService;
	@Autowired private UserRepository userRepository;
	@Autowired private ProductRepository productRepository;
	@Autowired private WishlistRepository wishlistRepository;
	
	@GetMapping("/{userId}")
	public List<WishlistDTO> getWishlistItems(@PathVariable Long userId) {
	    List<Wishlist> wishlists = wishlistRepository.findByUserId(userId);
	    List<WishlistDTO> wishlistItems = new ArrayList<>();

	    for (Wishlist wishlist : wishlists) {
	        Product product = wishlist.getProduct();
	        wishlistItems.add(new WishlistDTO(
	        		wishlist.getUser().getId(),
	                product.getId(),
	                product.getName(),
	                product.getImageUrl(),
	                product.getPrice()
	        ));
	    }

	    return wishlistItems;
	}

//	@PostMapping
//	public Wishlist addToWishlist(@RequestBody Wishlist item) {
//		return wishlistService.addToWishlist(item);
//	}
	
	@PostMapping
	public ResponseEntity<?> addToWishlist(@RequestBody WishlistDTO wishlistDTO) {
		Optional<User> userOptional = userRepository.findById(wishlistDTO.userId);
		if (!userOptional.isPresent()) {
			return ResponseEntity.badRequest().body("User not found");
		}
		Optional<Product> productOptional = productRepository.findById(wishlistDTO.productId);
		if (!productOptional.isPresent()) {
			return ResponseEntity.badRequest().body("Product not found");
		}
		
		Wishlist item = new Wishlist();
		item.setUser(userOptional.get());
		item.setProduct(productOptional.get());
		
		wishlistRepository.save(item);
		return ResponseEntity.ok("Item added to wishlist successfully");
	}
	
	@DeleteMapping("/{userId}/{productId}")
	public void removeItem(@PathVariable Long userId, @PathVariable Long productId) {
		wishlistService.removeFromWishlist(userId, productId);
	}
}
