package run.attraction.api.v1.mypage.service.dto.archive.article;

import java.time.LocalDate;
import java.util.Objects;

public record UserArticleDetail(
    String title,
    String name,
    LocalDate date,
    LocalDate receivedAt,
    int readingTime,
    int readPercentage
) {
  public UserArticleDetail {
    Objects.requireNonNull(title);
    Objects.requireNonNull(name);
    Objects.requireNonNull(date);
    Objects.requireNonNull(receivedAt);
  }
}
