package run.attraction.api.v1.gmail.event;

import org.springframework.util.Assert;

public record SubscribeVo(
    String newsletterEmail,
    String token
) {
  public SubscribeVo {
    Assert.hasText(newsletterEmail, "newsletterEmail 정보가 존재하지 않습니다.");
    Assert.hasText(token, "token 정보가 존재하지 않습니다.");
  }
}