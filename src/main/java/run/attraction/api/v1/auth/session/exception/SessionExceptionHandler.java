package run.attraction.api.v1.auth.session.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import run.attraction.api.v1.exception.ErrorCode;
import run.attraction.api.v1.exception.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class SessionExceptionHandler {
  @ExceptionHandler(SessionNotFoundException.class)
  protected ResponseEntity<ErrorResponse> handleSessionIdNotFoundException(SessionNotFoundException e) {
    ErrorCode errorCode = ErrorCode.SESSION_NOT_FOUND;
    final ErrorResponse response = ErrorResponse.of(errorCode);
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(InValidUserException.class)
  protected ResponseEntity<ErrorResponse> handleInValidUserException(InValidUserException e) {
    ErrorCode errorCode = ErrorCode.SESSION_INVALID__USER;
    final ErrorResponse response = ErrorResponse.of(errorCode);
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ResignedUserException.class)
  protected ResponseEntity<ErrorResponse> handleInValidUserException(ResignedUserException e) {
    ErrorCode errorCode = ErrorCode.SESSION_RESIGNED_USER;
    final ErrorResponse response = ErrorResponse.of(errorCode);
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

}
