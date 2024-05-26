package run.attraction.api.v1.mypage.service.dto.archive.article;

import java.time.LocalDate;
import java.util.Objects;

public record UserReadBoxDetail(
    Long articleId,
    LocalDate readDate,
    int readPercentage
) {
  public UserReadBoxDetail {
    Objects.requireNonNull(articleId);
    Objects.requireNonNull(readDate);
  }
}
