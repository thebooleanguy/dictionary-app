package com.thebooleanguy.dictionary.dataStructure.structures;

import com.thebooleanguy.dictionary.model.SearchResult;
import com.thebooleanguy.dictionary.model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SimpleHashMapTests {

    private SimpleHashMap map;

    @BeforeEach
    void setUp() {
        map = new SimpleHashMap();
    }

    @Test
    void testPutAndGet() {
        // Create Word instances
        Word word1 = new Word("word1", "noun", "definition1", "example1", 1);
        Word word2 = new Word("word2", "verb", "definition2", "example2", 2);
        Word word3 = new Word("word3", "adjective", "definition3", "example3", 3);

        // Create SearchResult instances
        SearchResult result1 = new SearchResult(Collections.singletonList(word1), Collections.emptyList());
        SearchResult result2 = new SearchResult(Collections.singletonList(word2), Collections.emptyList());
        SearchResult result3 = new SearchResult(Collections.singletonList(word3), Collections.emptyList());

        map.put("key1", result1);
        map.put("key2", result2);
        map.put("key3", result3);

        assertEquals(result1, map.get("key1"));
        assertEquals(result2, map.get("key2"));
        assertEquals(result3, map.get("key3"));
        assertNull(map.get("key4"));
    }

    @Test
    void testRemove() {
        // Create Word instances
        Word word1 = new Word("word1", "noun", "definition1", "example1", 1);
        Word word2 = new Word("word2", "verb", "definition2", "example2", 2);
        Word word3 = new Word("word3", "adjective", "definition3", "example3", 3);

        // Create SearchResult instances
        SearchResult result1 = new SearchResult(Collections.singletonList(word1), Collections.emptyList());
        SearchResult result2 = new SearchResult(Collections.singletonList(word2), Collections.emptyList());
        SearchResult result3 = new SearchResult(Collections.singletonList(word3), Collections.emptyList());

        map.put("key1", result1);
        map.put("key2", result2);
        map.put("key3", result3);

        map.remove("key2");
        assertNull(map.get("key2"));
        assertEquals(result1, map.get("key1"));
        assertEquals(result3, map.get("key3"));

        map.remove("key1");
        assertNull(map.get("key1"));
        assertEquals(result3, map.get("key3"));

        map.remove("key3");
        assertNull(map.get("key3"));
    }
}
