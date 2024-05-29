package run.attraction.api.v1.auth.service.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FirstLoginResponseDto {
  private String email;
  private boolean hasExtraDetails;
  private String accessToken;

  @Builder
  private FirstLoginResponseDto(String email, boolean hasExtraDetails, String accessToken) {
    this.email = email;
    this.hasExtraDetails = hasExtraDetails;
    this.accessToken = accessToken;
  }
}

