package run.attraction.api.v1.mypage.service.archive.article;

import java.util.List;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticlesDto;

public interface MypageArticleService {
  List<RecentArticlesDto> getUserRecentArticles(String email, int size);
}
