package run.attraction.api.v1.mypage.service.dto.archive.newsletter;

import java.util.Objects;

public record MypageNewsletterDetail(
    Long id,
    String thumbnailUrl,
    String title
) {
  public MypageNewsletterDetail {
    Objects.requireNonNull(id);
    Objects.requireNonNull(thumbnailUrl);
    Objects.requireNonNull(title);
  }
}