package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.pojo.Customer;

public interface MyRepo extends JpaRepository<Customer, String> {
	

}
