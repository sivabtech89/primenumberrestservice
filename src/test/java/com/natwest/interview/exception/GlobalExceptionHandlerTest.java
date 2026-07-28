package com.natwest.interview.exception;

import com.natwest.interview.model.ErrorResponse;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;

public class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler exceptionHandler;

  @BeforeEach
  void setUp() {
    exceptionHandler = new GlobalExceptionHandler();
  }

  @Test
  void testHandleErrorForBadRequest() {
    ResponseEntity<@NonNull ErrorResponse> responseResponseEntity = exceptionHandler.handleError(
        new Exception("NumberFormatException Occurred!", new NumberFormatException()));
    Assertions.assertEquals(responseResponseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  void testHandleErrorForMethodNotSupported() {
    ResponseEntity<@NonNull ErrorResponse> responseResponseEntity = exceptionHandler.handleMethodNotSupportedException(
        new HttpRequestMethodNotSupportedException("Request Method not Supported"));
    Assertions.assertEquals(responseResponseEntity.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
  }

  @Test
  void testHandleErrorForUnknownError() {
    ResponseEntity<@NonNull ErrorResponse> responseResponseEntity = exceptionHandler.handleError(
        new Exception("Unknown Error Occurred!", new RuntimeException()));
    Assertions.assertEquals(responseResponseEntity.getStatusCode(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
