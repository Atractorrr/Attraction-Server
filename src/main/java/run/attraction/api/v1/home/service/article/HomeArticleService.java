package run.attraction.api.v1.home.service.article;

import org.springframework.data.domain.Page;
import run.attraction.api.v1.home.service.dto.article.ArticleDetailDto;
import run.attraction.api.v1.home.service.dto.search.ArticleSearchDto;

import java.time.LocalDate;
import java.util.List;

public interface HomeArticleService {
  List<ArticleDetailDto> getReceivedArticles(String email, LocalDate startDate, LocalDate endDate);
  Page<ArticleSearchDto> getArticleBySearch(String search, int page, int size);
}
