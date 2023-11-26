package com.springboot.MainApp.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.MainApp.model.Country;
import com.springboot.MainApp.model.State;
import com.springboot.MainApp.service.CountryService;

public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/countries")
	public ResponseEntity<Set<Country>> getcountries() {
		return countryService.getcountries();
	}

	@GetMapping("/countrie/{id}")
	public ResponseEntity<Set<State>> getStates(@PathVariable Integer id) {
		return countryService.getStates(id);
	}
}
