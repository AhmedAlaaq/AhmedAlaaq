package com.assessment.ordina.core.WordFrequencyAnalyzer;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Component;
import com.assessment.ordina.core.WordFrequency.WordFrequency;
import com.assessment.ordina.core.WordFrequency.WordFrequencyImp;

@Component
public class WordFrequencyAnalyzerImp implements WordFrequencyAnalyzer {

    public int calculateHighestFrequency(String text) {

        Map<String, Integer> wordsFrequency = spiltText(text);

        /** Using Collections.max() method returning highest
         ** frequency in HashMap and storing in a integer
         ** variable **/
        return Collections.max(wordsFrequency.values());
    }

    public int calculateFrequencyForWord(String text, String word) {
        int index, count = 0;
        /**
         * i+word.length() is used to reduce comparisons
         */
        for (int i = 0; i + word.length() <= text.length(); i++) {
            index = text.toLowerCase().indexOf(word.toLowerCase(), i);
            if (index >= 0) {
                count++;
                i = index;
                index = -1;
            }
        }
        return count;
    }

    public WordFrequency[] calculateMostFrequentWords(String text, int n) {
        WordFrequency[] mostFrequentWords = new WordFrequency[n];

        /** Covert Map to array **/
        Map<String, Integer> values = spiltText(text);
        WordFrequency[] wordFrequencies = new WordFrequency[values.size()];
        int j = 0;
        for (Map.Entry<String, Integer> wordFrequency : values.entrySet()) {
            wordFrequencies[j] = new WordFrequencyImp(wordFrequency.getKey(), wordFrequency.getValue());
            j++;
        }
        /** full array with most Frequent Words */
        int index = 1;
        for (int i = 0; i < n; i++) {
            mostFrequentWords[i] = new WordFrequencyImp(wordFrequencies[wordFrequencies.length - index].getWord(),
                    wordFrequencies[wordFrequencies.length - index].getFrequency());
            index++;
        }
        return mostFrequentWords;
    }

    private Map spiltText(String text) {
        Map<String, Integer> wordsFrequency = new TreeMap<>();

        /** Splitting to find the word **/
        String words[] = text.toLowerCase().split("[\\s.,]+");

        /** Loop to iterate over the words **/
        for (int i = 0; i < words.length; i++) {
            /** Condition to check if the
             ** array element is present
             ** the hash-map **/
            if (wordsFrequency.containsKey(words[i])) {
                wordsFrequency.put(words[i], wordsFrequency.get(words[i]) + 1);
            } else {
                wordsFrequency.put(words[i], 1);
            }
        }
        return wordsFrequency;
    }
}
