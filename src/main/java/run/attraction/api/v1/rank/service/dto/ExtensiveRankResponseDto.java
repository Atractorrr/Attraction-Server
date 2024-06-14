package run.attraction.api.v1.rank.service.dto;

import java.util.List;
import java.util.Objects;

public record ExtensiveRankResponseDto(
    List<RankDetailDto> userArticleRank
) {
  public ExtensiveRankResponseDto {
    Objects.requireNonNull(userArticleRank);
  }
}
