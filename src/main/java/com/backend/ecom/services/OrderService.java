package com.backend.ecom.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.ecom.entities.Cart;
import com.backend.ecom.entities.Order;
import com.backend.ecom.entities.OrderItem;
import com.backend.ecom.entities.User;
import com.backend.ecom.repositories.CartRepository;
import com.backend.ecom.repositories.OrderRepository;
import com.backend.ecom.repositories.ProductRepository;
import com.backend.ecom.repositories.UserRepository;

@Service
public class OrderService {

	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order placeOrder(Long userId) {
        // Fetch user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch cart items for user
        List<Cart> cartItems = cartRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        // Create Order
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("PENDING");

        double totalPrice = 0;
        List<OrderItem> orderItems = new ArrayList<>();

//        for (OrderItem cart : orderItems) {
        for (Cart cart : cartItems) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(productRepository.findById(cart.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found")));
            item.setQuantity(cart.getQuantity());
            item.setPrice(item.getProduct().getPrice() * cart.getQuantity());

            totalPrice += item.getPrice();
            orderItems.add(item);
        }

        order.setTotalPrice(totalPrice);
        order.setItems(orderItems);

        // Save order with items
        Order savedOrder = orderRepository.save(order);

        // Clear cart after checkout
        cartRepository.deleteAll(cartItems);

        return savedOrder;
    }
    public List<Order> getOrderHistory(Long userId) {
    	User user = userRepository.findById(userId)
    			.orElseThrow(() -> new RuntimeException("User not found"));
    	
    	return orderRepository.findByUserId(userId);
    }
    
//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

//    public List<Order> findAllOrders() {
//        return orderRepository.findAll();
//    }
//    
//    public List<Order> findOrderByUserId(Long userId) {
//    	return orderRepository.findByUserId(userId);
//    }
//    
//    public Optional<Order> findOrderById(Long id) {
//    	return orderRepository.findById(id);
//    }
//
//    public Order saveOrder(Order order) {
//        return orderRepository.save(order);
//    }
}
