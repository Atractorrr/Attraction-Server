package run.attraction.api.v1.introduction.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import run.attraction.api.v1.archive.AdminArticle;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.archive.dto.NewsletterDTO;
import run.attraction.api.v1.introduction.Newsletter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PreviousArticleResponse(
    Long id,
    String title,
    String thumbnailUrl,
    String contentUrl,
    String contentSummary,
    int readingTime,
    LocalDate receivedAt,
    String newsletterName,
    NewsletterDTO newsletter
) {
  private static final String BASE_URL = "https://attraction-bucket.s3.ap-northeast-2.amazonaws.com/";

  public static PreviousArticleResponse from(AdminArticle article, String newsletterName) {
    return new PreviousArticleResponse(
        article.getId(),
        article.getTitle(),
        buildUrl("thumbnail/", article.getThumbnailUrl()),
        buildUrl("article/", article.getContentUrl()),
        article.getContentSummary(),
        article.getReadingTime(),
        article.getReceivedAt(),
        newsletterName,
        null
    );
  }

  public static PreviousArticleResponse from(Article article, Newsletter newsletter) {
    return new PreviousArticleResponse(
        article.getId(),
        article.getTitle(),
        buildUrl("thumbnail/", article.getThumbnailUrl()),
        buildUrl("article/", article.getContentUrl()),
        article.getContentSummary(),
        article.getReadingTime(),
        article.getReceivedAt(),
        null,
        new NewsletterDTO(newsletter)
    );
  }

  private static String buildUrl(String type, String path) {
    return BASE_URL + type + path;
  }
}
