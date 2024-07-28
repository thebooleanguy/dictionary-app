// Holds the word, part of speech, and definition.

package com.thebooleanguy.dictionary.model;

public class WordInfo {
    private String word;
    private String partOfSpeech;
    private String definition;

    public WordInfo(String word, String partOfSpeech, String definition) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    @Override
    // Concatenates these 3 strings and return as one
    public String toString() {
        return word + " (" + partOfSpeech + "): " + definition;
    }
}
