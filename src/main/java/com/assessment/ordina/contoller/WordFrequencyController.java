package com.assessment.ordina.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.assessment.ordina.core.WordFrequency.WordFrequency;
import com.assessment.ordina.service.WordFrequencyService;

@RestController
public class WordFrequencyController {

    private final WordFrequencyService wordFrequencyService;

    @Autowired
    public WordFrequencyController(final WordFrequencyService wordFrequencyService) {
        this.wordFrequencyService = wordFrequencyService;
    }

    @GetMapping("/HighestFrequency")
    @ResponseBody
    public int calculateHighestFrequency(@RequestBody final String text) {

        return wordFrequencyService.calculateHighestFrequency(text);
    }

    @GetMapping("/FrequencyForWord/{word}")
    @ResponseBody
    public int calculateFrequencyForWord(@RequestBody final String text, @PathVariable final String word) {

        return wordFrequencyService.calculateFrequencyForWord(text, word);
    }

    @GetMapping("/MostFrequentWords/{n}")
    @ResponseBody
    public WordFrequency[] calculateMostFrequentWords(@RequestBody final String text, @PathVariable final int n) {

        return wordFrequencyService.calculateMostFrequentWords(text, n);
    }

}
