package com.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.pojo.Customer;

public class MyServiceImpl implements MyService {
	
	@Autowired
	private JpaRepository<Customer,String> jpaRepository;
	
	@Override
	public String add(Customer customer) {
		jpaRepository.save(customer);
		return null;
	}

	@Override
	public String update(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> get(Integer sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}
}
