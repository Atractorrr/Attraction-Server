package run.attraction.api.v1.auth.session.exception;

public class SessionNotFoundException extends RuntimeException {
  public SessionNotFoundException() {
  }

  public SessionNotFoundException(String message) {
    super(message);
  }
}
