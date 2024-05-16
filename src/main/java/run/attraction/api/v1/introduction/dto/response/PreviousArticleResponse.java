package run.attraction.api.v1.introduction.dto.response;

import run.attraction.api.v1.introduction.Article;

public record PreviousArticleResponse(
    Long id,
    String title,
    String thumbnail,
    String contentUrl,
    String content,
    int readingTime
) {
  public PreviousArticleResponse(Article article) {
    this(
        article.getId(),
        article.getTitle(),
        article.getThumbnail(),
        article.getContentUrl(),
        article.getContent(),
        article.getReadingTime()
    );
  }
}