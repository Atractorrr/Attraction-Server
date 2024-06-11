package run.attraction.api.v1.auth.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import run.attraction.api.v1.auth.provider.exception.GoogleApiAccessTokenException;
import run.attraction.api.v1.auth.provider.exception.GoogleApiCodeException;

@ControllerAdvice
public class AuthenticationExceptionHandler {
  @ExceptionHandler(GoogleApiCodeException.class)
  public ResponseEntity<String> GoogleApiExceptionHandler(GoogleApiCodeException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(GoogleApiAccessTokenException.class)
  public ResponseEntity<String> GoogleApiAccessTokenExceptionHandler(GoogleApiAccessTokenException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<String> illegalStateExceptionHandler(IllegalStateException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
