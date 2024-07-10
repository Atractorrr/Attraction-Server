package run.attraction.api.v1.rank.service.dto;

import java.util.Objects;

public record RankDetailDto (
  String nickname,
  String profileImg,
  int value
){
  public RankDetailDto {
    Objects.requireNonNull(nickname);
    Objects.requireNonNull(profileImg);
    if (value < 0) {
      throw new IllegalArgumentException("Value cannot be negative");
    }
  }
}

