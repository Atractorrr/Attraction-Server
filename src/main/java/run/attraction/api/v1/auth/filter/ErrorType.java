package run.attraction.api.v1.auth.filter;

import lombok.Getter;

@Getter
public enum ErrorType {
  SESSION_NOT_FOUND(401, "세션이 존재하지 않습니다."),
  SESSION_INVALID__USER(401,"유효하지 않은 유저입니다."),
  SESSION_RESIGNED_USER(401, "탈퇴한 회원입니다.");

  private int code;
  private String message;

  ErrorType(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
