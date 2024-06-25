package run.attraction.api.v1.home.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.home.service.article.HomeArticleService;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesDto;
import run.attraction.api.v1.home.service.dto.newsletter.NewsletterDetailDto;
import run.attraction.api.v1.home.service.dto.search.ArticleSearchDto;
import run.attraction.api.v1.home.service.newsletter.HomeNewsletterService;
import run.attraction.api.v1.introduction.dto.response.NewslettersByCategoryResponse;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

  private final HomeNewsletterService newsletterService;
  private final HomeArticleService articleService;

  public List<String> getCategoriesByEmail(String email) {
    if (email.isEmpty()||(!newsletterService.hasUserDetail(email))) {
      return newsletterService.getDefaultCategories();
    }
    return newsletterService.getUserCategories(email);
  }

  public List<NewsletterDetailDto> getNewsletter(String category, int size) {
    if (category.equals("RECOMMEND")){
      return newsletterService.getMostNewsletter(size);
    }
    return newsletterService.getMostNewsletterByCategory(category, size);
  }

  public List<ReceivedArticlesDto> getReceivedArticlesByEmail(String email, int size){
    return articleService.getReceivedArticles(email, size);
  }

  public Page<ArticleSearchDto> getArticleSearchResult(String search, int page, int size){
    return articleService.getArticleBySearch(search, page, size);
  }

  public Page<NewslettersByCategoryResponse> getNewsletterSearchResult(String search, int page, int size) {
    return newsletterService.getNewsletterBySearch(search, page, size);
  }
}
