package run.attraction.api.v1.archive.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesDto;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;
import run.attraction.api.v1.mypage.service.dto.archive.article.RecentArticlesDto;

public interface ArticleRepositoryCustom {
  Page<ArticleDTO> findArticlesByUserEmail(String userEmail, List<String> newsletterEmails, String category, Boolean isRead, String search,  Pageable pageable);
  Page<ArticleDTO> findArticlesByArticleIds(List<Long> articleIds, String category, String search, Pageable pageable);
  Optional<ArticleDTO> findArticleByUserEmailAndArticleId(String userEmail, Long articleId);
  List<RecentArticlesDto> findRecentArticlesByUserEmail(String userEmail, int size);
  List<ReceivedArticlesDto> findReceivedArticlesByUserEmail(String userEmail, List<String> newsletterEmails,  int size);
  List<PreviousArticleResponse> findPreviousArticlesByUserEmail(String userEmail, int size);
}
