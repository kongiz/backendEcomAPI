package com.backend.ecom.DTO;

public class CartDTO {

	public Long userId;
	public Long productId;
	public int quantity;
    public String name;
    public String imageUrl;
    public double price;
    public double total;
	
    public CartDTO(Long userId, Long productId, int quantity, String name, String imageUrl, double price,
			double total) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.name = name;
		this.imageUrl = imageUrl;
		this.price = price;
		this.total = total;
	}

	public CartDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CartDTO [productId=" + productId + ", userId=" + userId + ", quantity=" + quantity + ", name=" + name
				+ ", imageUrl=" + imageUrl + ", price=" + price + ", total=" + total + "]";
	}
	
    	
}
