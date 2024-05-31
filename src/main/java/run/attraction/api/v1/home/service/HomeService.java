package run.attraction.api.v1.home.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.home.service.article.HomeArticleService;
import run.attraction.api.v1.home.service.dto.article.ArticleDetailDto;
import run.attraction.api.v1.home.service.dto.newsletter.NewsletterDetailDto;
import run.attraction.api.v1.home.service.newsletter.HomeNewsletterService;

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

  public List<ArticleDetailDto> getReceivedArticlesByEmail(String email){
    LocalDate endDate = LocalDate.now();
    LocalDate startDate = endDate.minusDays(6);
    return articleService.getReceivedArticles(email,startDate,endDate);
  }
}
