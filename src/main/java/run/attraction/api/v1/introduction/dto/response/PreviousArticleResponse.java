package run.attraction.api.v1.introduction.dto.response;

import run.attraction.api.v1.archive.AdminArticle;

public record PreviousArticleResponse(
    Long id,
    String title,
    String thumbnail,
    String contentUrl,
    String contentSummary,
    int readingTime
) {
  public static PreviousArticleResponse from(AdminArticle article) {
    return new PreviousArticleResponse(
        article.getId(),
        article.getTitle(),
        article.getThumbnailUrl(),
        article.getContentUrl(),
        article.getContentSummary(),
        article.getReadingTime()
    );
  }
}