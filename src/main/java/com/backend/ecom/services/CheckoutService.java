package com.backend.ecom.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.ecom.entities.Cart;
import com.backend.ecom.entities.Order;
import com.backend.ecom.entities.OrderItem;
import com.backend.ecom.entities.Product;
import com.backend.ecom.entities.User;
import com.backend.ecom.repositories.CartRepository;
import com.backend.ecom.repositories.OrderItemRepository;
import com.backend.ecom.repositories.OrderRepository;
import com.backend.ecom.repositories.ProductRepository;
import com.backend.ecom.repositories.UserRepository;

@Service
public class CheckoutService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Order checkout(Long userId) {
		User user = userRepository.findById(userId).orElseThrow();
		
		List<Cart> carts = cartRepository.findByUserId(userId);
		if (carts.isEmpty()) throw new RuntimeException("Cart is empty");
		
		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(new Date());
		order.setStatus("PENDING");
		order = orderRepository.save(order);
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for (Cart cart : carts) {
			Product product = cart.getProduct();
			
			// Check stock
			if (product.getStock() < cart.getQuantity()) {
				throw new RuntimeException("Insufficient stock for product:" + product.getName());
			}
			
			// Deduct stock
			product.setStock(product.getStock() - cart.getQuantity());
			productRepository.save(product);
			
			// Create order item
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(product);
			orderItem.setQuantity(cart.getQuantity());
			orderItem.setPrice(product.getPrice() * cart.getQuantity());
			orderItems.add(orderItem);
		}
		
		orderItemRepository.saveAll(orderItems);
		
		// Set back to order and save
		order.setItems(orderItems);
		orderRepository.save(order);
		
		// Clear the cart
		cartRepository.deleteAll(carts);
		
		return order;
	}
}
