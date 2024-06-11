package run.attraction.api.v1.home.service.dto.categories;

import java.util.List;
import java.util.Objects;

public record CategoriesResponseDto(
    List<String> categories
) {
  public CategoriesResponseDto {
    Objects.requireNonNull(categories);
  }
}
