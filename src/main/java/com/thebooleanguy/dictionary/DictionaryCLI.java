// A small CLI Interface mainly for testing

package com.thebooleanguy.dictionary;

import com.thebooleanguy.dictionary.dataStructure.BKTree;
import com.thebooleanguy.dictionary.dataStructure.Trie;
import com.thebooleanguy.dictionary.model.Word;
import com.thebooleanguy.dictionary.util.DictionaryFileLoader;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DictionaryCLI {

    private Trie trie;
    private BKTree bkTree;
    private List<Word> words;
    private Scanner scanner;

    private static final int PAGE_SIZE = 10;

    public DictionaryCLI() {
        scanner = new Scanner(System.in);
        loadDictionary();
        trie = new Trie(words); // Pass the loaded words to the Trie
        insertWordsIntoTrie();

        bkTree = new BKTree(words.get(0));
        insertWordsIntoBKTree();
    }

    private void loadDictionary() {
        DictionaryFileLoader loader = new DictionaryFileLoader();
        try {
            words = loader.loadDictionary();
            System.out.println("Dictionary loaded successfully.");
        } catch (IOException e) {
            System.out.println("Failed to load dictionary: " + e.getMessage());
        }
    }

    private void insertWordsIntoTrie() {
        for (int i = 0; i < words.size(); i++) {
            trie.insert(i);
        }
    }

    private void insertWordsIntoBKTree() {
        for (Word word : words) {
            bkTree.add(word);
        }
    }

    private void search() {
        System.out.println("\nEnter the prefix to search:");
        String prefix = scanner.nextLine();
        List<Word> results = trie.startsWith(prefix);

        if (!results.isEmpty()) {
            displayWords(results, prefix);
        } else {
            System.out.println("\nNo words found with prefix \"" + prefix + "\".");

            // Spellchecking
            List<Word> closeMatches = bkTree.search(prefix, 2);
            if (!closeMatches.isEmpty()) {
                displayWords(closeMatches, "Close matches");
            } else {
                System.out.println("\nNo close matches found.");
            }
        }
    }

    private void displayWords(List<Word> words, String header) {
        System.out.println("\n" + header + ":");
        int totalWords = words.size();
        int start = 0;
        int end = Math.min(PAGE_SIZE, totalWords);

        while (true) {
            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + words.get(i));
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
            System.out.println((i + 1) + ". " + words.get(i));
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
