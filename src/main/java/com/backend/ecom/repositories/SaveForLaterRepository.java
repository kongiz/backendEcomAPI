package com.backend.ecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.ecom.entities.SaveForLater;

public interface SaveForLaterRepository extends JpaRepository<SaveForLater, Long> {

	List<SaveForLater> findByUserId(Long userId);
	
	void deleteByUserIdAndProductId(Long userId, Long productId);
}
