package com.springboot.dao;

import java.util.List;
import java.util.Optional;

import com.springboot.pojo.Customer;

public interface RedisDao {
	String add(Customer customer);

	String update(Customer customer);

	String delete(String email);

	Optional<Customer> get(String email);

	List<Customer> getAll();
}
