package com.backend.ecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.ecom.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findByUserId(Long userId);
	
	void deleteByUserIdAndProductId(Long userId, Long productId);
}
