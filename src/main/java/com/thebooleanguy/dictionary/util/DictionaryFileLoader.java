package com.thebooleanguy.dictionary.util;

import com.thebooleanguy.dictionary.model.Word;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DictionaryFileLoader {

    /**
     * Loads the dictionary file and parses its contents into a list of Word objects.
     * Each line in the file is expected to have the format: 'word|partOfSpeech|definition|example|frequency'.
     *
     * @return a list of Word objects parsed from the dictionary file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public List<Word> loadDictionary() throws IOException {
        List<Word> dictionary = new ArrayList<>();
        Resource resource = new ClassPathResource("dictionary.txt");

        // Try-with-resources to ensure the BufferedReader is closed after use
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Split the line by '|' to separate word, part of speech, definition, example, and frequency
                String[] parts = line.split("\\|");

                // Check if the line has exactly 5 parts
                if (parts.length == 5) {
                    // Parse frequency as an integer
                    int frequency = Integer.parseInt(parts[4].trim());

                    // Create a new Word object and add it to the dictionary list
                    dictionary.add(new Word(
                            parts[0].trim(),   // word
                            parts[1].trim(),   // part of speech
                            parts[2].trim(),   // definition
                            parts[3].trim(),   // example sentence
                            frequency          // frequency of use
                    ));
                }
            }
        }

        return dictionary;
    }
}
