package com.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.Customer;
import com.springboot.service.MyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/customer/")
public class MyRestController {

	@Autowired
	private MyService service;
	 

	@GetMapping("/get/{email}")
	public ResponseEntity<Object> getData(@PathVariable("email") String email) {
		log.info("");
		Optional<Customer> optCust = service.get(email);
		if (optCust.isPresent()) {
			return ResponseEntity.ok(optCust.get());
		} else
			return ResponseEntity.status(404).body("Not Found");
	}

	@PostMapping("/add")
	public ResponseEntity<String> addData(@RequestBody Customer customer) {
		String status = this.service.add(customer);
		if (status.equals("Inserted"))
			return ResponseEntity.ok(status);
		if (status.equals("NotInserted"))
			return ResponseEntity.badRequest().body("Something Went Wrong \n please try again");
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Data Already Exist");
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Customer customer) {
		String status = this.service.add(customer);
		if (status.equals("Updated"))
			return ResponseEntity.ok(status);
		if (status.equals("NotUpdated"))
			return ResponseEntity.badRequest().body("Something Went Wrong \n please try again");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not exist to Update recheck the input");
	}

	@DeleteMapping("/delete/{email}")
	public ResponseEntity<String> detete(@PathVariable String email) {
		String status = this.service.delete(email);
		if (status.equals("deleted"))
			return ResponseEntity.ok("Successfully Deleted");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found to Delete recheck the input");
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll() {
		List<Customer> allCustomer = service.getAll();
		if(allCustomer.isEmpty())
			return ResponseEntity.status(404).body("Empty Data");
		else
			return ResponseEntity.ok(allCustomer);
	}
}
