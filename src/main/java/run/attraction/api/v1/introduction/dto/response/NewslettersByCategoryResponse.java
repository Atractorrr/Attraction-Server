package run.attraction.api.v1.introduction.dto.response;

import run.attraction.api.v1.introduction.Newsletter;

public record NewslettersByCategoryResponse(
    Long id,
    String thumbnailUrl,
    String name,
    String description
) {
  public NewslettersByCategoryResponse(Newsletter newsletter) {
    this(
        newsletter.getId(),
        newsletter.getThumbnail(),
        newsletter.getName(),
        newsletter.getDescription()
    );
  }
}
