package run.attraction.api.v1.mypage.service.dto.archive.article;

import java.util.Objects;

public record UserArticleImg(
    String thumbnail,
    String profile
) {
  public UserArticleImg {
//    Objects.requireNonNull(thumbnail);
//    Objects.requireNonNull(profile);
  }
}
