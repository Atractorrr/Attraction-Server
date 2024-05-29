package run.attraction.api.v1.home.service.dto.newsletter;

import java.util.List;
import java.util.Objects;

public record NewslettersResponseDto(
    List<NewsletterDetailDto> mainPageNewsLetters
) {
  public NewslettersResponseDto {
    Objects.requireNonNull(mainPageNewsLetters);
  }
}
