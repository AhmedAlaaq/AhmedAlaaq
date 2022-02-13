package com.assessment.ordina.core.WordFrequencyAnalyzer;

import com.assessment.ordina.core.WordFrequency.WordFrequency;

/**
 * Interface to be implemented by service class.
 * of WordFrequencyAnalyzerService
 */
public interface WordFrequencyAnalyzer {

    /**
     * should return the highest frequency in the text (several words might actually have this frequency)
     * @param text
     * @return int
     */
    int calculateHighestFrequency(String text);

    /**
     * should return the frequency of the specified word
     * @param text
     * @param word
     * @return int
     */
    int calculateFrequencyForWord (String text, String word);

    /**
     * should return a list of the most frequent „n‟ words in the input text,
     * all the words returned in lower case. If several words have the same frequency,
     * this method should return them in ascendant alphabetical order
     * (for input text “The sun shines over the lake” and n = 3, it should return the list {(“the”, 2), (“lake”, 1), (“over”, 1)
     * @param text
     * @param n
     * @return WordFrequency[]
     */
    WordFrequency[] calculateMostFrequentWords (String text, int n);
}
