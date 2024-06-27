package run.attraction.api.v1.auth.session.exception;

public class InValidUserException extends RuntimeException{
  public InValidUserException() {
  }

  public InValidUserException(String message) {
    super(message);
  }
}
