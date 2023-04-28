package com.springboot.swagger;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	protected Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.springboot"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ApiInfo apiInfo() {
		return new ApiInfo("Crud_Boot_Jpa_Swagger2_Jwt_Security_Application", "CRUD operation project", "1.0", "Terms of Service",
				new Contact("Contact_Name","Contact_Url","Contact_email"), "License of Api", "License of Url",new ArrayList());
	}
	
//	@Bean
//	Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
//				.build();
//	}
}
