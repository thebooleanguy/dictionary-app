// A Trie data structure for efficient retrieval of words with autocomplete

package com.thebooleanguy.dictionary.dataStructure;

import java.util.List;
import com.thebooleanguy.dictionary.model.Word;

public class Trie {

    TrieNode root;
    List<Word> wordList; // External list to store Word objects

    public Trie(List<Word> wordList) {
        root = new TrieNode('\0');
        this.wordList = wordList;
    }

    // Inner class representing a node in the Trie
    class TrieNode {

        private char value;
        private boolean isWord;
        private TrieNode[] children;
        private int wordIndex; // To store the index of the Word object in the external list

        public TrieNode(char val) {
            this.value = val;
            this.isWord = false;
            this.children = new TrieNode[26];
            this.wordIndex = -1; // Initialize with -1 to indicate no word
        }
    }

    // Inserts a word into the Trie
    public void insert(int wordIndex) {
        Word word = wordList.get(wordIndex);
        TrieNode curr = root;
        for (char x : word.getWord().toCharArray()) {
            if (curr.children[x - 'a'] == null) {
                curr.children[x - 'a'] = new TrieNode(x);
            }
            curr = curr.children[x - 'a'];
        }
        curr.isWord = true;
        curr.wordIndex = wordIndex; // Store the index of the word in the node
    }

    // Searches for a word in the Trie and returns the Word object if found
    public Word search(String word) {
        TrieNode res = getLast(word);
        return (res != null && res.isWord) ? wordList.get(res.wordIndex) : null;
    }

    // Helper method to traverse the Trie
    private TrieNode getLast(String word) {
        TrieNode curr = root;
        for (char x : word.toCharArray()) {
            if (curr.children[x - 'a'] == null) {
                return null;
            }
            curr = curr.children[x - 'a'];
        }
        return curr;
    }

    // Checks if there is any word in the Trie that starts with the given prefix
    public List<Word> startsWith(String prefix) {
        TrieNode res = getLast(prefix);
        List<Word> words = new java.util.ArrayList<>();
        if (res != null) {
            collectAllWords(res, words);
        }
        return words;
    }

    // Helper method to collect all words under a given Trie node
    private void collectAllWords(TrieNode node, List<Word> words) {
        if (node == null) return;

        if (node.isWord) {
            words.add(wordList.get(node.wordIndex));
        }

        for (TrieNode child : node.children) {
            collectAllWords(child, words);
        }
    }
}
