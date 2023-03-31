package com.springboot.service;

import java.util.Optional;

import com.springboot.pojo.Customer;

public interface MyService {

	String add(Customer customer);

	String update(Customer customer);

	Optional<Customer> get(Integer sid);

	String delete(Integer cid);
	
}
