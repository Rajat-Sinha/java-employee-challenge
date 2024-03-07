package com.example.rqchallenge.exception;

import com.example.rqchallenge.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@RestController
@Slf4j
public class GlobalResponseExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
        log.error("DataNotFoundException: " , ex);
        var response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParameterMissingException.class)
    public ResponseEntity<?> handleParameterMissingException(ParameterMissingException ex, WebRequest request) {
        log.error("ParameterMissingException: " , ex);
        var response = new Response(ex.getMessage());
        return ResponseEntity.ok().body(response);
    }

    @ExceptionHandler(DataCreationFailedException.class)
    public ResponseEntity<?> handleDataCreationFailedException(DataCreationFailedException ex, WebRequest request) {
        log.error("DataCreationFailedException: " , ex);
        var response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(DataUpdationFailedException.class)
    public ResponseEntity<?> handleDataUpdationFailedException(DataUpdationFailedException ex, WebRequest request) {
        log.error("DataUpdationFailedException: " , ex);
        var response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(InvalidAccessException.class)
    public ResponseEntity<?> handleInvalidAccessException(InvalidAccessException ex, WebRequest request) {
        log.error("InvalidAccessException: " , ex);
        var response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<?> handleDuplicateDataException(DuplicateDataException ex, WebRequest request) {
        log.error("DuplicateDataException: " , ex);
        var response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
