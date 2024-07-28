// A small CLI interface mainly for testing purposes

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

    private void searchPrefix() {
        System.out.println("\nEnter the prefix to search:");
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

    public void start() {
        while (true) {
            searchPrefix();
        }
    }

    public static void main(String[] args) {
        DictionaryCLI cli = new DictionaryCLI();
        cli.start();
    }
}

