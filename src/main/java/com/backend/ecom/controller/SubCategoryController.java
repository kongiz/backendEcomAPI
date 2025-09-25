package com.backend.ecom.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecom.entities.SubCategory;
import com.backend.ecom.services.SubCategoryService;

@RestController
@RequestMapping("/api/subCategories")
public class SubCategoryController {

	private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public List<SubCategory> getAllSubCategories() {
        return subCategoryService.findAllSubCategories();
    }

    @PostMapping
    public SubCategory createSubCategory(@RequestBody SubCategory subCategory) {
        return subCategoryService.saveSubCategory(subCategory);
    }
}
