package run.attraction.api.v1.mypage.service.dto.archive.article;

import java.util.List;
import java.util.Objects;

public record RecentArticlesResponseDto(
    List<RecentArticlesDto> mypageArticles
) {
  public RecentArticlesResponseDto {
    Objects.requireNonNull(mypageArticles);
  }
}
