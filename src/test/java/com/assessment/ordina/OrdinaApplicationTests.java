package com.assessment.ordina;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import com.assessment.ordina.contoller.exception.InvalidTextException;
import com.assessment.ordina.core.WordFrequency.WordFrequency;
import com.assessment.ordina.core.WordFrequency.WordFrequencyImp;
import com.assessment.ordina.core.WordFrequencyAnalyzer.WordFrequencyAnalyzer;
import com.assessment.ordina.core.WordFrequencyAnalyzer.WordFrequencyAnalyzerImp;
import com.assessment.ordina.service.WordFrequencyService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class OrdinaApplicationTests {

    private static final String TEXT = "The sun shines over the lake";
    private static final String WORD = "ThE";
    private static final String SPECIAL_CHARACTER_TEXT = "The sun$ shines % over the lake&";
    private static final String ERROR_MESSAGE = "text should not contains special characters";
    private static final String URL_REQUEST_PATH = "/HighestFrequency";
    private static final int NUMBER_OF_WORDS = 3;
    private static final WordFrequency[] mostFrequentWords = new WordFrequency[NUMBER_OF_WORDS];

    @Mock
    private WordFrequencyAnalyzer wordFrequencyAnalyzer = new WordFrequencyAnalyzerImp();

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Mock
    private WordFrequencyService wordFrequencyService = new WordFrequencyService(wordFrequencyAnalyzer);

    @Before
    public void init() {
        mockMvc = webAppContextSetup(wac).build();
        mostFrequentWords[0] = new WordFrequencyImp("the",2);
        mostFrequentWords[1] = new WordFrequencyImp("sun",1);
        mostFrequentWords[2] = new WordFrequencyImp("shines",1);

    }

    @Test
    public void calculateHighestFrequency() {
        when(wordFrequencyService.calculateHighestFrequency(TEXT)).thenReturn(2);

    }

    @Test
    public void textValidationWithSpecialCharactersShouldThrowException() throws Exception {
        when (wordFrequencyService.calculateHighestFrequency(SPECIAL_CHARACTER_TEXT))
                .thenThrow(new InvalidTextException(ERROR_MESSAGE));
        mockMvc.perform(get(URL_REQUEST_PATH))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    public void calculateFrequencyForWord() {
        when(wordFrequencyService.calculateFrequencyForWord(TEXT, WORD)).thenReturn(2);
    }

    @Test
    public void calculateMostFrequentWords() {
        when(wordFrequencyService.calculateMostFrequentWords(TEXT, 3)).thenReturn(mostFrequentWords);
    }


}
