package run.attraction.api.v1.auth.provider.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class GoogleApiAccessTokenException extends IllegalArgumentException {
  private final HttpHeaders headers;

  public GoogleApiAccessTokenException(HttpStatusCode status, HttpHeaders headers) {
    super("[Google API 통신 에러] 유효하지 않은 AccessToken 입니다.");
    this.headers = headers;
  }

  public HttpHeaders getHeaders() {
    return headers;
  }
}