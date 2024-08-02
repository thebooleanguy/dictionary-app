package com.thebooleanguy.dictionary.model;

import java.util.List;

/**
 * A wrapper class that holds the results of a search operation.
 * It includes a list of exact matches and a list of suggestions.
 */
public class SearchResult {
    private final List<Word> exactMatches; // List of words that exactly match the search query
    private final List<Word> suggestions;   // List of words suggested based on the search query

    /**
     * Constructs a SearchResult with the specified exact matches and suggestions.
     *
     * @param exactMatches A list of words that exactly match the search query.
     * @param suggestions A list of words suggested based on the search query.
     */
    public SearchResult(List<Word> exactMatches, List<Word> suggestions) {
        this.exactMatches = exactMatches;
        this.suggestions = suggestions;
    }

    /**
     * Returns the list of exact matches for the search query.
     *
     * @return A list of exact matches.
     */
    public List<Word> getExactMatches() {
        return exactMatches;
    }

    /**
     * Returns the list of suggestions based on the search query.
     *
     * @return A list of suggested words.
     */
    public List<Word> getSuggestions() {
        return suggestions;
    }
}
