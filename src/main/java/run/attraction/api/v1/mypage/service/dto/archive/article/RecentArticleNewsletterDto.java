package run.attraction.api.v1.mypage.service.dto.archive.article;

import com.querydsl.core.annotations.QueryProjection;
import run.attraction.api.v1.introduction.Newsletter;

public record RecentArticleNewsletterDto(
    Long id,
    String name,
    String thumbnailUrl
) {
  private static final String THUMBNAIL = "/thumbnails/";

  @QueryProjection
  public RecentArticleNewsletterDto(Newsletter newsletter) {
    this(
        newsletter.getId(),
        newsletter.getName(),
        THUMBNAIL + newsletter.getThumbnailUrl()
    );
  }

  public static RecentArticleNewsletterDto from(Newsletter newsletter) {
    return new RecentArticleNewsletterDto(
        newsletter.getId(),
        newsletter.getName(),
        THUMBNAIL + newsletter.getThumbnailUrl()
    );
  }
}
