package com.backend.ecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.ecom.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	List<Order> findByUserId(Long userId);
}
