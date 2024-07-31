// To be used by the Dictionary

package com.thebooleanguy.dictionary.service;

import com.thebooleanguy.dictionary.dataStructure.BKTree;
import com.thebooleanguy.dictionary.dataStructure.Trie;
import com.thebooleanguy.dictionary.model.Word;
import com.thebooleanguy.dictionary.util.DictionaryFileLoader;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
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

    public List<Word> searchWords(String prefix) {
        List<Word> results = trie.startsWith(prefix);
        if (results.isEmpty()) {
            results = bkTree.search(prefix, 2);
        }
        return results;
    }
}
