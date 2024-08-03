package com.thebooleanguy.dictionary.dataStructure.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevenshteinDistanceTests {

    @Test
    void testIdenticalStrings() {
        assertEquals(0, LevenshteinDistance.levenshteinDistance("test", "test"));
    }

    @Test
    void testCompletelyDifferentStrings() {
        assertEquals(4, LevenshteinDistance.levenshteinDistance("test", "abcd"));
    }

    @Test
    void testOneCharacterDifference() {
        assertEquals(1, LevenshteinDistance.levenshteinDistance("test", "tests"));
        assertEquals(1, LevenshteinDistance.levenshteinDistance("test", "tost"));
    }

    @Test
    void testInsertionAndDeletion() {
        assertEquals(1, LevenshteinDistance.levenshteinDistance("cat", "cats"));
        assertEquals(1, LevenshteinDistance.levenshteinDistance("cats", "cat"));
    }

    @Test
    void testComplexCases() {
        assertEquals(3, LevenshteinDistance.levenshteinDistance("kitten", "sitting"));
        assertEquals(2, LevenshteinDistance.levenshteinDistance("flaw", "lawn"));
    }

    @Test
    void testEmptyStrings() {
        assertEquals(4, LevenshteinDistance.levenshteinDistance("", "test"));
        assertEquals(4, LevenshteinDistance.levenshteinDistance("test", ""));
        assertEquals(0, LevenshteinDistance.levenshteinDistance("", ""));
    }
}
