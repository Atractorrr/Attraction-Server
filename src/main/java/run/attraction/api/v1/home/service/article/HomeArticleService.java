package run.attraction.api.v1.home.service.article;

import org.springframework.data.domain.Page;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesDto;
import run.attraction.api.v1.home.service.dto.search.ArticleSearchDto;

import java.util.List;

public interface HomeArticleService {
  List<ReceivedArticlesDto> getReceivedArticles(String email, int size);
  Page<ArticleSearchDto> getArticleBySearch(String search, int page, int size);
}
