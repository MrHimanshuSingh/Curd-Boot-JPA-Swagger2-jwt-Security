package com.springboot.controller;

import java.util.Optional;

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

@RestController
@RequestMapping("/customer/")
public class MyRestController {

	@Autowired
	private MyService service;

	@SuppressWarnings("rawtypes")
	@GetMapping("get/{id}")
	public ResponseEntity getData(@PathVariable("id") String cid) {
		Optional<Customer> optCust = service.get(cid);
		if (optCust.isPresent())
			return ResponseEntity.of(optCust);
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping("/add")
	public ResponseEntity<String> addData(@RequestBody Customer customer) {
		String status = this.service.add(customer);
		if(status.equals("Inserted"))
			return ResponseEntity.ok(status);
		if(status.equals("NotInserted"))
			return ResponseEntity.badRequest().body("Something Went Wrong \n please try again");
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Data Already Exist");
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Customer customer){
		String status = this.service.add(customer);
		if(status.equals("Updated"))
			return ResponseEntity.ok(status);
		if(status.equals("NotUpdated"))
			return ResponseEntity.badRequest().body("Something Went Wrong \n please try again");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not exist to Update recheck the input");
	}
	
	@DeleteMapping("/delete/{cid}")
	public ResponseEntity<String> detete(@PathVariable("cid")String cid){
		String status = this.service.delete(cid);
		if(status.equals("deleted"))
			return ResponseEntity.ok("Successfully Inserted");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found to Delete recheck the input");
	}
}
