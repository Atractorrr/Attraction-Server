package run.attraction.api.v1.auth.session.dto;

import java.util.Objects;

public record UserDetailBySession(
    String email,
    String nickname,
    String profileImg,
    boolean hasExtraDetails,
    boolean shouldReissueToken
) {
  public UserDetailBySession {
    Objects.requireNonNull(email);
    Objects.requireNonNull(profileImg);
  }
}
