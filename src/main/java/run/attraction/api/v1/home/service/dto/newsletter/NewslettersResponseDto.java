package run.attraction.api.v1.home.service.dto.newsletter;

import java.util.List;
import java.util.Objects;

public record NewslettersResponseDto(
    List<NewsletterDetailDto> mainPageNewsletters
) {
  public NewslettersResponseDto {
    Objects.requireNonNull(mainPageNewsletters);
  }
}
