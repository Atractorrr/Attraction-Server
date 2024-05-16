package run.attraction.api.v1.auth.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserTokenDto {
  private String accessToken;
  private String refreshToken;
  private Long id;
  private boolean isUserBefore;

  @Builder
  private UserTokenDto(String accessToken, String refreshToken, Long id, boolean isUserBefore) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.id = id;
    this.isUserBefore = isUserBefore;
  }
}