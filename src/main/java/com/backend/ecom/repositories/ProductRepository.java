package com.backend.ecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.ecom.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByCategoryId(Long categoryId);

}
