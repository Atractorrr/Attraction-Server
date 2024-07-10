package run.attraction.api.v1.auth.session.dto;

import java.util.Objects;
import run.attraction.api.v1.user.Role;

public record UserDetailBySession(
    String email,
    String nickname,
    String profileImg,
    Role role,
    boolean hasExtraDetails,
    boolean shouldReissueToken
) {
  public UserDetailBySession {
    Objects.requireNonNull(email);
    Objects.requireNonNull(profileImg);
  }
}
