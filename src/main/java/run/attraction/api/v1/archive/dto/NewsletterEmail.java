package run.attraction.api.v1.archive.dto;

import org.springframework.util.Assert;

public record NewsletterEmail(
    String email
) {
  public NewsletterEmail {
    Assert.hasText(email, "email 정보가 존재하지 않습니다.");
  }
}
