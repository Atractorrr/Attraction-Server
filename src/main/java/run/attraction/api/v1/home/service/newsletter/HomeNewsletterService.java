package run.attraction.api.v1.home.service.newsletter;

import java.util.List;
import run.attraction.api.v1.home.service.dto.newsletter.NewsletterDetailDto;

public interface HomeNewsletterService {
  List<String> getDefaultCategories();
  List<String> getUserCategories(String email);
  List<NewsletterDetailDto> getMostNewsletterByCategory(String category, int size);
  List<NewsletterDetailDto> getMostNewsletter(int size);
  boolean hasUserDetail(String email);
}
