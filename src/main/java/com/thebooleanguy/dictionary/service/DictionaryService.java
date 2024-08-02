// To be used by the Dictionary

package com.thebooleanguy.dictionary.service;

import com.thebooleanguy.dictionary.dataStructure.structures.BKTree;
import com.thebooleanguy.dictionary.dataStructure.structures.Trie;
import com.thebooleanguy.dictionary.model.SearchResult;
import com.thebooleanguy.dictionary.model.Word;
import com.thebooleanguy.dictionary.util.DictionaryFileLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictionaryService {

    private Trie trie;
    private BKTree bkTree;
    private List<Word> words;

    @PostConstruct
    public void init() {
        DictionaryFileLoader loader = new DictionaryFileLoader();
        try {
            words = loader.loadDictionary();
            trie = new Trie(words);
            bkTree = new BKTree(words.get(0));
            insertWordsIntoBKTree();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertWordsIntoBKTree() {
        for (Word word : words) {
            bkTree.add(word);
        }
    }

    public SearchResult searchWords(String prefix) {
        List<Word> prefixMatches = trie.startsWith(prefix);
        List<Word> suggestions = new ArrayList<>();

        if (prefixMatches.isEmpty()) {
            suggestions.addAll(bkTree.search(prefix, 2)); // Use BKTree to find similar words
        }

        return new SearchResult(prefixMatches, suggestions);
    }
}


