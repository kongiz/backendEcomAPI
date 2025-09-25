package com.backend.ecom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecom.entities.User;
import com.backend.ecom.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
    private final UserService userService;
	
	private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // GET /api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST /api/users
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setGender(updatedUser.getGender());
            user.setPhone(updatedUser.getPhone());
            
         // ✅ Check if password is being updated and hash it
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            
            user.setPassword(updatedUser.getPassword()); // hash if needed
            // Set other fields as needed
            return ResponseEntity.ok(userService.saveUser(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    	Optional<User> user = userService.findUserById(id);
    	if (user.isPresent()) {
    		userService.deleteUserById(id);
    		return ResponseEntity.noContent().build();
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
}
