package com.assessment.ordina.core.WordFrequency;

public class WordFrequencyImp implements WordFrequency{
    private final String word;
    private final int frequncy;

    public WordFrequencyImp(String word, int frequncy) {
        this.word = word;
        this.frequncy = frequncy;
    }

    public String getWord(){
        return word;
    }

    public int getFrequency(){
        return frequncy;
    }


}
