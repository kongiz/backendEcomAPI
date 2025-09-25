package com.backend.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecom.entities.SaveForLater;
import com.backend.ecom.services.SaveForLaterService;

@RestController
@RequestMapping("/api/save_later")
public class SaveForLaterController {

	@Autowired
	private SaveForLaterService saveForLaterService;
	
	@GetMapping("/{userId}")
	public List<SaveForLater> getSaveForLater(@PathVariable Long userId) {
		return saveForLaterService.getUserSaveForLater(userId);
	}

	@PostMapping
	public SaveForLater addToSaveForLater(@RequestBody SaveForLater item) {
		return saveForLaterService.addToSaveForLater(item);
	}
	
	@DeleteMapping("/{userId}/{productId}")
	public void removeItem(@PathVariable Long userId, @PathVariable Long productId) {
		saveForLaterService.removeFromSaveForLater(userId, productId);
	}
}
