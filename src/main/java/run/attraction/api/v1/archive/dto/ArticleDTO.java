package run.attraction.api.v1.archive.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.util.Date;
import run.attraction.api.v1.introduction.Article;

public record ArticleDTO(
    Long id,
    String title,
    String thumbnail,
    String contentUrl,
    int readingTime,
    Date receivedAt,
    int percentage,
    NewsletterDTO newsletter
) {
  @QueryProjection
  public ArticleDTO(Article article, int percentage) {
    this(
        article.getId(),
        article.getTitle(),
        article.getThumbnail(),
        article.getContentUrl(),
        article.getReadingTime(),
        article.getReceivedAt(),
        percentage,
        new NewsletterDTO(article.getNewsletter())
    );
  }


}

