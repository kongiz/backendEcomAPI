package com.backend.ecom.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.ecom.entities.Category;
import com.backend.ecom.entities.Product;
import com.backend.ecom.repositories.CategoryRepository;
import com.backend.ecom.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired private ProductRepository productRepository;
	@Autowired private CategoryRepository categoryRepository;
	private final String uploadDir = "uploads/images/";

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    
    public Optional<Product> findProductById(Long id) {
    	return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        // Fetch and set category
    	if (product.getCategory() != null && product.getCategory().getId() != null) {
    		Category category = categoryRepository.findById(product.getCategory().getId())
    											.orElseThrow(() -> new RuntimeException("category not found"));
    		product.setCategory(category);
    	}
        
        return productRepository.save(product);
    }
    
    // Image saving logic
    public String saveImage(MultipartFile file) throws IOException {
//    	String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
    	String originalFilename = file.getOriginalFilename();
    	String filename = System.currentTimeMillis() + "_" + originalFilename;
    	
    	Path uploadPath = Paths.get(uploadDir);
    	if (!Files.exists(uploadPath)) {
    		Files.createDirectories(uploadPath);
    	}
    	
    	Path filePath = uploadPath.resolve(filename);
    	Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    	return filename;
    }

    public void deleteProductById(Long id) {
    	productRepository.deleteById(id);
    }
    
    public List<Product> getProductsByCategory(Long categoryId) {
    	return productRepository.findByCategoryId(categoryId);
    }
}
