package run.attraction.api.v1.gmail.dto;

import org.springframework.util.Assert;

public record UserGmailDto(
    String userEmail,
    String newsletterEmail
) {
  public UserGmailDto {
    Assert.hasText(userEmail, "userEmail 정보가 존재하지 않습니다.");
    Assert.hasText(newsletterEmail, "newsletterEmail 정보가 존재하지 않습니다.");
  }
}
