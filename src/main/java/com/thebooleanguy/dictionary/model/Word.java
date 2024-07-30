// Holds the word, part of speech, definition and an example sentence.

package com.thebooleanguy.dictionary.model;

public class Word {
    private String word;
    private String partOfSpeech;
    private String definition;
    private String sentence; // Example Sentence
    private int frequency; // How common the word is

    public Word(String word, String partOfSpeech, String definition, String sentence, int frequency) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.sentence = sentence;
        this.frequency = frequency;
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

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return word + " (" + partOfSpeech + "): " + definition + " Example: " + sentence + " Frequency: " + frequency;
    }
}

