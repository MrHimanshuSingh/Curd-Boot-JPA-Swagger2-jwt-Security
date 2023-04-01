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
	public Optional<Customer> get(String cid) {
		return jpaRepository.findById(cid);
	}

	@Override
	public String delete(String cid) {
		Optional<Customer> optCust = get(cid);
		if (optCust.isPresent()) {
			jpaRepository.deleteById(cid);
			return "deleted";
		} else
			return "NotExist";
	}
}
