package run.attraction.api.v1.home.service.dto.article;

import java.time.LocalDate;
import java.util.Objects;

public record ArticleDetailDto(
    Long id,
    String newsletterthumbnailUrl,
    String newsletterTitle,
    String articleThumbnailUrl,
    String title,
    LocalDate date,
    int readingTime,
    int readingPercentage
) {
  public ArticleDetailDto {
    Objects.requireNonNull(id);
    Objects.requireNonNull(newsletterthumbnailUrl);
    Objects.requireNonNull(newsletterTitle);
    Objects.requireNonNull(articleThumbnailUrl);
    Objects.requireNonNull(title);
    Objects.requireNonNull(date);
    if (readingTime == 0) {
      throw new IllegalArgumentException("readingTime must be greater than 0");
    }
    if (readingPercentage < 0 || readingPercentage > 100) {
      throw new IllegalArgumentException("readingPercentage must be between 0 and 100");
    }
  }
}
