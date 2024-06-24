package run.attraction.api.v1.auth.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStateDto {
  private String email;
  private boolean isUserBefore;
  private boolean shouldReissueToken;
  private boolean hasExtraDetails;

  @Builder
  private UserStateDto(String email, boolean isUserBefore) {
    this.email = email;
    this.isUserBefore = isUserBefore;
  }

  public void setShouldReissueToken(boolean shouldReissueToken) {
    this.shouldReissueToken = shouldReissueToken;
  }

  public void setHasExtraDetails(boolean hasExtraDetails) {
    this.hasExtraDetails = hasExtraDetails;
  }
}