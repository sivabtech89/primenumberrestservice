package com.natwest.interview.exception;

import com.natwest.interview.model.ErrorResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  private static final String EXCEPTION_HANDLER_ACCEPT_HEADER = "application/hal+json";

  @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<@NonNull ErrorResponse> handleMethodNotSupportedException(
      final HttpRequestMethodNotSupportedException methodNotSupportedException) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .httpErrorCode(HttpStatus.METHOD_NOT_ALLOWED.value()).errorCode(5002)
        .origin("PrimeNumberService").errorMessage(methodNotSupportedException.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value())
        .contentType(MediaType.valueOf(EXCEPTION_HANDLER_ACCEPT_HEADER)).body(errorResponse);
  }

  @ExceptionHandler({Throwable.class})
  public ResponseEntity<@NonNull ErrorResponse> handleError(final Throwable throwable) {

    ErrorResponse errorResponse;
    switch (throwable.getCause()) {
      case NumberFormatException numberFormatException -> {
        errorResponse = ErrorResponse.builder().httpErrorCode(HttpStatus.BAD_REQUEST.value())
            .errorCode(5001).origin("PrimeNumberService")
            .errorMessage(numberFormatException.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .contentType(MediaType.valueOf(EXCEPTION_HANDLER_ACCEPT_HEADER)).body(errorResponse);
      }
      default -> {
        errorResponse = ErrorResponse.builder()
            .httpErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).errorCode(5005)
            .origin("PrimeNumberService").errorMessage("Internal Server Error!").build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .contentType(MediaType.valueOf(EXCEPTION_HANDLER_ACCEPT_HEADER)).body(errorResponse);
      }
    }
  }
}
