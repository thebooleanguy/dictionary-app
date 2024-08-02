package com.thebooleanguy.dictionary.service;

import com.thebooleanguy.dictionary.dataStructure.structures.BKTree;
import com.thebooleanguy.dictionary.dataStructure.structures.LRUCache;
import com.thebooleanguy.dictionary.dataStructure.structures.Trie;
import com.thebooleanguy.dictionary.model.SearchResult;
import com.thebooleanguy.dictionary.model.Word;
import com.thebooleanguy.dictionary.util.DictionaryFileLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing and searching words in the dictionary.
 * It uses a Trie for prefix-based searches and a BK-Tree for approximate searches.
 */
@Service
public class DictionaryService {

    private Trie trie;        // Trie structure for prefix-based searches
    private BKTree bkTree;    // BK-Tree structure for approximate searches
    private List<Word> words; // List of words loaded from the dictionary
    private LRUCache queryHistory; // LRU cache for query history

    /**
     * Initializes the DictionaryService by loading the dictionary file,
     * constructing the Trie and BK-Tree, and inserting words into the BK-Tree.
     */
    @PostConstruct
    public void init() {
        DictionaryFileLoader loader = new DictionaryFileLoader();
        try {
            // Load words from the dictionary file
            words = loader.loadDictionary();

            // Initialize Trie with the loaded words
            trie = new Trie(words);

            // Initialize BK-Tree with the first word as the root
            bkTree = new BKTree(words.get(0));

            // Insert all words into the BK-Tree
            insertWordsIntoBKTree();

            // Initialize the LRU cache for query history
            queryHistory = new LRUCache(5);
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException during dictionary loading
        }
    }

    /**
     * Inserts all words into the BK-Tree.
     */
    private void insertWordsIntoBKTree() {
        for (Word word : words) {
            bkTree.add(word); // Add each word to the BK-Tree
        }
    }

    /**
     * Searches for words that match the given prefix and provides suggestions
     * if no exact matches are found.
     *
     * @param prefix The prefix to search for.
     * @return A SearchResult containing exact matches and suggestions.
     */
    public SearchResult searchWords(String prefix) {
        // Check if the result is already cached
        SearchResult cachedResult = queryHistory.getResult(prefix);
        if (cachedResult != null) {
            return cachedResult; // Return cached result if available
        }

        // Perform prefix-based search using Trie
        List<Word> prefixMatches = trie.startsWith(prefix);
        List<Word> suggestions = new ArrayList<>();

        // If no exact matches are found, use BK-Tree to find suggestions
        if (prefixMatches.isEmpty()) {
            suggestions.addAll(bkTree.search(prefix, 2)); // Edit distance threshold of 2
        }

        // Create a SearchResult object with exact matches and suggestions
        SearchResult result = new SearchResult(prefixMatches, suggestions);

        // Cache the search result
        queryHistory.addQuery(prefix, result);

        return result;
    }

    /**
     * Retrieves the list of recent queries from the LRU cache.
     *
     * @return A list of recent queries.
     */
    public List<String> getQueryHistory() {
        return queryHistory.getHistory(); // Retrieve the query history from the LRU cache
    }

    /**
     * Retrieves the cached result for a given query.
     *
     * @param query The query string to look up.
     * @return The cached SearchResult, or null if not cached.
     */
    public SearchResult getCachedResult(String query) {
        return queryHistory.getResult(query);
    }
}
