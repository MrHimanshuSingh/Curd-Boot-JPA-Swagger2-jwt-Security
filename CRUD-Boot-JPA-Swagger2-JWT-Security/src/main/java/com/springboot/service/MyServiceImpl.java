package com.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.pojo.Customer;
import com.springboot.repository.MyRepo;

@Service
public class MyServiceImpl implements MyService {

	@Autowired
	private MyRepo jpaRepository;

	@Override
	public String add(Customer customer) {
		Optional<Customer> optCust = get(customer.getCustomer_Email());
		if (optCust.isEmpty())
			return (jpaRepository.save(customer) != null) ? "Inserted" : "NotInserted";
		else
			return "AlreadyExisted";

	}

	@Override
	public String update(Customer customer) {
		Optional<Customer> optCust = get(customer.getCustomer_Email());
		if (optCust.isPresent())
			return (jpaRepository.save(customer) != null) ? "Updated" : "NotUpdated";
		else
			return "NotExist";
	}

	@Override
	public Optional<Customer> get(String email) {
		return jpaRepository.findById(email);
	}

	@Override
	public String delete(String email) {
		Optional<Customer> optCust = get(email);
		if (optCust.isPresent()) {
			jpaRepository.deleteById(email);
			return "deleted";
		} else
			return "NotExist";
	}
}
