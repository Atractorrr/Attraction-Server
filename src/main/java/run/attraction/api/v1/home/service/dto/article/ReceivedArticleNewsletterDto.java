package run.attraction.api.v1.home.service.dto.article;

import com.querydsl.core.annotations.QueryProjection;
import run.attraction.api.v1.introduction.Newsletter;

public record ReceivedArticleNewsletterDto(
    Long id,
    String name,
    String thumbnailUrl
) {
  private static final String THUMBNAIL = "/thumbnails/";

  @QueryProjection
  public ReceivedArticleNewsletterDto(Newsletter newsletter) {
    this(
        newsletter.getId(),
        newsletter.getName(),
        THUMBNAIL + newsletter.getThumbnailUrl()
    );
  }

  public static ReceivedArticleNewsletterDto from(Newsletter newsletter) {
    return new ReceivedArticleNewsletterDto(
        newsletter.getId(),
        newsletter.getName(),
        THUMBNAIL + newsletter.getThumbnailUrl()
    );
  }
}
