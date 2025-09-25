package com.backend.ecom.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.ecom.entities.OrderItem;
import com.backend.ecom.repositories.OrderItemRepository;

@Service
public class OrderItemService {

	private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
