package com.backend.ecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.ecom.entities.Wishlist;
import com.backend.ecom.repositories.WishlistRepository;

@Service
public class WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;
	
	public List<Wishlist> getUserWishlist(Long userId) {
		return wishlistRepository.findByUserId(userId);
	}
	
	public Wishlist addToWishlist(Wishlist item) {
		return wishlistRepository.save(item);
	}
	
	public void removeFromWishlist(Long userId, Long productId) {
		wishlistRepository.deleteByUserIdAndProductId(userId, productId);
	}
}
