package com.thebooleanguy.dictionary.dataStructure.structures;

import com.thebooleanguy.dictionary.model.SearchResult;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

/**
 * An LRU Cache implementation for storing query history along with results.
 */
public class LRUCache {
    private final Map<String, SearchResult> cache; // Holds query history and results
    private final LinkedList<String> usageOrder; // Maintains usage order of queries
    private final int maxSize; // Maximum size of the cache

    /**
     * Constructs an LRUCache instance with the specified maximum size.
     *
     * @param maxSize The maximum number of queries to retain.
     */
    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new HashMap<>();
        this.usageOrder = new LinkedList<>();
    }

    /**
     * Adds a query and its result to the cache. If the cache is full, the least recently used query will be removed.
     *
     * @param query  The query to add to the cache.
     * @param result The result associated with the query.
     */
    public void addQuery(String query, SearchResult result) {
        if (cache.containsKey(query)) {
            usageOrder.remove(query);
        } else if (cache.size() >= maxSize) {
            String leastUsed = usageOrder.removeLast();
            cache.remove(leastUsed);
        }
        usageOrder.addFirst(query);
        cache.put(query, result);
    }

    /**
     * Returns the history as a list of queries in the order of most recently used to least recently used.
     *
     * @return A list of queries in the cache.
     */
    public List<String> getHistory() {
        return new LinkedList<>(usageOrder);
    }

    /**
     * Retrieves the result for a given query from the cache.
     * Moves the query to the front to mark it as recently used.
     *
     * @param query The query to look up.
     * @return The search result associated with the query, or null if not found.
     */
    public SearchResult getResult(String query) {
        if (!cache.containsKey(query)) {
            return null;
        }
        usageOrder.remove(query);
        usageOrder.addFirst(query);
        return cache.get(query);
    }
}
