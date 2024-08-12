package com.thebooleanguy.dictionary.dataStructure.structures;

import com.thebooleanguy.dictionary.model.SearchResult;

/**
 * A basic implementation of a HashMap using SimpleLinkedList.
 */
public class SimpleHashMap {
    private static final int INITIAL_CAPACITY = 16;
    private SimpleLinkedList<Entry>[] table;

    /**
     * Constructs a SimpleHashMap instance with the default capacity.
     */
    public SimpleHashMap() {
        this.table = new SimpleLinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new SimpleLinkedList<>();
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
        // Calculate the index in the hash table using a hash function
        int index = hash(key);

        // Retrieve the bucket (a linked list) at the calculated index
        SimpleLinkedList<Entry> bucket = table[index];

        // Traverse through the nodes in the linked list (bucket)
        for (SimpleLinkedList.Node<Entry> node = bucket.getHead(); node != null; node = node.next) {
            // If the key already exists, update the associated value
            if (node.data.key.equals(key)) {
                node.data.value = value;
                return; // Exit the method as the value has been updated
            }
        }

        // If the key does not exist in the bucket, add a new entry at the beginning of the list
        bucket.addFirst(new Entry(key, value));
    }


    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The key to look up.
     * @return The value associated with the key, or null if not found.
     */
    public SearchResult get(String key) {
        int index = hash(key);
        SimpleLinkedList<Entry> bucket = table[index];
        for (SimpleLinkedList.Node<Entry> node = bucket.getHead(); node != null; node = node.next) {
            if (node.data.key.equals(key)) {
                return node.data.value;
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
        SimpleLinkedList<Entry> bucket = table[index];
        for (SimpleLinkedList.Node<Entry> node = bucket.getHead(); node != null; node = node.next) {
            if (node.data.key.equals(key)) {
                bucket.remove(node.data);
                return;
            }
        }
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
