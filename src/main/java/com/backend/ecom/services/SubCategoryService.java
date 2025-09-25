package com.backend.ecom.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.ecom.entities.SubCategory;
import com.backend.ecom.repositories.SubCategoryRepository;

@Service
public class SubCategoryService {

	private final SubCategoryRepository subCategoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public List<SubCategory> findAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    public SubCategory saveSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }
}
