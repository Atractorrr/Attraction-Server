package run.attraction.api.v1.introduction.dto.response;

import java.time.LocalDate;
import run.attraction.api.v1.archive.AdminArticle;

public record PreviousArticleResponse(
    Long id,
    String title,
    String thumbnailUrl,
    String contentUrl,
    String contentSummary,
    int readingTime,
    LocalDate receivedAt,
    String newsletterName
) {
  public static PreviousArticleResponse from(AdminArticle article, String newsletterName) {
    return new PreviousArticleResponse(
        article.getId(),
        article.getTitle(),
        article.getThumbnailUrl(),
        article.getContentUrl(),
        article.getContentSummary(),
        article.getReadingTime(),
        article.getReceivedAt(),
        newsletterName
    );
  }
}