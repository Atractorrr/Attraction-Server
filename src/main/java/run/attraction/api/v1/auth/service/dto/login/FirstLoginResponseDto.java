package run.attraction.api.v1.auth.service.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FirstLoginResponseDto {
  private String email;
  private String accessToken;
  private boolean shouldReissueToken;
  private boolean hasExtraDetails;

  @Builder
  private FirstLoginResponseDto(String email, String accessToken, boolean shouldReissueToken, boolean hasExtraDetails) {
    this.email = email;
    this.accessToken = accessToken;
    this.shouldReissueToken = shouldReissueToken;
    this.hasExtraDetails = hasExtraDetails;
  }
}

