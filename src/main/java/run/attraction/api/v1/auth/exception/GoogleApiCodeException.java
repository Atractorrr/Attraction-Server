package run.attraction.api.v1.auth.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class GoogleApiException extends IllegalArgumentException {
  private final HttpHeaders headers;

  public GoogleApiException(HttpStatusCode status, HttpHeaders headers) {
    super("[Google API 통신 에러] 유효하지 않은 code 값 입니다.");
    this.headers = headers;
  }

  public HttpHeaders getHeaders() {
    return headers;
  }
}
