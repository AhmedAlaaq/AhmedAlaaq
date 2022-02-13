package com.assessment.ordina.contoller.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Handles exceptions mapped to {@link HttpStatus#INTERNAL_SERVER_ERROR}
     */
    @ExceptionHandler(InvalidTextException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleBadRequestExceptionsWithText(InvalidTextException exception) {
        LOGGER.error("An error occurred: ", exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), INTERNAL_SERVER_ERROR);
    }

}
