package com.journalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalapp.cashe.AppCashe;
import com.journalapp.entity.User;
import com.journalapp.service.UserService;
import com.journalapp.utils.JwtUtils;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AppCashe appCashe;
	
	@PostMapping("/signup")
	public void signUp(@RequestBody User user) {
		 	userService.saveNewUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = detailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtils.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
	}
	
	@GetMapping("/clear-app-cashe")
	public void clearCashe() {
		appCashe.init();
	}
}
