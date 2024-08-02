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
	 * This method first attempts to use the Desktop API for Windows and macOS.
	 * If that fails or is not supported, it falls back to using the `xdg-open` command for Linux systems.
	 *
	 * @param url The URL to open in the default web browser.
	 */
	private static void openBrowser(String url) {
		// Attempt to use the Desktop API (works on Windows, macOS, and some Linux configurations)
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(new URI(url));
					return; // Exit early if successful
				} catch (IOException | URISyntaxException e) {
					System.err.println("Failed to open browser using Desktop API: " + e.getMessage());
				}
			}
		}

		// Fallback to using `xdg-open` for Unix-like systems (Linux and macOS)
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
			try {
				Runtime.getRuntime().exec(new String[]{"xdg-open", url});
			} catch (IOException e) {
				System.err.println("Failed to open browser using xdg-open: " + e.getMessage());
			}
		} else {
			System.err.println("Unsupported operating system. Unable to open browser.");
		}
	}

}
