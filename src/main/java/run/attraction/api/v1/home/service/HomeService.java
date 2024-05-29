package run.attraction.api.v1.home.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.home.service.dto.newsletter.NewsletterDetailDto;
import run.attraction.api.v1.home.service.newsletter.HomeNewsletterService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

  private final HomeNewsletterService newsletterService;

  public List<String> getCategoriesByEmail(String email) {
    if (email.isEmpty()) {
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
}
