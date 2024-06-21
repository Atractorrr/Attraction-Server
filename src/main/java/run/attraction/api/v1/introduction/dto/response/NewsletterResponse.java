package run.attraction.api.v1.introduction.dto.response;

import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;

public record NewsletterResponse(
    String name,
    String description,
    String uploadDays,
    Category category,
    String mainLink,
    String subscribeLink,
    String thumbnailUrl,
    boolean isAutoSubscribeEnabled
) {
  public static NewsletterResponse from(Newsletter newsletter) {
    return new NewsletterResponse(
        newsletter.getName(),
        newsletter.getDescription(),
        newsletter.getUploadDays(),
        newsletter.getCategory(),
        newsletter.getMainLink(),
        newsletter.getSubscribeLink(),
        newsletter.getThumbnailUrl(),
        newsletter.isAutoSubscribeEnabled()
    );
  }
}
