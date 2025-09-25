package com.backend.ecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.ecom.entities.Wishlist;

public interface WishlistRepository  extends JpaRepository<Wishlist, Long> {

	List<Wishlist> findByUserId(Long userId);
	
	void deleteByUserIdAndProductId(Long userId, Long productId);
}