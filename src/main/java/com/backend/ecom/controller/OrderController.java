package com.backend.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecom.DTO.OrderResponse;
import com.backend.ecom.entities.Order;
import com.backend.ecom.mapper.OrderMapper;
import com.backend.ecom.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/checkout/{userId}")
    public ResponseEntity<OrderResponse> checkout(@PathVariable Long userId) {
        Order order = orderService.placeOrder(userId);
        return ResponseEntity.ok(OrderMapper.toResponse(order));
    }
	
	@GetMapping("/history/{userId}")
	public ResponseEntity<List<OrderResponse>> getOrderHistory(@PathVariable Long userId) {
	    List<Order> orders = orderService.getOrderHistory(userId);
	    List<OrderResponse> responses = orders.stream()
	                                          .map(OrderMapper::toResponse)
	                                          .toList();
	    return ResponseEntity.ok(responses);
	}
//	@GetMapping("/history/{userId}")
//	public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long userId) {
//		List<Order> orders = orderService.getOrderHistory(userId);
//		return ResponseEntity.ok(orders);
//	}
	
//    @PostMapping
//    public Order createOrder(@RequestBody Order order) {
//    	return orderService.saveOrder(order);
//    }
}
