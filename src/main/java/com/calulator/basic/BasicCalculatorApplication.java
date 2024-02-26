package com.calulator.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class BasicCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicCalculatorApplication.class, args);
	}

}
