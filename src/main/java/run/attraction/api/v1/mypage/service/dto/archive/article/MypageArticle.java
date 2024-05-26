package run.attraction.api.v1.mypage.service.dto.archive.article;

import java.util.Objects;

public record MypageArticle(
    Long id,
    UserArticleImg image,
    UserArticleDetail info
) {
  public MypageArticle {
    Objects.requireNonNull(id);
    Objects.requireNonNull(image);
    Objects.requireNonNull(info);
  }
}
