package run.attraction.api.v1.auth.token.exception;

public class TokenExpirationException extends RuntimeException {
  public TokenExpirationException(String message) {
    super(message);
  }
}
