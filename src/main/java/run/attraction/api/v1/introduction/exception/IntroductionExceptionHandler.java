package run.attraction.api.v1.introduction.exception;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import run.attraction.api.v1.introduction.controller.IntroductionController;

@Order(1)
@RestControllerAdvice(assignableTypes = IntroductionController.class)
public class IntroductionExceptionHandler {

  private ResponseEntity<Map<String, String>> buildResponseEntity(String message, String details, HttpStatus status) {
    Map<String,  String> errors = new HashMap<>();
    errors.put("message", message);
    errors.put("details", details);
    return new ResponseEntity<>(errors, status);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Map<String, String>> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
    return buildResponseEntity(ErrorMessages.INVALID_TYPE.getViewName(), request.getDescription(false), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Map<String, String>> handleNoSuchElementException(Exception ex, WebRequest request) {
    String errorMessage = ex.getMessage().isEmpty() ? ErrorMessages.SEVER_ERROR.getViewName() : ex.getMessage();
    return buildResponseEntity(errorMessage, request.getDescription(false), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getConstraintViolations().forEach(violation -> {
      errors.put("message", violation.getMessage());
      errors.put("path", (violation.getPropertyPath() != null ? violation.getPropertyPath().toString() : ""));
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    return buildResponseEntity(ex.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex,WebRequest request) {
    return buildResponseEntity(ErrorMessages.REQUEST_PARAMETER_MISSING.getViewName() + ex.getParameterName(), request.getDescription(false), HttpStatus.BAD_REQUEST);
  }
}
