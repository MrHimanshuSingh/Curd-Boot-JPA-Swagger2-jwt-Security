package com.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.pojo.Customer;

public interface MyRepo extends JpaRepository<Customer, String> {
	

}
