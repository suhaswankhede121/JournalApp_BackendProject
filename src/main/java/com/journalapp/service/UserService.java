package com.journalapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.journalapp.entity.JournalEntities;
import com.journalapp.entity.User;
import com.journalapp.repository.JournalEntriesRepo;
import com.journalapp.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	//how to store password in the hashed form
	private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	public void saveNewUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		userRepository.save(user);
	}
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public List<User> getAllUser(){
		return new ArrayList<>(userRepository.findAll());
	}
	
	public User getUserById(ObjectId id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public void deleteById(ObjectId id) {
		userRepository.deleteById(id);
	}
	
	public User updateById(ObjectId id, User ur) {
		userRepository.save(ur); 	
		return ur;
	}
	
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}
	public void saveAdmin(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
		userRepository.save(user);
		
	}

}
