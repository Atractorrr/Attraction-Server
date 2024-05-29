package run.attraction.api.v1.auth.service.dto.login;

import lombok.Getter;

@Getter
public class LoginResponseDto {
  private String email;
  private String accessToken;
  private boolean shouldReissueToken;

  public LoginResponseDto(String email, String accessToken, boolean shouldReissueToken) {
    this.email = email;
    this.accessToken = accessToken;
    this.shouldReissueToken = shouldReissueToken;
  }
}
