package run.attraction.api.v1.gmail.event;

import org.springframework.util.Assert;

public record UserSubscribedEvent(
    String userEmail,
    String newsletterEmail,
    String token
) {
  public UserSubscribedEvent {
    Assert.hasText(userEmail, "사용자 이메일이 존재하지 않습니다.");
    Assert.hasText(newsletterEmail, "뉴스레터 이메일이 존재하지 않습니다.");
    Assert.hasText(token, newsletterEmail + "을 가진 사용자의 Google 토큰이 존재하지 않습니다.");
  }
}