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
public class SessionIdNotFoundExceptionHandler {
  @ExceptionHandler(SessionIdNotFoundException.class)
  protected ResponseEntity<ErrorResponse> handleSessionIdNotFoundException(SessionIdNotFoundException e) {
    ErrorCode errorCode = ErrorCode.SESSION_ID_NOT_FOUND;
    final ErrorResponse response = ErrorResponse.of(errorCode);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
