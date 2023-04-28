package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.pojo.Admin;

public interface AdminRepo extends JpaRepository<Admin, String> {

	
}
