package com.thebooleanguy.dictionary.dataStructure.algorithms;

import com.thebooleanguy.dictionary.model.Word;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortTests {

    @Test
    void testQuickSort() {
        // Create a list of Word objects with frequencies
        List<Word> words = new ArrayList<>();
        words.add(new Word("apple", "noun", "A fruit", "I ate an apple.", 5));
        words.add(new Word("banana", "noun", "A fruit", "I ate a banana.", 2));
        words.add(new Word("cherry", "noun", "A fruit", "I ate a cherry.", 7));
        words.add(new Word("date", "noun", "A fruit", "I ate a date.", 1));

        // Sort the list in descending order of frequency
        QuickSort.quickSort(words, false);

        // Verify the order
        assertEquals(7, words.get(0).getFrequency());
        assertEquals(5, words.get(1).getFrequency());
        assertEquals(2, words.get(2).getFrequency());
        assertEquals(1, words.get(3).getFrequency());
    }

    @Test
    void testQuickSortSkipFirstElement() {
        // Create a list of Word objects with frequencies
        List<Word> words = new ArrayList<>();
        words.add(new Word("apple", "noun", "A fruit", "I ate an apple.", 5));
        words.add(new Word("banana", "noun", "A fruit", "I ate a banana.", 2));
        words.add(new Word("cherry", "noun", "A fruit", "I ate a cherry.", 7));
        words.add(new Word("date", "noun", "A fruit", "I ate a date.", 1));

        // Sort the list in descending order of frequency, skipping the first element
        QuickSort.quickSort(words, true);

        // Verify the order
        assertEquals(5, words.get(0).getFrequency()); // First element remains unchanged
        assertEquals(7, words.get(1).getFrequency());
        assertEquals(2, words.get(2).getFrequency());
        assertEquals(1, words.get(3).getFrequency());
    }
}
