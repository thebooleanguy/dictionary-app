package com.thebooleanguy.dictionary;

import com.thebooleanguy.dictionary.model.Word;
import com.thebooleanguy.dictionary.service.DictionaryService;
import com.thebooleanguy.dictionary.model.SearchResult;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Command Line Interface (CLI) for interacting with the dictionary.
 * Provides functionality to search for words and display results in a paginated format.
 */
public class DictionaryCLI {

    private final DictionaryService dictionaryService; // Service for dictionary operations
    private final Scanner scanner; // Scanner for reading user input

    // Regular expression pattern to validate that the prefix contains only alphabetic characters
    private static final Pattern VALID_PREFIX_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    // Number of entries to display per page
    private static final int PAGE_SIZE = 10;

    // ANSI color codes for terminal output
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private static final String YELLOW = "\033[0;33m";
    private static final String BLUE = "\033[0;34m";
    private static final String CYAN = "\033[0;36m";

    /**
     * Constructs a DictionaryCLI instance, initializes the dictionary service, and sets up the scanner.
     */
    public DictionaryCLI() {
        this.scanner = new Scanner(System.in);
        this.dictionaryService = initializeDictionaryService();
    }

    /**
     * Initializes the DictionaryService by creating an instance and calling its init method.
     *
     * @return The initialized DictionaryService.
     */
    private DictionaryService initializeDictionaryService() {
        DictionaryService service = new DictionaryService();
        service.init(); // Initialize the dictionary service
        return service;
    }

    /**
     * Handles user input to search for words based on a provided prefix.
     * Validates input, performs the search, and displays results.
     */
    private void search() {
        String prefix;
        while (true) {
            System.out.println(CYAN + "\nEnter the prefix to search (letters only):" + RESET);
            prefix = scanner.nextLine().trim();
            if (prefix.isEmpty()) {
                System.out.println(RED + "Prefix cannot be empty. Please try again." + RESET);
            } else if (!VALID_PREFIX_PATTERN.matcher(prefix).matches()) {
                System.out.println(RED + "Invalid input. Only letters are allowed. Please try again." + RESET);
            } else {
                break;
            }
        }

        // Perform the search operation
        SearchResult result = dictionaryService.searchWords(prefix);
        List<Word> exactMatches = result.getExactMatches();
        List<Word> suggestions = result.getSuggestions();

        // Display exact matches or a message if none are found
        if (!exactMatches.isEmpty()) {
            System.out.println(GREEN + "\nPrefix Matches:" + RESET);
            displayWords(exactMatches);
        } else {
            System.out.println(YELLOW + "\nNo exact matches found for prefix \"" + prefix + "\"." + RESET);
        }

        // Display suggestions or a message if none are found
        if (!suggestions.isEmpty()) {
            System.out.println(GREEN + "\nSuggested Words:" + RESET);
            displayWords(suggestions);
        } else if (exactMatches.isEmpty()) {
            System.out.println(YELLOW + "\nNo similar words found." + RESET);
        }
    }

    /**
     * Displays a list of words in a paginated format.
     * Allows the user to navigate through pages or view all entries.
     *
     * @param words The list of words to display.
     */
    private void displayWords(List<Word> words) {
        int totalWords = words.size();
        int start = 0;
        int end = Math.min(PAGE_SIZE, totalWords);

        while (true) {
            // Display the current page of words
            for (int i = start; i < end; i++) {
                System.out.println(BLUE + (i + 1) + ". " + words.get(i).toString() + RESET);
            }

            // If on the last page, prompt user to quit or search again
            if (end >= totalWords) {
                System.out.println(CYAN + "\nEnd of list. Press 'q' to quit or 'r' to search again." + RESET);
                break;
            }

            // Display page navigation options
            System.out.println(CYAN + "\nShowing " + start + " to " + (end - 1) + " of " + totalWords + " entries.");
            System.out.println("Press 'n' for next page, 'p' for previous page, 'a' to view all, or 'q' to quit." + RESET);

            // Handle user input for page navigation
            String input = scanner.nextLine().trim();
            switch (input.toLowerCase()) {
                case "n":
                    start = end;
                    end = Math.min(end + PAGE_SIZE, totalWords);
                    break;
                case "p":
                    start = Math.max(start - PAGE_SIZE, 0);
                    end = start + PAGE_SIZE;
                    break;
                case "a":
                    displayAllWords(words);
                    return;
                case "q":
                    return;
                default:
                    System.out.println(RED + "Invalid option. Please try again." + RESET);
                    break;
            }
        }
    }

    /**
     * Displays all entries in the list of words.
     * Waits for user input before returning to the search prompt.
     *
     * @param words The list of words to display.
     */
    private void displayAllWords(List<Word> words) {
        System.out.println(GREEN + "\nDisplaying all entries:" + RESET);
        for (int i = 0; i < words.size(); i++) {
            System.out.println(BLUE + (i + 1) + ". " + words.get(i).toString() + RESET);
        }
        System.out.println(CYAN + "\nEnd of list. Press 'r' to return to search." + RESET);
        scanner.nextLine(); // Wait for user input to return
    }

    /**
     * Starts the CLI and continuously prompts the user to search for words.
     */
    public void start() {
        while (true) {
            search();
        }
    }

    /**
     * Main method to start the CLI application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        DictionaryCLI cli = new DictionaryCLI();
        cli.start();
    }
}
