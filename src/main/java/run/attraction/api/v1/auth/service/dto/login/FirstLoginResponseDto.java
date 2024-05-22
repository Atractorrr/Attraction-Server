package run.attraction.api.v1.auth.service.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FirstLoginResponseDto {
  private Long userId;
  private boolean hasExtraDetails;
  private String accessToken;

  @Builder
  private FirstLoginResponseDto(Long userId, boolean hasExtraDetails, String accessToken) {
    this.userId = userId;
    this.hasExtraDetails = hasExtraDetails;
    this.accessToken = accessToken;
  }
}

