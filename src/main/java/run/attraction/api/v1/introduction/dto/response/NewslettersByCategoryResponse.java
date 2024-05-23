package run.attraction.api.v1.introduction.dto.response;

import run.attraction.api.v1.introduction.Newsletter;

public record NewslettersByCategoryResponse(
    Long id,
    String thumbnailUrl,
    String name,
    String description
) {
  public static NewslettersByCategoryResponse from(Newsletter newsletter) {
    return new NewslettersByCategoryResponse(
        newsletter.getId(),
        newsletter.getThumbnailUrl(),
        newsletter.getName(),
        newsletter.getDescription()
    );
  }
}
