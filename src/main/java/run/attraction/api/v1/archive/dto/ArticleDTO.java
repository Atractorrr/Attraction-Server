package run.attraction.api.v1.archive.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.introduction.Newsletter;

public record ArticleDTO(
    Long id,
    String title,
    String thumbnail,
    String contentUrl,
    int readingTime,
    LocalDate receivedAt,
    int readPercentage,
    NewsletterDTO newsletter
) {
  @QueryProjection
  public ArticleDTO(Article article, int readPercentage, Newsletter newsletter) {
    this(
        article.getId(),
        article.getTitle(),
        article.getThumbnailUrl(),
        article.getContentUrl(),
        article.getReadingTime(),
        article.getReceivedAt(),
        readPercentage,
        new NewsletterDTO(newsletter)
    );
  }
}

