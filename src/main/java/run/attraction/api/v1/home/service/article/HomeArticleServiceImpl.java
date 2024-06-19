package run.attraction.api.v1.home.service.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.home.service.dto.article.ArticleDetailDto;
import run.attraction.api.v1.home.service.dto.search.ArticleSearchDto;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HomeArticleServiceImpl implements HomeArticleService {
  private final ArticleRepository articleRepository;

  public List<ArticleDetailDto> getReceivedArticles(String email, LocalDate startDate, LocalDate endDate){
    return articleRepository.getReceivedArticleByEmail(email, startDate, endDate);
  }

  public Page<ArticleSearchDto> getArticleBySearch(String search, int page, int size){
    Pageable pageable = PageRequest.of(page,size);
    return articleRepository.findArticleBySearch(search, pageable);
  }

}
