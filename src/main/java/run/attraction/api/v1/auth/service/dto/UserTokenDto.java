package run.attraction.api.v1.auth.service.dto;

import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserTokenDto {
  private String accessToken;
  private String refreshToken;
  private String email;
  private boolean isUserBefore;
  @Setter
  private boolean shouldReissueToken;

  @Builder
  private UserTokenDto(String accessToken, String refreshToken, String email, boolean isUserBefore) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.email = email;
    this.isUserBefore = isUserBefore;
  }

  public boolean getShouldReissueToken() {
    return shouldReissueToken;
  }
  public void setShouldReissueToken(boolean shouldReissueToken) {
    this.shouldReissueToken = shouldReissueToken;
  }
}