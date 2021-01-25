package com.oriako.javaspringddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaSpringDddApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(JavaSpringDddApplication.class);
		springApplication.addListeners(new ApplicationStartedEventListener());
		springApplication.run(args);
	}

}
