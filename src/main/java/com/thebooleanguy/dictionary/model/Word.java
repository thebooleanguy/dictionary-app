package com.thebooleanguy.dictionary.model;

public class Word {
    private String word;
    private String partOfSpeech;
    private String definition;
    private String sentence;

    public Word(String word, String partOfSpeech, String definition, String sentence) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.sentence = sentence;
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

    public String getSentence() {
        return sentence;
    }

    @Override
    // Concatenate the 4 Strings into one
    public String toString() {
        return word + " (" + partOfSpeech + "): " + definition + " Example: " + sentence;
    }
}
