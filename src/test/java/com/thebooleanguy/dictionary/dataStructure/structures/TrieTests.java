package com.thebooleanguy.dictionary.dataStructure.structures;

import com.thebooleanguy.dictionary.model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieTests {

    private Trie trie;
    private List<Word> wordList;

    @BeforeEach
    void setUp() {
        wordList = new ArrayList<>();
        wordList.add(new Word("hello", "noun", "A greeting", "Hello, world!", 10));
        wordList.add(new Word("hell", "noun", "A place", "Go to hell!", 8));
        wordList.add(new Word("help", "verb", "Assist", "Please help me.", 6));
        wordList.add(new Word("hero", "noun", "A brave person", "Be a hero.", 4));

        trie = new Trie(wordList);
    }

    @Test
    void testInsertAndSearch() {
        Word word = trie.search("hello");
        assertNotNull(word);
        assertEquals("hello", word.getWord());

        word = trie.search("hero");
        assertNotNull(word);
        assertEquals("hero", word.getWord());

        word = trie.search("unknown");
        assertNull(word);
    }

    @Test
    void testStartsWith() {
        List<Word> results = trie.startsWith("he");
        assertEquals(4, results.size());
        assertEquals("hello", results.get(0).getWord());
        assertEquals("hell", results.get(1).getWord());
        assertEquals("help", results.get(2).getWord());
        assertEquals("hero", results.get(3).getWord());

        results = trie.startsWith("hel");
        assertEquals(3, results.size());
        assertEquals("hello", results.get(0).getWord());
        assertEquals("hell", results.get(1).getWord());
        assertEquals("help", results.get(2).getWord());

        results = trie.startsWith("h");
        assertEquals(4, results.size());
        assertEquals("hello", results.get(0).getWord());
        assertEquals("hell", results.get(1).getWord());
        assertEquals("help", results.get(2).getWord());
        assertEquals("hero", results.get(3).getWord());

        results = trie.startsWith("her");
        assertEquals(1, results.size());
        assertEquals("hero", results.get(0).getWord());

        results = trie.startsWith("unknown");
        assertEquals(0, results.size());
    }
}
