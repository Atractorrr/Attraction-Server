package run.attraction.api.v1.auth.service.dto.join;

import java.util.Objects;

public record CheckDuplicationRequsetDto(
    String nickname
) {
  public CheckDuplicationRequsetDto {
    Objects.requireNonNull(nickname);
  }
}
