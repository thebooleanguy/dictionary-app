package com.thebooleanguy.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point for the Spring Boot application.
 * This class is responsible for starting the Spring Boot application context.
 */
@SpringBootApplication
public class DictionaryWebAppApplication {

	/**
	 * Main method to start the Spring Boot application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		// Launch the Spring Boot application
		SpringApplication.run(DictionaryWebAppApplication.class, args);
	}
}
