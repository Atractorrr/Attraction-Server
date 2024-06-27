package run.attraction.api.v1.auth.session.exception;

public class ResignedUserException extends RuntimeException{
  public ResignedUserException() {
  }

  public ResignedUserException(String message) {
    super(message);
  }
}
