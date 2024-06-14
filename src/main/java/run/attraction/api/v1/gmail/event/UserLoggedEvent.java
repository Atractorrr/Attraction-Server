package run.attraction.api.v1.gmail.event;

import io.jsonwebtoken.lang.Assert;

public record UserLoggedEvent(
    String email,
    String token
) {
  public UserLoggedEvent {
    Assert.hasText(email, "사용자 이메일이 존재하지 않습니다.");
    Assert.hasText(token, email +"을 가진 사용자의 Google 토큰이 존재하지 않습니다.");
  }
}
