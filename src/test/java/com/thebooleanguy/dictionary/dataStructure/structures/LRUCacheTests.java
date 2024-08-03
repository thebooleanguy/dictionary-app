package com.thebooleanguy.dictionary.dataStructure.structures;

import com.thebooleanguy.dictionary.model.SearchResult;
import com.thebooleanguy.dictionary.model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTests {

    private LRUCache cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache(3); // Set max size of cache to 3 for testing
    }

    @Test
    void testAddQueryAndEviction() {
        // Create Word instances
        Word word1 = new Word("word1", "noun", "definition1", "example1", 1);
        Word word2 = new Word("word2", "verb", "definition2", "example2", 2);
        Word word3 = new Word("word3", "adjective", "definition3", "example3", 3);
        Word word4 = new Word("word4", "noun", "definition4", "example4", 4);

        // Create SearchResult instances
        SearchResult result1 = new SearchResult(Collections.singletonList(word1), Collections.emptyList());
        SearchResult result2 = new SearchResult(Collections.singletonList(word2), Collections.emptyList());
        SearchResult result3 = new SearchResult(Collections.singletonList(word3), Collections.emptyList());
        SearchResult result4 = new SearchResult(Collections.singletonList(word4), Collections.emptyList());

        cache.addQuery("query1", result1);
        cache.addQuery("query2", result2);
        cache.addQuery("query3", result3);

        List<String> history = cache.getHistory();
        assertEquals(3, history.size());
        assertEquals("query3", history.get(0));
        assertEquals("query2", history.get(1));
        assertEquals("query1", history.get(2));

        cache.addQuery("query4", result4);
        history = cache.getHistory();
        assertEquals(3, history.size());
        assertEquals("query4", history.get(0));
        assertEquals("query3", history.get(1));
        assertEquals("query2", history.get(2));
        assertNull(cache.getResult("query1")); // query1 should be evicted
    }

    @Test
    void testRetrieveQuery() {
        // Create Word instances
        Word word1 = new Word("word1", "noun", "definition1", "example1", 1);
        Word word2 = new Word("word2", "verb", "definition2", "example2", 2);
        Word word3 = new Word("word3", "adjective", "definition3", "example3", 3);

        // Create SearchResult instances
        SearchResult result1 = new SearchResult(Collections.singletonList(word1), Collections.emptyList());
        SearchResult result2 = new SearchResult(Collections.singletonList(word2), Collections.emptyList());
        SearchResult result3 = new SearchResult(Collections.singletonList(word3), Collections.emptyList());

        cache.addQuery("query1", result1);
        cache.addQuery("query2", result2);
        cache.addQuery("query3", result3);

        assertEquals(result2, cache.getResult("query2"));

        List<String> history = cache.getHistory();
        assertEquals(3, history.size());
        assertEquals("query2", history.get(0));
        assertEquals("query3", history.get(1));
        assertEquals("query1", history.get(2));
    }

    @Test
    void testGetHistory() {
        // Create Word instances
        Word word1 = new Word("word1", "noun", "definition1", "example1", 1);
        Word word2 = new Word("word2", "verb", "definition2", "example2", 2);
        Word word3 = new Word("word3", "adjective", "definition3", "example3", 3);

        // Create SearchResult instances
        SearchResult result1 = new SearchResult(Collections.singletonList(word1), Collections.emptyList());
        SearchResult result2 = new SearchResult(Collections.singletonList(word2), Collections.emptyList());
        SearchResult result3 = new SearchResult(Collections.singletonList(word3), Collections.emptyList());

        cache.addQuery("query1", result1);
        cache.addQuery("query2", result2);
        cache.addQuery("query3", result3);

        List<String> history = cache.getHistory();
        assertEquals(3, history.size());
        assertEquals("query3", history.get(0));
        assertEquals("query2", history.get(1));
        assertEquals("query1", history.get(2));
    }
}
