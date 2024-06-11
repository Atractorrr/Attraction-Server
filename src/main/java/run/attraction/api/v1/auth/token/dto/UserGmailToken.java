package run.attraction.api.v1.auth.token.dto;

import io.jsonwebtoken.lang.Assert;

public record UserGmailToken(
    String token
) {
  public UserGmailToken {
    Assert.hasText(token, "token 정보가 존재하지 않습니다.");
  }
}
