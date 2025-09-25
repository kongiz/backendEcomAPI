package com.backend.ecom.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.ecom.DTO.LoginResponseDTO;
import com.backend.ecom.entities.User;
import com.backend.ecom.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
		User user = userRepository.findByEmail(email);
		
		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			String token = jwtUtil.generateToken(user.getEmail());
	
			LoginResponseDTO response = new LoginResponseDTO("Login Successfull", token, user);
			return ResponseEntity.ok(response);
		} else {
			Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Credentials not matched");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
		
	}
}
