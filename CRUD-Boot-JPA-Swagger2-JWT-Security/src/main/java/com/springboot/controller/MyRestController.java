package com.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.Customer;
import com.springboot.service.MyService;

@RestController
@RequestMapping("/customer/")
public class MyRestController {
	
	@Autowired
	private MyService service;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("get/{id}")
	public ResponseEntity getData(@PathVariable("id") int cid) {
		Optional<Customer> optCust= service.get(cid);
		if(optCust.isPresent())
			return ResponseEntity.of(optCust);
		else
			return ResponseEntity.notFound().build();
	}
}
