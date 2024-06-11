package run.attraction.api.v1.user.validator.exception;

public class InvalidNicknameException extends RuntimeException{
  public InvalidNicknameException(){
  }
  public InvalidNicknameException(String message) {
    super(message);
  }
}
