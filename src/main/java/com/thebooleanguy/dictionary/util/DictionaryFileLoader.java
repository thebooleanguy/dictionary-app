// Parses a dictionary file where each line has the format 'word|partOfSpeech|definition|example'

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

    public List<Word> loadDictionary() throws IOException {
        List<Word> dictionary = new ArrayList<>();
        Resource resource;
        resource = new ClassPathResource("dictionary.txt");

        // Try-with-resources to ensure the BufferedReader is closed after use
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Split the line by '|' to separate word, part of speech, and definition
                String[] parts = line.split("\\|");
                // Check if the line has exactly 4 parts
                if (parts.length == 5) {
                    // Parse frequency
                    int frequency = Integer.parseInt(parts[4].trim());
                    // Add a new WordInfo object to the dictionary list
                    dictionary.add(new Word(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), frequency));
                }
            }
        }

        return dictionary;
    }
}
