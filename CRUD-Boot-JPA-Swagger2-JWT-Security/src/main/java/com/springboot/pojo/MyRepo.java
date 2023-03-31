package com.springboot.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRepo extends JpaRepository<Customer, String> {

}
