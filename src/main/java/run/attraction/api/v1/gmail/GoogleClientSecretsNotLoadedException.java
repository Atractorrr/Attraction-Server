package run.attraction.api.v1.gmail;

import java.io.IOException;

public class GoogleClientSecretsNotLoadedException extends RuntimeException {
  public GoogleClientSecretsNotLoadedException(Throwable cause) {
    super(cause);
  }
}
