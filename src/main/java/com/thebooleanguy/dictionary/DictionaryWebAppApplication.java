package com.thebooleanguy.dictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Entry point for the Dictionary Web Application.
 */
@SpringBootApplication
public class DictionaryWebAppApplication {

	/**
	 * Main method to launch the Spring Boot application and open the default web browser.
	 *
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DictionaryWebAppApplication.class, args);
		openBrowser("http://localhost:8080");
	}

	/**
	 * Opens the default web browser to the specified URL.
	 *
	 * @param url The URL to open in the browser.
	 */
	private static void openBrowser(String url) {
		String osName = System.getProperty("os.name").toLowerCase();

		try {
			if (osName.contains("win")) {
				// For Windows
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			} else if (osName.contains("mac")) {
				// For macOS
				Runtime.getRuntime().exec("open " + url);
			} else if (osName.contains("nix") || osName.contains("nux")) {
				// For Linux
				Runtime.getRuntime().exec("xdg-open " + url);
			} else {
				// Fallback to Desktop API if the OS is not recognized
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					if (desktop.isSupported(Desktop.Action.BROWSE)) {
						desktop.browse(new URI(url));
					}
				}
			}
		} catch (IOException | URISyntaxException e) {
			System.err.println("Failed to open browser: " + e.getMessage());
		}
	}
}
