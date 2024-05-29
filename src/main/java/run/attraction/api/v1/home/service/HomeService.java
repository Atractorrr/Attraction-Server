package run.attraction.api.v1.home.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.repository.ArticleRepository;
import run.attraction.api.v1.home.service.newsletter.HomeNewsletterService;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;
import run.attraction.api.v1.user.Interest;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class HomeService {

  private final HomeNewsletterService newsletterService;

  @Transactional(readOnly = true)
  public List<String> getCategoriesByEmail(String email) {
    if (email.isEmpty()) {
      return newsletterService.getDefaultCategories();
    }
    return newsletterService.getUserCategories(email);
  }

}
