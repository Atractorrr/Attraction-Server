package run.attraction.api.v1.home.service.article;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.home.service.dto.article.ArticleDetailDto;

@Component
@RequiredArgsConstructor
public class HomeArticleServiceImpl implements HomeArticleService {
  private final ArticleRepository articleRepository;

  public List<ArticleDetailDto> getReceivedArticles(String email, LocalDate startDate, LocalDate endDate){
    return articleRepository.getReceivedArticleByEmail(email, startDate, endDate);
  }

}
