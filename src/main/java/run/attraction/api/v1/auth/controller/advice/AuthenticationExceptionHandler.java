package run.attraction.api.v1.auth.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import run.attraction.api.v1.auth.exception.GoogleApiException;

@ControllerAdvice
public class AuthenticationExceptionHandler {
  @ExceptionHandler(GoogleApiException.class)
  public ResponseEntity<String> GoogleApiExceptionHandler(GoogleApiException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
