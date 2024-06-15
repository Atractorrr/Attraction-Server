package run.attraction.api.v1.gmail.event;

import io.jsonwebtoken.lang.Assert;

public record SubscribeVo(
    String userEmail,
    String token
) {
  public SubscribeVo {
    Assert.hasText(userEmail, "userEmail 정보가 존재하지 않습니다.");
    Assert.hasText(token, "token 정보가 존재하지 않습니다.");
  }
}
