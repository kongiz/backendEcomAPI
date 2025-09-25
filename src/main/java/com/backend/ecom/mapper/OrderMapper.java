package com.backend.ecom.mapper;

import java.util.List;

import com.backend.ecom.DTO.OrderItemResponse;
import com.backend.ecom.DTO.OrderResponse;
import com.backend.ecom.entities.Order;

public class OrderMapper {
	
	public static OrderResponse toResponse(Order order) {
		OrderResponse orderResponse = new OrderResponse(null, null, null, null, null);
		orderResponse.setId(order.getId());
		orderResponse.setTotalPrice(order.getTotalPrice());
		orderResponse.setStatus(order.getStatus());
		orderResponse.setOrderDate(order.getOrderDate().toString());;
		
		List<OrderItemResponse> items = order.getItems().stream()
				.map(item -> {
					OrderItemResponse itemResponse = new OrderItemResponse(null, null, 0, 0);
					itemResponse.setId(item.getId());
					itemResponse.setProductName(item.getProduct().getName());
					itemResponse.setQuantity(item.getQuantity());
					itemResponse.setPrice(item.getPrice());
					return itemResponse;
				})
				.toList();
		
		orderResponse.setItems(items);
		return orderResponse;
		
	}

}
