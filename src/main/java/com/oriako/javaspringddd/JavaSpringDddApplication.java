package com.oriako.javaspringddd;

import com.oriako.javaspringddd.core.ApplicationStartedEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class JavaSpringDddApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(JavaSpringDddApplication.class);
		springApplication.addListeners(new ApplicationStartedEventListener());
		springApplication.run(args);
	}

}
