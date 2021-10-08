package com.SafetyNet.Alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Configuration
public class SafetyNetAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetAlertApplication.class, args);
	}

}
