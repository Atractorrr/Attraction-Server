package run.attraction.api.v1.home.service.dto.newsletter;

import java.util.Objects;

public record NewsletterDetailDto(
    Long id,
    String newsletterThumbnailUrl,
    String name,
    String description
) {
  public NewsletterDetailDto {
    Objects.requireNonNull(id, "id must not be null");
    Objects.requireNonNull(newsletterThumbnailUrl, "newsletterThumbnailUrl must not be null");
    Objects.requireNonNull(name, "name must not be null");
    Objects.requireNonNull(description, "description must not be null");
  }
}
