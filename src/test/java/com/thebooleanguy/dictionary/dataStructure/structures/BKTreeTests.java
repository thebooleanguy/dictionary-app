package com.thebooleanguy.dictionary.dataStructure.structures;

import com.thebooleanguy.dictionary.model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BKTreeTests {

    private BKTree bkTree;

    @BeforeEach
    void setUp() {
        Word rootWord = new Word("hello", "noun", "A greeting", "Hello, world!", 10);
        bkTree = new BKTree(rootWord);
    }

    @Test
    void testAddAndSearch() {
        Word word1 = new Word("hell", "noun", "A place", "Go to hell!", 8);
        Word word2 = new Word("help", "verb", "Assist", "Please help me.", 6);
        Word word3 = new Word("hero", "noun", "A brave person", "Be a hero.", 4);

        bkTree.add(word1);
        bkTree.add(word2);
        bkTree.add(word3);

        List<Word> results = bkTree.search("hello", 1);
        assertEquals(2, results.size());
        assertTrue(results.contains(word1));
        assertTrue(results.contains(word2));

        results = bkTree.search("hero", 1);
        assertEquals(1, results.size());
        assertTrue(results.contains(word3));

        results = bkTree.search("hero", 2);
        assertEquals(2, results.size());
        assertTrue(results.contains(word2));
        assertTrue(results.contains(word3));
    }
}
