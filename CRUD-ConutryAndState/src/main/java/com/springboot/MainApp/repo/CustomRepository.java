package com.springboot.MainApp.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.MainApp.model.Country;

public interface CustomRepository extends JpaRepository<Country, Integer> {

	@Query("SELECT C.id,C.countryName FROM Country C")
	Set<Country> getcountries();

}
