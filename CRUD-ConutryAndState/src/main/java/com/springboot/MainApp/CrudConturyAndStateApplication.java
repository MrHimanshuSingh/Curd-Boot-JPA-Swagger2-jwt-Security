package com.springboot.MainApp;

import java.util.Arrays;
import java.util.stream.Collector;
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
		CustomRepository repo = run.getBean(CustomRepository.class);
		
		Country c1 = new Country();
		c1.setConutryName("Uttar Pradesh");
		c1.setStatesName(Stream.of(new State("Agra"), new State("Aligarh"), new State("PrayagRaj"),
				new State("Chitrakoot"), new State("Etawah")).collect(Collectors.toSet()));
		repo.save(c1);
		Country c2 = new Country();
		c2.setConutryName("Madhya Pradesh");
		c2.setStatesName(Stream.of(new State("Chambal"), new State("Gwalior"), new State("Bhopal"), new State("Indore"),
				new State("Rewa")).collect(Collectors.toSet()));
		repo.save(c2);
	}

}
