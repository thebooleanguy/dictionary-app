package com.thebooleanguy.dictionary.dataStructure;

import java.util.List;
import com.thebooleanguy.dictionary.model.Word;

/**
 * Trie data structure for efficient retrieval of words with autocomplete functionality.
 */
public class Trie {

    private final TrieNode root;
    private final List<Word> wordList; // External list to store Word objects

    /**
     * Initializes the Trie with a list of words and inserts all words into the Trie.
     *
     * @param wordList The list of words to be inserted into the Trie.
     */
    public Trie(List<Word> wordList) {
        this.root = new TrieNode('\0'); // Initialize root with a null character
        this.wordList = wordList;
        // Insert all words into the Trie during initialization
        for (int i = 0; i < wordList.size(); i++) {
            insert(i);
        }
    }

    /**
     * Represents a node in the Trie.
     */
    private class TrieNode {

        private final char value;
        private boolean isWord; // Indicates if the node marks the end of a word
        private final TrieNode[] children;
        private int wordIndex; // Index of the Word object in the external list

        /**
         * Creates a new TrieNode with the specified character.
         *
         * @param val The character value of the node.
         */
        public TrieNode(char val) {
            this.value = val;
            this.isWord = false;
            this.children = new TrieNode[26]; // 26 letters in the English alphabet
            this.wordIndex = -1; // Initialize with -1 to indicate no word
        }
    }

    /**
     * Inserts a word into the Trie based on its index in the external word list.
     *
     * @param wordIndex The index of the word in the external list to be inserted.
     */
    public void insert(int wordIndex) {
        Word word = wordList.get(wordIndex);
        TrieNode curr = root;

        // Traverse the Trie, creating nodes as needed
        for (char x : word.getWord().toCharArray()) {
            if (curr.children[x - 'a'] == null) {
                curr.children[x - 'a'] = new TrieNode(x);
            }
            curr = curr.children[x - 'a'];
        }
        curr.isWord = true;
        curr.wordIndex = wordIndex; // Store the index of the word in the node
    }

    /**
     * Searches for a word in the Trie and returns the Word object if found.
     *
     * @param word The word to search for.
     * @return The Word object if found, otherwise null.
     */
    public Word search(String word) {
        TrieNode res = getLast(word);
        return (res != null && res.isWord) ? wordList.get(res.wordIndex) : null;
    }

    /**
     * Helper method to find the node corresponding to the last character of the given word.
     *
     * @param word The word to traverse the Trie.
     * @return The TrieNode corresponding to the last character of the word, or null if not found.
     */
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

    /**
     * Checks if there is any word in the Trie that starts with the given prefix.
     *
     * @param prefix The prefix to search for.
     * @return A list of words that start with the given prefix, sorted by frequency in descending order.
     */
    public List<Word> startsWith(String prefix) {
        TrieNode res = getLast(prefix);
        List<Word> words = new java.util.ArrayList<>();
        if (res != null) {
            collectAllWords(res, words);
        }

        // !TODO Implement sorting by hand
        // Sort words by frequency in descending order
        words.sort((w1, w2) -> Integer.compare(w2.getFrequency(), w1.getFrequency()));
        return words;
    }

    /**
     * Helper method to collect all words under a given Trie node.
     *
     * @param node  The TrieNode to start collecting words from.
     * @param words The list to store collected words.
     */
    private void collectAllWords(TrieNode node, List<Word> words) {
        if (node == null) return;

        // Add the word if the node marks the end of a word
        if (node.isWord) {
            words.add(wordList.get(node.wordIndex));
        }

        // Recursively collect words from child nodes
        for (TrieNode child : node.children) {
            collectAllWords(child, words);
        }
    }
}
