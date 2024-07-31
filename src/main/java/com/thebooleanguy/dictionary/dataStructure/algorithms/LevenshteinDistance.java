package com.thebooleanguy.dictionary.dataStructure.algorithms;

/**
 * The Levenshtein Distance is a measure of the difference between two sequences
     * and is defined as the minimum number of single-character edits (insertions, deletions,
     * or substitutions) required to change one word into the other.
     */
public class LevenshteinDistance {

    /**
     * Computes the Levenshtein Distance between two strings.
     * @param s1 the first string
     * @param s2 the second string
     * @return the Levenshtein Distance between s1 and s2
     */
    public static int levenshteinDistance(String s1, String s2) {
        int lenS1 = s1.length(); // Length of the first string
        int lenS2 = s2.length(); // Length of the second string

        // Ensure lenS1 is always less than or equal to lenS2
        if (lenS1 > lenS2) {
            // Swap s1 and s2 if s1 is longer
            String temp = s1;
            s1 = s2;
            s2 = temp;
            lenS1 = s1.length();
            lenS2 = s2.length();
        }

        // Create a row for storing distances
        int[] currentRow = new int[lenS1 + 1];
        // Initialize the first row of distances (0 to lenS1)
        for (int i = 0; i <= lenS1; i++) {
            currentRow[i] = i;
        }

        // Iterate through each character of the second string
        for (int i = 1; i <= lenS2; i++) {
            // Save the previous row and create a new row for the current iteration
            int[] previousRow = currentRow;
            currentRow = new int[lenS1 + 1];
            currentRow[0] = i; // Distance for transforming an empty string to the prefix of s2

            // Iterate through each character of the first string
            for (int j = 1; j <= lenS1; j++) {
                // Calculate costs for insertion, deletion, and substitution
                int add = previousRow[j] + 1; // Cost of insertion
                int delete = currentRow[j - 1] + 1; // Cost of deletion
                int change = previousRow[j - 1]; // Cost of substitution
                if (s1.charAt(j - 1) != s2.charAt(i - 1)) {
                    change += 1; // Increment substitution cost if characters are different
                }
                // Minimum cost among insertion, deletion, and substitution
                currentRow[j] = Math.min(Math.min(add, delete), change);
            }
        }

        // The last element of the current row contains the Levenshtein Distance
        return currentRow[lenS1];
    }
}
