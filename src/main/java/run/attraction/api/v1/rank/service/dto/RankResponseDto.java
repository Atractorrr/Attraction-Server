package run.attraction.api.v1.rank.service.dto;

import java.util.List;
import java.util.Objects;

public record RankResponseDto(
    List<RankDetailDto> userExtensiveRank
) {
  public RankResponseDto {
    Objects.requireNonNull(userExtensiveRank);
  }
}
