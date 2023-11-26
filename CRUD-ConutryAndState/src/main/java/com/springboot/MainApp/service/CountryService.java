package com.springboot.MainApp.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MainApp.model.Country;
import com.springboot.MainApp.model.State;
import com.springboot.MainApp.repo.CustomRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {

	private CustomRepository customRepository;
//
//	{
//		Country c1 = new Country();
//		c1.setId(1);
//		c1.setConutryName("Uttar Pradesh");
//		c1.setStatesName(Stream.of(new State(1, "Agra"), new State(2, "Aligarh"), new State(3, "PrayagRaj"),
//				new State(4, "Chitrakoot"), new State(5, "Etawah")).collect(Collectors.toSet()));
//		customRepository.save(c1);
//		Country c2 = new Country();
//		c2.setId(2);
//		c2.setConutryName("Madhya Pradesh");
//		c2.setStatesName(Stream.of(new State(1, "Chambal"), new State(2, "Gwalior"), new State(3, "Bhopal"),
//				new State(4, "Indore"), new State(5, "Rewa ")).collect(Collectors.toSet()));
//		customRepository.save(c2);
//	}

	public ResponseEntity<Set<Country>> getcountries() {
		try {
			Set<Country> countries = customRepository.getcountries();
			if (countries.isEmpty()) {
				return new ResponseEntity<>(new HashSet<>(), HttpStatus.NO_CONTENT);
			} else
				return new ResponseEntity<>(countries, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new HashSet<>(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Set<State>> getStates(Integer id) {
		try {
			Optional<Country> countryOpt = customRepository.findById(id);
			if (countryOpt.isPresent())
				return new ResponseEntity<>(countryOpt.get().getStatesName(), HttpStatus.OK);
			else
				return new ResponseEntity<>(new HashSet<>(), HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(new HashSet<>(), HttpStatus.BAD_REQUEST);
		}
	}
}
