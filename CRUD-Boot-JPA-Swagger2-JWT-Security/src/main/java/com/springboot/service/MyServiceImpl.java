package com.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.pojo.Customer;
import com.springboot.repository.MyRepo;

public class MyServiceImpl implements MyService {

	@Autowired
	private MyRepo jpaRepository;

	@Override
	public String add(Customer customer) {
		Optional<Customer> optCust = get(customer.getCustomer_Id());
		if (optCust.isEmpty())
			return (jpaRepository.save(customer) != null) ? "Inserted" : "NotInserted";
		else
			return "AlreadyExisted";

	}

	@Override
	public String update(Customer customer) {
		Optional<Customer> optCust = get(customer.getCustomer_Id());
		if (optCust.isPresent())
			return (jpaRepository.save(customer) != null) ? "Updated" : "NotUpdated";
		else
			return "NotExist";
	}

	@Override
	public Optional<Customer> get(Integer cid) {
		return jpaRepository.findById(String.valueOf(cid));
	}

	@Override
	public String delete(Integer cid) {
		Optional<Customer> optCust = get(cid);
		if (optCust.isPresent()) {
			jpaRepository.deleteById(String.valueOf(cid));
			return "deleted";
		} else
			return "NotExist";
	}
}
