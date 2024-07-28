package com.thebooleanguy.dictionary;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import com.thebooleanguy.dictionary.model.Word;
import com.thebooleanguy.dictionary.util.DictionaryFileLoader;
import com.thebooleanguy.dictionary.dataStructure.Trie;

public class DictionaryCLI {

    private Trie trie;
    private List<Word> words;
    private Scanner scanner;

    public DictionaryCLI() {
        scanner = new Scanner(System.in);
        loadDictionary();
        trie = new Trie(words); // Pass the loaded words to the Trie
        insertWordsIntoTrie();
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

    private void insertWord() {
        System.out.println("Enter the word:");
        String word = scanner.nextLine();
        System.out.println("Enter the part of speech:");
        String partOfSpeech = scanner.nextLine();
        System.out.println("Enter the definition:");
        String definition = scanner.nextLine();
        System.out.println("Enter a sentence using the word:"); // New prompt for sentence
        String sentence = scanner.nextLine();
        Word newWord = new Word(word, partOfSpeech, definition, sentence);
        words.add(newWord);
        trie.insert(words.size() - 1); // Insert the new word's index into the Trie
        System.out.println("Word inserted successfully.");
    }

    private void searchWord() {
        System.out.println("Enter the word to search:");
        String word = scanner.nextLine();
        Word result = trie.search(word);
        if (result != null) {
            System.out.println("Word found: " + result);
        } else {
            System.out.println("Word not found.");
        }
    }

    private void searchPrefix() {
        System.out.println("Enter the prefix to search:");
        String prefix = scanner.nextLine();
        List<Word> results = trie.startsWith(prefix);
        if (!results.isEmpty()) {
            System.out.println("Words found with prefix " + prefix + ":");
            for (Word word : results) {
                System.out.println(word);
            }
        } else {
            System.out.println("No words found with prefix " + prefix + ".");
        }
    }

    private void showMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Insert a word");
        System.out.println("2. Search for a word");
        System.out.println("3. Search for words with a prefix");
        System.out.println("4. Exit");
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    insertWord();
                    break;
                case 2:
                    searchWord();
                    break;
                case 3:
                    searchPrefix();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        DictionaryCLI cli = new DictionaryCLI();
        cli.start();
    }
}

