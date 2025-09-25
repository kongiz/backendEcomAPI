package com.backend.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.ecom.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
