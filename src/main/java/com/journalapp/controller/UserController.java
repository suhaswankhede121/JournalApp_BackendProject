package com.journalapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.apiresponce.WheatherResponse;
import com.journalapp.entity.User;
import com.journalapp.repository.UserRepository;
import com.journalapp.service.UserService;
import com.journalapp.service.WheatherService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private WheatherService wheatherService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUser();
	}
	
	@PostMapping 
	public void createUser(@RequestBody User user) { 
		userService.saveNewUser(user);
	}
	
	@PutMapping
   public ResponseEntity<?> updateUser(@RequestBody User user){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
	   User userdb=userService.findUserByUsername(username);
	   if(userdb!=null) {
		   userdb.setUsername(user.getUsername());
		   userdb.setPassword(user.getPassword());
		   userService.saveNewUser(userdb);
		   return new ResponseEntity<>(HttpStatus.OK);
	   } 
	   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
	
	@DeleteMapping
	public ResponseEntity<?> deleteByUserId(){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		userRepository.deleteByUsername(auth.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/greet")
	public ResponseEntity<?> greetings(){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		WheatherResponse res=wheatherService.getWheather("pune");
		String greet="";
		if(res!=null) {
			greet=", Wheather Tempreture is "+res.getCurrent().getTemperature();
		}
		return new ResponseEntity<>("Hi "+auth.getName()+greet,HttpStatus.OK);
	}
	
}