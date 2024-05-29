package run.attraction.api.v1.auth.service.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDto {
  private String provider;
  private String code;

  @Builder
  private LoginRequestDto(String provider, String code) {
    this.provider = provider;
    this.code = code;
  }
}
