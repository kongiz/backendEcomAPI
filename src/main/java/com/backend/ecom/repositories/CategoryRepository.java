package com.backend.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.ecom.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
