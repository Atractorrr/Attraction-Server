package run.attraction.api.v1.home.service.article;

import java.time.LocalDate;
import java.util.List;
import run.attraction.api.v1.home.service.dto.article.ArticleDetailDto;

public interface HomeArticleService {
  List<ArticleDetailDto> getReceivedArticles(String email, LocalDate startDate, LocalDate endDate);
}
