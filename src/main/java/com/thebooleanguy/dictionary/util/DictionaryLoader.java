// Parses a dictionary file where each line has the format 'word|partOfSpeech|definition'

package com.thebooleanguy.dictionary.util;

import com.thebooleanguy.dictionary.model.WordInfo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DictionaryLoader {

    public List<WordInfo> loadDictionary() throws IOException {
        List<WordInfo> dictionary = new ArrayList<>();
        Resource resource;
        resource = new ClassPathResource("dictionary.txt");

        // Try-with-resources to ensure the BufferedReader is closed after use
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Split the line by '|' to separate word, part of speech, and definition
                String[] parts = line.split("\\|");
                // Check if the line has exactly 3 parts
                if (parts.length == 3) {
                    // Add a new WordInfo object to the dictionary list
                    dictionary.add(new WordInfo(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
        }

        return dictionary;
    }
}
