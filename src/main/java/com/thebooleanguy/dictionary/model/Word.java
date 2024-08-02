package com.thebooleanguy.dictionary.model;

/**
 * Represents a word in the dictionary with its part of speech, definition, example sentence, and frequency.
 */
public class Word {
    private String word;            // The actual word
    private String partOfSpeech;    // The part of speech (e.g., noun, verb)
    private String definition;      // Definition of the word
    private String sentence;        // Example sentence using the word
    private int frequency;          // Frequency of the word usage

    /**
     * Constructs a Word instance with the specified attributes.
     *
     * @param word The actual word.
     * @param partOfSpeech The part of speech of the word.
     * @param definition The definition of the word.
     * @param sentence An example sentence using the word.
     * @param frequency The frequency of the word's usage.
     */
    public Word(String word, String partOfSpeech, String definition, String sentence, int frequency) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
        this.sentence = sentence;
        this.frequency = frequency;
    }

    /**
     * Returns the word.
     *
     * @return The actual word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the part of speech of the word.
     *
     * @return The part of speech.
     */
    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    /**
     * Returns the definition of the word.
     *
     * @return The definition.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Returns the example sentence using the word.
     *
     * @return The example sentence.
     */
    public String getSentence() {
        return sentence;
    }

    /**
     * Returns the frequency of the word's usage.
     *
     * @return The frequency.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Returns a string representation of the word, including its part of speech, definition,
     * example sentence, and frequency.
     *
     * @return A string representation of the word.
     */
    @Override
    public String toString() {
        return word + " (" + partOfSpeech + "): " + definition + " Example: " + sentence + " Frequency: " + frequency;
    }
}
