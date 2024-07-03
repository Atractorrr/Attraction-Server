package run.attraction.api.v1.introduction.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.archive.dto.NewsletterDTO;
import run.attraction.api.v1.introduction.Newsletter;

import java.time.LocalDate;

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

  @QueryProjection
  public PreviousArticleResponse(Article article, Newsletter newsletter) {
    this(
         article.getId(),
        article.getTitle(),
        article.getThumbnailUrl(),
        buildUrl("/thumbnail/", article.getContentUrl()),
        buildUrl("/article/",article.getContentSummary()),
        article.getReadingTime() ,
        article.getReceivedAt(),
        newsletter.getName(),
        new NewsletterDTO(newsletter)
    );
  }

  public static PreviousArticleResponse from(Article article, String newsletterName) {
    return new PreviousArticleResponse(
        article.getId(),
        article.getTitle(),
        buildUrl("/thumbnail/", article.getThumbnailUrl()),
        buildUrl("/article/", article.getContentUrl()),
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
        buildUrl("/thumbnail/", article.getThumbnailUrl()),
        buildUrl("/article/", article.getContentUrl()),
        article.getContentSummary(),
        article.getReadingTime(),
        article.getReceivedAt(),
        null,
        new NewsletterDTO(newsletter)
    );
  }

  private static String buildUrl(String type, String path) {
    return type + path;
  }
}
