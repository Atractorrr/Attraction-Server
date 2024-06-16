package run.attraction.api.v1.home.service.newsletter;

import org.springframework.data.domain.Page;
import run.attraction.api.v1.home.service.dto.newsletter.NewsletterDetailDto;
import run.attraction.api.v1.introduction.dto.response.NewslettersByCategoryResponse;

import java.util.List;

public interface HomeNewsletterService {
  List<String> getDefaultCategories();
  List<String> getUserCategories(String email);
  List<NewsletterDetailDto> getMostNewsletterByCategory(String category, int size);
  List<NewsletterDetailDto> getMostNewsletter(int size);
  boolean hasUserDetail(String email);
  Page<NewslettersByCategoryResponse> getNewsletterBySearch(String search, int page, int size);
}
