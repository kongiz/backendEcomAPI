package com.backend.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecom.entities.Order;
import com.backend.ecom.services.CheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;
	
	@PostMapping
	public ResponseEntity<Order> checkout(@PathVariable Long userId) {
		Order order = checkoutService.checkout(userId);
		return ResponseEntity.ok(order);
	}
}
