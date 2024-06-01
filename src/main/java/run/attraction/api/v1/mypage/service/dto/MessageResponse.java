package run.attraction.api.v1.mypage.service.dto;

import java.util.Objects;

public record MessageResponse(
    String message
) {
  public MessageResponse {
    Objects.requireNonNull(message);
  }
}
