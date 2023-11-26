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
		c1.setId(1);
		c1.setConutryName("Uttar Pradesh");
		c1.setStatesName(Stream.of(new State(1, "Agra"), new State(2, "Aligarh"), new State(2, "PrayagRaj"),
				new State(3, "Chitrakoot"), new State(1, "Etawah")).collect(Collectors.toSet()));
		repo.save(c1);
		c1.setId(1);
		c1.setConutryName("Madhya Pradesh");
		c1.setStatesName(Stream.of(new State(1, "Chambal"), new State(2, "Gwalior"), new State(2, "Bhopal"),
				new State(3, "Indore"), new State(1, "Rewa ")).collect(Collectors.toSet()));

	}

}
