package run.attraction.api.v1.auth.service.dto.login;

import lombok.Getter;

@Getter
public class LoginResponseDto {
  private String email;
  private boolean shouldReissueToken;
  private boolean hasExtraDetails;

  public LoginResponseDto(String email, boolean shouldReissueToken, boolean hasExtraDetails) {
    this.email = email;
    this.shouldReissueToken = shouldReissueToken;
    this.hasExtraDetails = hasExtraDetails;
  }
}
