package run.attraction.api.v1.introduction.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    logger.error("IllegalArgumentException: {}", ex.getMessage(), ex);
    Map<String, Object> errors = new HashMap<>();
    errors.put("timestamp", LocalDateTime.now());
    errors.put("message", ex.getMessage().isEmpty() ? "Bad Request" : ex.getMessage());
    errors.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
    errors.put("status", HttpStatus.BAD_REQUEST.value());
    errors.put("path", request.getDescription(false).substring(4)); // URI 경로
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  // 400 Bad Request - 요청 파라미터의 유효성 검사가 실패했을 때 발생
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
    logger.error("MethodArgumentNotValidException: {}", ex.getMessage(), ex);
    Map<String, Object> errors = new HashMap<>();
    errors.put("timestamp", LocalDateTime.now());
    errors.put("message", "Validation failed");
    errors.put("errors", ex.getBindingResult().getFieldErrors().stream()
        .collect(Collectors.toMap(FieldError::getField, fieldError -> {
          String defaultMessage = fieldError.getDefaultMessage();
          return defaultMessage != null ? defaultMessage : "Validation error";
        })));
    errors.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
    errors.put("status", HttpStatus.BAD_REQUEST.value());
    errors.put("path", request.getDescription(false).substring(4));
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  // 405 Method Not Allowed - 지원되지 않는 HTTP 메서드로 요청했을 때 발생
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public ResponseEntity<Map<String, Object>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
    logger.error("HttpRequestMethodNotSupportedException: {}", ex.getMessage(), ex);
    Map<String, Object> errors = new HashMap<>();
    errors.put("timestamp", LocalDateTime.now());
    errors.put("message", ex.getMessage());
    errors.put("error", HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    errors.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
    errors.put("path", request.getDescription(false).substring(4));
    return new ResponseEntity<>(errors, HttpStatus.METHOD_NOT_ALLOWED);
  }

  // 400 Bad Request - 유효성 검사가 실패했을 때 발생
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
    logger.error("ConstraintViolationException: {}", ex.getMessage(), ex);
    Map<String, Object> errors = new HashMap<>();
    errors.put("timestamp", LocalDateTime.now());
    errors.put("status", HttpStatus.BAD_REQUEST.value());
    errors.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
    errors.put("message", "Constraint violation");
    errors.put("path", request.getDescription(false).substring(4));
    errors.put("errors", ex.getConstraintViolations().stream()
        .collect(Collectors.toMap(violation -> {
          // Path 정보에서 정확한 위치 추출
          StringBuilder propertyPath = new StringBuilder();
          for (Path.Node node : violation.getPropertyPath()) {
            propertyPath.append(node.getName()).append(".");
          }
          return "parameter";
        }, ConstraintViolation::getMessage)));
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  // 500 Internal Server Exception - 통합
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex, WebRequest request) {
    logger.error("Exception: {}", ex.getMessage(), ex);
    Map<String, Object> errors = new HashMap<>();
    errors.put("timestamp", LocalDateTime.now());
    errors.put("message", ex.getMessage());
    errors.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    errors.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    errors.put("path", request.getDescription(false).substring(4)); // URI 경로
    return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}