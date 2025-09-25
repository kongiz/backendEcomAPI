package com.backend.ecom.DTO;

public class OrderItemResponse {

	 private Long id;
	 private String productName;
	 private int quantity;
	 private double price;
	 
	 public OrderItemResponse(Long id, String productName, int quantity, double price) {
		super();
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getProductName() {
		 return productName;
	 }

	 public void setProductName(String productName) {
		 this.productName = productName;
	 }

	 public int getQuantity() {
		 return quantity;
	 }

	 public void setQuantity(int quantity) {
		 this.quantity = quantity;
	 }

	 public double getPrice() {
		 return price;
	 }

	 public void setPrice(double price) {
		 this.price = price;
	 }

	 @Override
	 public String toString() {
		return "OrderItemResponse [id=" + id + ", productName=" + productName + ", quantity=" + quantity + ", price="
				+ price + "]";
	 }
	 
	 
	 
}
