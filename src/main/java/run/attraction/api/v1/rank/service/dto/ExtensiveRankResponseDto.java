package run.attraction.api.v1.rank.service.dto;

import java.util.List;
import java.util.Objects;

public record ExtensiveRankResponseDto(
    List<RankDetailDto> userExtensiveRank
) {
  public ExtensiveRankResponseDto {
    Objects.requireNonNull(userExtensiveRank);
  }
}
