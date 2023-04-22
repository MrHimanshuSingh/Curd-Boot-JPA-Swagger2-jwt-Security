package com.springboot.service;

import java.util.List;
import java.util.Optional;

import com.springboot.pojo.Customer;

public interface MyService {

	String add(Customer customer);

	String update(Customer customer);

	Optional<Customer> get(String sid);

	String delete(String cid);

	List<Customer> getAll();

}
