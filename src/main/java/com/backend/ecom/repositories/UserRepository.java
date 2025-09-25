package com.backend.ecom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.ecom.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
