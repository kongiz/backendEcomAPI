package com.backend.ecom.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.ecom.entities.Category;
import com.backend.ecom.entities.Product;
import com.backend.ecom.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    	Optional<Product> product = productService.findProductById(id);
    	return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
    	List<Product> products = productService.getProductsByCategory(categoryId);
    	return ResponseEntity.ok(products);
    }
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProduct(
    		@RequestParam("name") String name,
    		@RequestParam("description") String description,
    		@RequestParam("price") Double price,
    		@RequestParam("stock") Integer stock,
    		@RequestParam("imageUrl") MultipartFile imageUrl,
    		@RequestParam("categoryId") Long categoryId) {
    	
    	try {
    		String imageName = productService.saveImage(imageUrl);
    		
    		Product product = new Product();
    		product.setName(name);
    		product.setDescription(description);
    		product.setPrice(price);
    		product.setStock(stock);
    		product.setImageUrl(imageName); // Save only the image name or URL
    		
    		// Set category id before storing product
    		Category category = new Category();
    		category.setId(categoryId);
    		product.setCategory(category);
    		
    		Product saved = productService.saveProduct(product);
    		return ResponseEntity.ok(saved);
    	} catch (IOException e) {
			// TODO: handle exception
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
    	Optional<Product> existingPorduct = productService.findProductById(id);
    	if (existingPorduct.isPresent()) {
    		Product product = existingPorduct.get();
    		product.setName(updatedProduct.getName());
    		product.setDescription(updatedProduct.getDescription());
    		product.setStock(updatedProduct.getStock());
    		product.setPrice(updatedProduct.getPrice());
    		product.setCategory(updatedProduct.getCategory());
    		
    		return ResponseEntity.ok(productService.saveProduct(product));
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    	Optional<Product> product = productService.findProductById(id);
    	if (product.isPresent()) {
    		productService.deleteProductById(id);
    		return ResponseEntity.noContent().build();
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
}
