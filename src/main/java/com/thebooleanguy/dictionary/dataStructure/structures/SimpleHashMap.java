package com.thebooleanguy.dictionary.dataStructure.structures;

import com.thebooleanguy.dictionary.model.SearchResult;
import java.util.LinkedList;

/**
 * A basic implementation of a HashMap.
 */
public class SimpleHashMap {
    private static final int INITIAL_CAPACITY = 16;
    private LinkedList<Entry>[] table;

    /**
     * Constructs a SimpleHashMap instance with the default capacity.
     */
    public SimpleHashMap() {
        this.table = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /**
     * Hash function to compute the index for a key.
     *
     * @param key The key to hash.
     * @return The index for the key.
     */
    private int hash(String key) {
        return Math.abs(key.hashCode() % table.length);
    }

    /**
     * Adds a key-value pair to the map.
     *
     * @param key   The key.
     * @param value The value.
     */
    public void put(String key, SearchResult value) {
        int index = hash(key);
        LinkedList<Entry> bucket = table[index];
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry(key, value));
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if not found.
     */
    public SearchResult get(String key) {
        int index = hash(key);
        LinkedList<Entry> bucket = table[index];
        for (Entry entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Removes the entry associated with the given key.
     *
     * @param key The key to remove.
     */
    public void remove(String key) {
        int index = hash(key);
        LinkedList<Entry> bucket = table[index];
        bucket.removeIf(entry -> entry.key.equals(key));
    }

    /**
     * Inner class representing an entry in the hash map.
     */
    private static class Entry {
        String key;
        SearchResult value;

        Entry(String key, SearchResult value) {
            this.key = key;
            this.value = value;
        }
    }
}
