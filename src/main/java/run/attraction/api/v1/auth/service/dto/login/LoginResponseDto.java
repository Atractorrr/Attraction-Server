package run.attraction.api.v1.auth.service.dto.login;

import lombok.Getter;

@Getter
public class LoginResponseDto {
  private String email;
  private String accessToken;

  public LoginResponseDto(String email, String accessToken) {
    this.email = email;
    this.accessToken = accessToken;
  }
}
