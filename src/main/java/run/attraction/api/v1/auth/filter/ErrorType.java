package run.attraction.api.v1.auth.filter;

import lombok.Getter;

@Getter
public enum ErrorType {
  NOT_VALID_TOKEN(400, "토큰이 유효하지 않습니다."),
  EXPIRED_TOKEN(400, "만료된 토큰입니다."),
  LOGOUT_ACCESS_TOKEN(400, "로그아웃된 Access Token 입니다."),
  NOT_FOUND_USER(400, "사용자가 존재하지 않습니다.");

  private int code;
  private String message;

  ErrorType(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
