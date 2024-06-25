package run.attraction.api.v1.home.service.dto.article;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticleNewsletterDto;

public record ReceivedArticlesDto(
    Long id,
    String title,
    String thumbnailUrl,
    int readingTime,
    LocalDate receivedAt,
    int readPercentage,
    RecentArticleNewsletterDto newsletter
) {

  @QueryProjection
  public ReceivedArticlesDto(Article article, int readPercentage, Newsletter newsletter) {
    this(
        article.getId(),
        article.getTitle(),
        article.getThumbnailUrl(),
        article.getReadingTime(),
        article.getReceivedAt(),
        readPercentage,
        new RecentArticleNewsletterDto(newsletter)
    );
  }
}

