package com.vijay.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.springsecurity.entity.AuthRequest;
import com.vijay.springsecurity.entity.UserInfo;
import com.vijay.springsecurity.service.JwtService;
import com.vijay.springsecurity.service.UserInfoService;


@RestController
@RequestMapping("/")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private JwtService jwtService; 
	
	@Autowired
	private AuthenticationManager authenticationManager; 

	@GetMapping("/welcome")
	public String Welcome() {
		return "welcome to endpoint is not secure";
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/product/all")
	public String getProduct() {
		return "All Product";
	}
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/product/{id}")
	public String getMessageById(@PathVariable int id) {
		return "Welcome user "+id;
	}
	
	@PostMapping("/users/new")
	public String addUser(@RequestBody UserInfo userInfo) {
		return userInfoService.addUser(userInfo);
	}
	
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authenticate.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		}
		else {
			throw new UsernameNotFoundException("Invalid user request!!");
		}
	}
}
