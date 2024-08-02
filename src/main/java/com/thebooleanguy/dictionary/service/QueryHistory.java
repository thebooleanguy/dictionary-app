package com.thebooleanguy.dictionary.service;

import com.thebooleanguy.dictionary.model.SearchResult;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

/**
 * An LRU Cache implementation for storing query history along with results.
 */
public class QueryHistory {
    private final LinkedHashMap<String, SearchResult> history; // Holds query history and results
    private final int maxSize; // Maximum size of the cache

    /**
     * Constructs an LRU QueryHistory instance with the specified maximum size.
     *
     * @param maxSize The maximum number of queries to retain.
     */
    public QueryHistory(int maxSize) {
        this.maxSize = maxSize;
        this.history = new LinkedHashMap<String, SearchResult>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, SearchResult> eldest) {
                return size() > maxSize; // Remove the oldest entry if the size exceeds maxSize
            }
        };
    }

    /**
     * Adds a query and its result to the history. If the cache is full, the least recently used query will be removed.
     *
     * @param query The query to add to the history.
     * @param result The result associated with the query.
     */
    public void addQuery(String query, SearchResult result) {
        history.put(query, result);
    }

    /**
     * Returns the history as a list of queries.
     *
     * @return A list of queries in the history.
     */
    public List<String> getHistory() {
        return new LinkedList<>(history.keySet());
    }

    /**
     * Retrieves the result for a given query from the history.
     *
     * @param query The query to look up.
     * @return The search result associated with the query, or null if not found.
     */
    public SearchResult getResult(String query) {
        return history.get(query);
    }
}
