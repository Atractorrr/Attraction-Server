package run.attraction.api.v1.auth.service.dto.login;

import lombok.Getter;

@Getter
public class LoginResponseDto {
  private String accessToken;

  public LoginResponseDto(String accessToken) {
    this.accessToken = accessToken;
  }
}
