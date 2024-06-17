package run.attraction.api.v1.introduction.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex, WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    String errorMessage = ex.getMessage().isEmpty() ? ErrorMessages.SEVER_ERROR.getViewName() : ex.getMessage();
    errors.put("message", errorMessage);
    errors.put("details", request.getDescription(false));
    return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}