package com.thebooleanguy.dictionary;

import com.thebooleanguy.dictionary.model.Word;
import com.thebooleanguy.dictionary.service.DictionaryService;
import com.thebooleanguy.dictionary.model.SearchResult;

import java.util.List;
import java.util.Scanner;

public class DictionaryCLI {

    private final DictionaryService dictionaryService;
    private final Scanner scanner;

    private static final int PAGE_SIZE = 10;

    public DictionaryCLI() {
        this.scanner = new Scanner(System.in);
        this.dictionaryService = initializeDictionaryService();
    }

    private DictionaryService initializeDictionaryService() {
        DictionaryService service = new DictionaryService();
        service.init(); // Initialize the dictionary service
        return service;
    }

    private void search() {
        System.out.println("\nEnter the prefix to search:");
        String prefix = scanner.nextLine();
        SearchResult result = dictionaryService.searchWords(prefix);

        List<Word> exactMatches = result.getExactMatches();
        List<Word> suggestions = result.getSuggestions();

        if (!exactMatches.isEmpty()) {
            System.out.println("\nPrefix Matches:");
            displayWords(exactMatches);
        } else {
            System.out.println("\nNo exact matches found for prefix \"" + prefix + "\".");
        }

        if (!suggestions.isEmpty()) {
            System.out.println("\nSuggested Words:");
            displayWords(suggestions);
        } else if (exactMatches.isEmpty()) {
            System.out.println("\nNo similar words found.");
        }
    }

    private void displayWords(List<Word> words) {
        int totalWords = words.size();
        int start = 0;
        int end = Math.min(PAGE_SIZE, totalWords);

        while (true) {
            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + words.get(i).toString());
            }

            if (end >= totalWords) {
                System.out.println("\nEnd of list. Press 'q' to quit or 'r' to search again.");
                break;
            }

            System.out.println("\nShowing " + start + " to " + (end - 1) + " of " + totalWords + " entries.");
            System.out.println("Press 'n' for next page, 'p' for previous page, 'a' to view all, or 'q' to quit.");

            String input = scanner.nextLine();
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
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void displayAllWords(List<Word> words) {
        System.out.println("\nDisplaying all entries:");
        for (int i = 0; i < words.size(); i++) {
            System.out.println((i + 1) + ". " + words.get(i).toString());
        }
        System.out.println("\nEnd of list. Press 'r' to return to search.");
        scanner.nextLine(); // Wait for user input to return
    }

    public void start() {
        while (true) {
            search();
        }
    }

    public static void main(String[] args) {
        DictionaryCLI cli = new DictionaryCLI();
        cli.start();
    }
}
