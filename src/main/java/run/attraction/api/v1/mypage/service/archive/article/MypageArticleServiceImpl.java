package run.attraction.api.v1.mypage.service.archive.article;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticlesDto;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageArticleServiceImpl implements MypageArticleService {

  private final ArticleRepository articleRepository;

  public List<RecentArticlesDto> getUserRecentArticles(String userEmail) {
    return articleRepository.findRecentArticlesByUserEmail(userEmail);
  }
}
