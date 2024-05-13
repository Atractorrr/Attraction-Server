package run.attraction.api.v1.auth.service.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {
  private Long userId;
  private boolean hasExtraDetails;

  @Builder
  private LoginResponseDto(Long userId, boolean hasExtraDetails) {
    this.userId = userId;
    this.hasExtraDetails = hasExtraDetails;
  }
}
