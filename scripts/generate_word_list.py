import os
import nltk
from nltk.corpus import wordnet, brown
from collections import Counter

# Ensure NLTK resources are downloaded
nltk.download('wordnet')
nltk.download('omw')
nltk.download('brown')

# Define a set of very common words
VERY_COMMON_WORDS = set([
    'a', 'an', 'the', 'and', 'of', 'in', 'to', 'is', 'it', 'that',
    'with', 'for', 'on', 'as', 'at'
])

# Create a mapping for part of speech abbreviations
pos_mapping = {
    'n': 'noun',
    'v': 'verb',
    'a': 'adjective',
    's': 'adjective satellite',
    'r': 'adverb'
}

# Generate a frequency distribution from the Brown corpus
brown_words = brown.words()
word_freq = Counter(word.lower() for word in brown_words)

# Create a set of common words based on frequency
common_words = {word for word, freq in word_freq.items() if freq > 5}  # Adjust frequency threshold as needed

def ensure_period(text):
    return text if text.endswith('.') else text + '.'

def generate_dataset():
    data = []
    seen_words = set()  # Keep track of (word, pos) pairs to avoid duplicates

    for synset in wordnet.all_synsets():
        for lemma in synset.lemmas():
            word = lemma.name().lower()
            pos = pos_mapping.get(synset.pos(), synset.pos())
            if word.isalpha() and (word, pos) not in seen_words:
                # Check if the word is common
                if word in common_words:
                    definition = ensure_period(synset.definition())
                    score = word_freq[word]  # Using frequency as score

                    # De-prioritize very common words
                    if word in VERY_COMMON_WORDS:
                        score = 1  # Lower score for very common words

                    # Use WordNet examples if available
                    example_sentences = synset.examples()
                    if example_sentences:
                        example_sentence = ensure_period(example_sentences[0])
                    else:
                        # Generic example sentence if no WordNet examples are available
                        example_sentence = ensure_period(f"This is a sample sentence using the word {word}.")

                    data.append("{} | {} | {} | {} | {}".format(word, pos, definition, example_sentence, score))
                    seen_words.add((word, pos))

    # Sort by score (frequency) and limit to the most common 13,000 words
    data = sorted(data, key=lambda x: int(x.split(" | ")[-1]), reverse=True)[:13000]

    # Save to file
    output_dir = '../src/main/resources'
    os.makedirs(output_dir, exist_ok=True)

    output_file = os.path.join(output_dir, 'dictionary.txt')
    with open(output_file, 'w') as file:
        file.write("\n".join(data))

generate_dataset()