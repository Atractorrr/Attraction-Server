package run.attraction.api.v1.home.service.dto.article;

import java.util.List;
import java.util.Objects;

public record ReceivedArticlesResponseDto(
    List<ReceivedArticlesDto> mainPageArticles
){
  public ReceivedArticlesResponseDto {
    Objects.requireNonNull(mainPageArticles);
  }
}
