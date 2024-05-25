package run.attraction.api.v1.mypage.service.archive.article;

import java.util.List;
import run.attraction.api.v1.mypage.service.dto.archive.article.MypageArticle;

public interface MypageArticleService {
  List<MypageArticle> getUserRecentArticles(String email);
}
