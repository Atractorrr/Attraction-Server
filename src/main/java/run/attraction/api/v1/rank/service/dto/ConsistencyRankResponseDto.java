package run.attraction.api.v1.rank.service.dto;

import java.util.List;
import java.util.Objects;

public record ConsistencyRankResponseDto(
    List<RankDetailDto> userStreakRank
) {
  public ConsistencyRankResponseDto {
    Objects.requireNonNull(userStreakRank);
  }
}