package com.backend.ecom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.ecom.entities.User;
import com.backend.ecom.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), new ArrayList<>()
		); // empty authorities for now
	}
}
