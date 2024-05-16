package run.attraction.api.v1.introduction.dto.response;

import java.util.List;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.UploadDays;

public record NewsletterResponse(
    String name,
    String description,
    List<UploadDays> uploadDays,
    Category category,
    String mainLink,
    String subscribeLink,
    String thumbnail
) {
  public NewsletterResponse(Newsletter newsletter) {
    this(
        newsletter.getName(),
        newsletter.getDescription(),
        newsletter.getUploadDays(),
        newsletter.getCategory(),
        newsletter.getMainLink(),
        newsletter.getSubscriptionLink(),
        newsletter.getThumbnail()
    );
  }
}
