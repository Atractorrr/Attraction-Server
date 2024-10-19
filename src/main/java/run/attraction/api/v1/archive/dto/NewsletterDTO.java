package run.attraction.api.v1.archive.dto;


import com.querydsl.core.annotations.QueryProjection;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;

public record NewsletterDTO(
    Long id,
    String name,
    Category category,
    String thumbnailUrl,
    String homepageUrl,
    String prevArticleListUrl
) {
  @QueryProjection
  public NewsletterDTO(Newsletter newsletter) {
    this(
        newsletter.getId(),
        newsletter.getName(),
        newsletter.getCategory(),
        newsletter.getThumbnailUrl(),
        newsletter.getHomepageUrl(),
        newsletter.getPrevArticleListUrl()
    );
  }

  public static NewsletterDTO from(Newsletter newsletter) {
    return new NewsletterDTO(
        newsletter.getId(),
        newsletter.getName(),
        newsletter.getCategory(),
        newsletter.getThumbnailUrl(),
        newsletter.getHomepageUrl(),
        newsletter.getPrevArticleListUrl()
    );
  }
}
