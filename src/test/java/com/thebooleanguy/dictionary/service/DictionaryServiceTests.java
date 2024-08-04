package com.thebooleanguy.dictionary.service;

import com.thebooleanguy.dictionary.dataStructure.structures.BKTree;
import com.thebooleanguy.dictionary.dataStructure.structures.LRUCache;
import com.thebooleanguy.dictionary.dataStructure.structures.Trie;
import com.thebooleanguy.dictionary.model.SearchResult;
import com.thebooleanguy.dictionary.model.Word;
import com.thebooleanguy.dictionary.util.DictionaryFileLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DictionaryServiceTests {

    @Mock
    private DictionaryFileLoader loader;

    @Mock
    private Trie trie;

    @Mock
    private BKTree bkTree;

    @Mock
    private LRUCache queryHistory;

    @InjectMocks
    private DictionaryService dictionaryService;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        // Mock the loader to return a list of words
        List<Word> words = new ArrayList<>();
        words.add(new Word("example", "noun", "A sample word.", "This is an example sentence.", 10));
        when(loader.loadDictionary()).thenReturn(words);

        // Mock the Trie and BKTree initialization
        when(trie.startsWith(anyString())).thenReturn(new ArrayList<>());
        when(bkTree.search(anyString(), anyInt())).thenReturn(new ArrayList<>());

        // Mock the LRUCache behavior
        when(queryHistory.getResult(anyString())).thenReturn(null);
    }

    @Test
    void testSearchWords_CacheHit() {
        // Mock a cached result
        List<Word> exactMatches = new ArrayList<>();
        List<Word> suggestions = new ArrayList<>();
        SearchResult cachedResult = new SearchResult(exactMatches, suggestions);
        when(queryHistory.getResult("test")).thenReturn(cachedResult);

        // Call the service method
        SearchResult result = dictionaryService.searchWords("test");

        // Verify that the cache was used
        assertEquals(cachedResult, result);
        verify(queryHistory, never()).addQuery(anyString(), any(SearchResult.class));
    }

    @Test
    void testSearchWords_NoCache() {
        // Set up mocks for no cache hit
        List<Word> exactMatches = new ArrayList<>();
        List<Word> suggestions = new ArrayList<>();
        when(trie.startsWith(anyString())).thenReturn(exactMatches);
        when(bkTree.search(anyString(), anyInt())).thenReturn(suggestions);

        // Call the service method
        SearchResult result = dictionaryService.searchWords("test");

        // Verify that the result is created and added to cache
        assertNotNull(result);
        verify(queryHistory).addQuery(anyString(), any(SearchResult.class));
    }

    @Test
    void testGetQueryHistory() {
        // Mock query history
        List<String> history = List.of("test1", "test2");
        when(queryHistory.getHistory()).thenReturn(history);

        // Call the service method
        List<String> result = dictionaryService.getQueryHistory();

        // Verify the result
        assertEquals(history, result);
    }

    @Test
    void testGetCachedResult() {
        // Mock a cached result
        List<Word> exactMatches = new ArrayList<>();
        List<Word> suggestions = new ArrayList<>();
        SearchResult cachedResult = new SearchResult(exactMatches, suggestions);
        when(queryHistory.getResult("test")).thenReturn(cachedResult);

        // Call the service method
        SearchResult result = dictionaryService.getCachedResult("test");

        // Verify the result
        assertEquals(cachedResult, result);
    }
}
