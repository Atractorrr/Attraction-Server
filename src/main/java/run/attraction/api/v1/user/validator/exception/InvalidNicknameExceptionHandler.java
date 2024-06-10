package run.attraction.api.v1.user.validator.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import run.attraction.api.v1.mypage.service.dto.MessageResponse;

@Order(1)
@RestControllerAdvice
public class InvalidNicknameExceptionHandler {
  @ExceptionHandler(InvalidNicknameException.class)
  public ResponseEntity<MessageResponse> GoogleApiExceptionHandler(InvalidNicknameException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(e.getMessage()));
  }
}