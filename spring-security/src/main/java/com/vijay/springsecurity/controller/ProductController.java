package com.vijay.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class ProductController {

	
	@GetMapping("/welcome")
	public String Welcome() {
		return "welcome to endpoint is not secure";
	}
	
	@GetMapping("/product/all")
	public String getProduct() {
		return "All Product";
	}
	
	@GetMapping("/")
	public String getMessage() {
		return "Welcome";
	}
	
	@GetMapping("/product/{id}")
	public String getMessageById(@PathVariable int id) {
		return "Welcome user "+id;
	}
}
