package com.springboot.MainApp;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.springboot.MainApp.model.Country;
import com.springboot.MainApp.model.State;
import com.springboot.MainApp.repo.CustomRepository;

@SpringBootApplication
public class CrudConturyAndStateApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(CrudConturyAndStateApplication.class, args);
//		CustomRepository repo = run.getBean(CustomRepository.class);
//
//		Country c1 = new Country();
//		c1.setId(1);
//		c1.setCountryName("Uttar Pradesh");
//		Set<State> states1 = Stream.of(new State("Agra"), new State("Aligarh"), new State("PrayagRaj"),
//		        new State("Chitrakoot"), new State("Etawah")).collect(Collectors.toSet());
//		for (State state : states1) {
//		    state.setCountry(c1);
//		}
//		c1.setStates(states1);
//		repo.save(c1);
//
//		Country c2 = new Country();
//		c2.setId(2);
//		c2.setCountryName("Madhya Pradesh");
//		Set<State> states2 = Stream.of(new State("Chambal"), new State("Gwalior"), new State("Bhopal"), new State("Indore"),
//		        new State("Rewa")).collect(Collectors.toSet());
//		for (State state : states2) {
//		    state.setCountry(c2);
//		}
//		c2.setStates(states2);
//		repo.save(c2);

	}

}
