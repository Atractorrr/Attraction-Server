package run.attraction.api.v1.introduction.dto.response;

import run.attraction.api.v1.introduction.Newsletter;

public record SubscribedNewsletterResponse(
    Long id,
    String name,
    String thumbnailUrl
) {
  public SubscribedNewsletterResponse(Newsletter newsletter) {
    this(newsletter.getId(), newsletter.getName(), newsletter.getThumbnailUrl());
  }
}
