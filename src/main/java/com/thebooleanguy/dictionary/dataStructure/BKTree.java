package com.thebooleanguy.dictionary.dataStructure;

import com.thebooleanguy.dictionary.service.LevenshteinDistance;
import com.thebooleanguy.dictionary.model.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * BKTree is a data structure for efficiently searching approximate matches
 * using the Levenshtein distance (edit distance) metric.
 */
public class BKTree {
    private final Node root;

    /**
     * Initializes the BKTree with a root node containing the provided word.
     *
     * @param rootWord The word to be used as the root of the tree.
     */
    public BKTree(Word rootWord) {
        this.root = new Node(rootWord);
    }

    /**
     * Represents a node in the BKTree.
     */
    private static class Node {
        Word word;
        Map<Integer, Node> children;

        /**
         * Creates a new node with the specified word.
         *
         * @param word The word associated with this node.
         */
        Node(Word word) {
            this.word = word;
            this.children = new HashMap<>();
        }
    }

    /**
     * Adds a new word to the BKTree.
     *
     * @param word The word to be added.
     */
    public void add(Word word) {
        Node currentNode = root;

        while (true) {
            // Calculate the Levenshtein distance between the new word and the current node's word
            int distance = LevenshteinDistance.levenshteinDistance(word.getWord(), currentNode.word.getWord());

            if (currentNode.children.containsKey(distance)) {
                // Move to the child node if it exists
                currentNode = currentNode.children.get(distance);
            } else {
                // Add a new child node if it doesn't exist
                currentNode.children.put(distance, new Node(word));
                break;
            }
        }
    }

    /**
     * Searches for words within a specified Levenshtein distance from the given word.
     *
     * @param word        The word to search for.
     * @param maxDistance The maximum Levenshtein distance for matches.
     * @return A list of words within the specified distance, sorted by frequency in descending order.
     */
    public List<Word> search(String word, int maxDistance) {
        List<Word> results = new ArrayList<>();
        search(root, word, maxDistance, results);

        // !TODO Implement sorting by hand
        // Sort results by frequency in descending order
        return results.stream()
                .sorted((w1, w2) -> Integer.compare(w2.getFrequency(), w1.getFrequency()))
                .collect(Collectors.toList());
    }

    /**
     * Recursive helper method to perform the search in the BKTree.
     *
     * @param node        The current node to process.
     * @param word        The word to search for.
     * @param maxDistance The maximum Levenshtein distance for matches.
     * @param results     The list to collect matching words.
     */
    private void search(Node node, String word, int maxDistance, List<Word> results) {
        // Calculate the distance from the current node's word to the search word
        int distance = LevenshteinDistance.levenshteinDistance(word, node.word.getWord());

        // If the distance is within the allowed range, add the word to results
        if (distance <= maxDistance) {
            results.add(node.word);
        }

        // Define the range of distances to check for children nodes
        int start = Math.max(0, distance - maxDistance);
        int end = distance + maxDistance;

        // Search child nodes within the distance range
        for (int i = start; i <= end; i++) {
            Node child = node.children.get(i);
            if (child != null) {
                search(child, word, maxDistance, results);
            }
        }
    }
}
