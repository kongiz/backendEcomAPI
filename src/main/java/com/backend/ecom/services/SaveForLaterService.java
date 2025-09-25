package com.backend.ecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.ecom.entities.SaveForLater;
import com.backend.ecom.repositories.SaveForLaterRepository;

@Service
public class SaveForLaterService {

	@Autowired
	private SaveForLaterRepository saveForLaterRepository;
	
	public List<SaveForLater> getUserSaveForLater(Long userId) {
		return saveForLaterRepository.findByUserId(userId);
	}
	
	public SaveForLater addToSaveForLater(SaveForLater item) {
		return saveForLaterRepository.save(item);
	}
	
	public void removeFromSaveForLater(Long userId, Long productId) {
		saveForLaterRepository.deleteByUserIdAndProductId(userId, productId);
	}
}
