package com.thebooleanguy.dictionary.service;

import com.thebooleanguy.dictionary.dataStructure.structures.BKTree;
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
    private QueryHistory queryHistory;

    /**
     * Initializes the DictionaryService by loading the dictionary file,
     * constructing the Trie and BK-Tree, and inserting words into the BK-Tree.
     */
    @PostConstruct
    public void init() {
        DictionaryFileLoader loader = new DictionaryFileLoader();
        try {
            words = loader.loadDictionary(); // Load words from dictionary file
            trie = new Trie(words);         // Initialize Trie with the loaded words
            bkTree = new BKTree(words.get(0)); // Initialize BK-Tree with the first word as the root
            insertWordsIntoBKTree();         // Insert all words into the BK-Tree
            queryHistory = new QueryHistory(5); // Initialize the LRU cache for query history
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
        SearchResult cachedResult = queryHistory.getResult(prefix);
        if (cachedResult != null) {
            return cachedResult; // Return cached result if available
        }

        List<Word> prefixMatches = trie.startsWith(prefix);
        List<Word> suggestions = new ArrayList<>();

        if (prefixMatches.isEmpty()) {
            suggestions.addAll(bkTree.search(prefix, 2));
        }

        SearchResult result = new SearchResult(prefixMatches, suggestions);
        queryHistory.addQuery(prefix, result); // Cache the result
        return result;
    }

    /**
     * Retrieves the list of queries from the LRU cache.
     *
     * @return A list of recent queries.
     */
    public List<String> getQueryHistory() {
        return queryHistory.getHistory(); // Retrieve the query history from the LRU cache
    }

    public SearchResult getCachedResult(String query) {
        return queryHistory.getResult(query);
    }
}
