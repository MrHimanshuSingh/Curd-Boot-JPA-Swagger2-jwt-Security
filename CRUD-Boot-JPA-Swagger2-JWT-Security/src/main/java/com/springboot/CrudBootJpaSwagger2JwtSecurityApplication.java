package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot.pojo.Customer;
import com.springboot.service.MyService;
import com.springboot.service.MyServiceImpl;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

public class CrudBootJpaSwagger2JwtSecurityApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CrudBootJpaSwagger2JwtSecurityApplication.class, args);
//		MyService service = (MyService) context.getBean(MyServiceImpl.class);
//		for (int a = 1; a <= 5; a++) {
//			Customer customer = new Customer( a,"Name" + a, "Surname" + a, (a % 2 == 0) ? "Male" : "Female","hima"+a+"@gmail.com");
//			service.add(customer);
//		}
	}

}