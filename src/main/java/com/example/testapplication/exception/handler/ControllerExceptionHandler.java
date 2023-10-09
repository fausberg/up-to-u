package com.example.testapplication.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.example.testapplication.entity.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler({EntityNotFoundException.class})
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponse handleEntityNotFoundException(
      final EntityNotFoundException ex, final WebRequest request) {
    return new ErrorResponse(NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
  }
}
