package com.assessment.ordina.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.assessment.ordina.contoller.exception.InvalidTextException;
import com.assessment.ordina.core.WordFrequency.WordFrequency;
import com.assessment.ordina.core.WordFrequencyAnalyzer.WordFrequencyAnalyzer;

/**
 * Service class for WordFrequency results
 */
@Service
public class WordFrequencyService {

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    public WordFrequencyService(WordFrequencyAnalyzer wordFrequencyAnalyzer) {
        this.wordFrequencyAnalyzer = wordFrequencyAnalyzer;
    }

    public int calculateHighestFrequency(final String text) {

        if (textValidation(text).booleanValue()) {
            return wordFrequencyAnalyzer.calculateHighestFrequency(text);
        }
        throw new InvalidTextException("text should not contains special characters");
    }

    public int calculateFrequencyForWord(final String text, final String word) {
        if (textValidation(text).booleanValue()) {
            return wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
        }
        throw new InvalidTextException("text should not contains special characters");
    }

    public WordFrequency[] calculateMostFrequentWords(final String text, final int n) {
        if (textValidation(text).booleanValue()) {
            return wordFrequencyAnalyzer.calculateMostFrequentWords(text, n);
        }
        throw new InvalidTextException("text should not contains special characters");
    }

    /**
     * text validation if contains special characters or not
     *
     * @param text
     * @return
     */
    private Boolean textValidation(String text) {
        Pattern pattern = Pattern.compile("[a-zA-Z .,:?]*");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
