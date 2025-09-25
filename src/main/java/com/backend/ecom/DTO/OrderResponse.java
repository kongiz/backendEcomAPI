package com.backend.ecom.DTO;

import java.util.List;

public class OrderResponse {
	private Long id;
    private Double totalPrice;
    private String status;
    private String orderDate;
    private List<OrderItemResponse> items;
	public OrderResponse(Long id, Double totalPrice, String status, String orderDate, List<OrderItemResponse> items) {
		super();
		this.id = id;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderDate = orderDate;
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public List<OrderItemResponse> getItems() {
		return items;
	}
	public void setItems(List<OrderItemResponse> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "OrderResponse [id=" + id + ", totalPrice=" + totalPrice + ", status=" + status + ", orderDate="
				+ orderDate + ", items=" + items + "]";
	}
    
    

}
