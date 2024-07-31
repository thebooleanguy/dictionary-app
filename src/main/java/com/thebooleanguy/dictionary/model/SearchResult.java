// A wrapper class that holds the list of words along with the source where it came from

package com.thebooleanguy.dictionary.model;

import java.util.List;

public class SearchResult {
    private final List<Word> exactMatches;
    private final List<Word> suggestions;

    public SearchResult(List<Word> exactMatches, List<Word> suggestions) {
        this.exactMatches = exactMatches;
        this.suggestions = suggestions;
    }

    public List<Word> getExactMatches() {
        return exactMatches;
    }

    public List<Word> getSuggestions() {
        return suggestions;
    }
}

