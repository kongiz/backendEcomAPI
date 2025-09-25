package com.backend.ecom.DTO;

public class WishlistDTO {

	public Long userId;
	public Long productId;
    public String name;
    public String imageUrl;
    public double price;
	
    public WishlistDTO(Long userId, Long productId, String name, String imageUrl, double price) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.name = name;
		this.imageUrl = imageUrl;
		this.price = price;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	@Override
	public String toString() {
		return "WishlistDTO [userId=" + userId + ", productId=" + productId + ", name="
				+ name + ", imageUrl=" + imageUrl + ", price=" + price + "]";
	}
    
}
