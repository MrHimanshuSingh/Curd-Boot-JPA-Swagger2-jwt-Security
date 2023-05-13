package com.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.Customer;
import com.springboot.service.RedisService;


@RestController
@RequestMapping("/redis")
public class MyRedisController {

	@Autowired
	private RedisService redisService;

	@GetMapping("/get")
	public ResponseEntity<?> get(@RequestParam String customer_Email) {
		Optional<Customer> optCust = redisService.get(customer_Email);
		if (optCust.isPresent())
			return ResponseEntity.ok(optCust.get());
		else
			return ResponseEntity.status(404).body("Not Found");
	}

	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Customer customer) {
		String status = redisService.add(customer);
		if (status.equals("success"))
			return ResponseEntity.ok("inserted Successfully");
		if (status.equals("already existed"))
			return ResponseEntity.status(409).body("ID duplicacy");
		else
			return ResponseEntity.internalServerError().body("Something Went Wrong!!");

	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Customer customer) {
		String status = redisService.update(customer);
		if (status.equals("success"))
			return ResponseEntity.ok("Updated Successfully");
		if (status.equals("not exist"))
			return ResponseEntity.status(404).body("Not Found");
		else
			return ResponseEntity.internalServerError().body("Something Went Wrong!!");
	}

	@DeleteMapping("/delete/{email}")
	public ResponseEntity<String> delete(@PathVariable String email) {
		String status = redisService.delete(email);
		if (status.equals("success"))
			return ResponseEntity.ok("Delete Succefully");
		if (status.equals("not Exist"))
			return ResponseEntity.status(404).body("Nothing found to Delete");
		else
			return ResponseEntity.internalServerError().body("Something Went Wrong!!");
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAll() {
		return ResponseEntity.ok(redisService.getAll());
	}
}
