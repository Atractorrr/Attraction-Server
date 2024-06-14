package run.attraction.api.v1.bookmark.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import run.attraction.api.v1.bookmark.controller.BookmarkController;

@Order(1)
@RestControllerAdvice(assignableTypes = BookmarkController.class)
public class BookmarkExceptionHandler {

  private ResponseEntity<Map<String, String>> buildResponseEntity(String message, String details, HttpStatus status) {
    Map<String,  String> errors = new HashMap<>();
    errors.put("message", message);
    errors.put("details", details);
    return new ResponseEntity<>(errors, status);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
    return buildResponseEntity("Invalid request", ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
    return buildResponseEntity("Server error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
