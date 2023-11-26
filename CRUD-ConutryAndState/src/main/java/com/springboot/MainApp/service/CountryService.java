package com.springboot.MainApp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MainApp.model.Country;
import com.springboot.MainApp.model.State;
import com.springboot.MainApp.repo.CustomRepository;

@Service
public class CountryService {

	@Autowired
	private CustomRepository customRepository;

	public ResponseEntity<Set<Country>> getcountries() {
		try {
			List<Country> countries = customRepository.findAll();
			if (countries.isEmpty()) {
				return new ResponseEntity<>(new HashSet<>(), HttpStatus.NO_CONTENT);
			} else
				return new ResponseEntity<>(new HashSet<>(countries), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new HashSet<>(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Set<State>> getStates(Integer id) {
		try {
			Optional<Country> countryOpt = customRepository.findById(id);
			if (countryOpt.isPresent())
				return new ResponseEntity<>(countryOpt.get().getStates(), HttpStatus.OK);
			else
				return new ResponseEntity<>(new HashSet<>(), HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(new HashSet<>(), HttpStatus.BAD_REQUEST);
		}
	}
}
