package run.attraction.api.v1.auth.session.exception;

import run.attraction.api.v1.exception.ErrorCode;

public class SessionIdNotFoundException extends RuntimeException {
  public SessionIdNotFoundException() {
  }

  public SessionIdNotFoundException(String message, ErrorCode errorCode) {
    super(message);
  }
}
