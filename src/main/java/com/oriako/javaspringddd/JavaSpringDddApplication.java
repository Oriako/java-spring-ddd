package com.oriako.javaspringddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class JavaSpringDddApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(JavaSpringDddApplication.class);
		springApplication.run(args);
	}

}
