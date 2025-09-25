package com.backend.ecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.ecom.entities.Category;
import com.backend.ecom.repositories.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Optional<Category> findCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    
    public void deleteCategoryById(Long id) {
    	categoryRepository.deleteById(id);
	}
}
