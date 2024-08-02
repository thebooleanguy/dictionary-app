package com.thebooleanguy.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The entry point for the Spring Boot application.
 * This class initializes and starts the Spring Boot application context.
 */
@SpringBootApplication
public class DictionaryWebAppApplication {

	/**
	 * The main method that serves as the entry point for the Spring Boot application.
	 * It initializes the application context and opens the default web browser to the application's URL.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(DictionaryWebAppApplication.class, args);

		// Attempt to open the default web browser and navigate to the application's URL
		openBrowser("http://localhost:8080");
	}

	/**
	 * Opens the default web browser and navigates to the specified URL.
	 * This method uses the Desktop API to perform the browsing action.
	 *
	 * @param url The URL to open in the default web browser.
	 */
	private static void openBrowser(String url) {
		// Check if the Desktop API is supported on the current platform
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();

			// Check if the BROWSE action is supported
			if (desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					// Open the specified URL in the default web browser
					desktop.browse(new URI(url));
				} catch (IOException | URISyntaxException e) {
					// Print an error message if the URL cannot be opened
					System.err.println("Failed to open browser: " + e.getMessage());
				}
			}
		}
	}
}
