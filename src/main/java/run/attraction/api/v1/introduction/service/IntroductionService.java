package run.attraction.api.v1.introduction.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.AdminArticle;
import run.attraction.api.v1.archive.repository.AdminArticleRepository;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.dto.response.NewsletterResponse;
import run.attraction.api.v1.introduction.dto.response.NewslettersByCategoryResponse;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;
import run.attraction.api.v1.introduction.exception.ErrorMessages;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;

@Service
@RequiredArgsConstructor
public class IntroductionService {

  private final NewsletterRepository newsletterRepository;
  private final AdminArticleRepository adminArticleRepository;

  @Transactional(readOnly = true)
  public NewsletterResponse getNewsletter(Long newsletterId) {
    Newsletter newsletter = newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));

    return NewsletterResponse.from(newsletter);
  }

  @Transactional(readOnly = true)
  public List<PreviousArticleResponse> getPreviousArticles(Long newsletterId, int size) {
    Newsletter newsletter = newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));
    List<AdminArticle> previousArticles = adminArticleRepository.findByNewsletterEmail(newsletter.getEmail())
        .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));

    return previousArticles.stream()
        .limit(size)
        .map(previousArticle -> PreviousArticleResponse.from(previousArticle, newsletter.getName()))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<NewslettersByCategoryResponse> getRelatedNewslettersByCategory(Long newsletterId, int size) {
    Newsletter newsletter = newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));
    Category category = newsletter.getCategory();
    final int count = newsletterRepository.countByCategoryAndIdNot(newsletterId, category);

    if (count <= size) {
      return getNewslettersByCategory(newsletterId, category, size);
    }
    return getRandomNewslettersByCategory(newsletterId, category, size, count);
  }

  private List<NewslettersByCategoryResponse> getNewslettersByCategory(Long newsletterId, Category category, int size) {
    List<Newsletter> newsletters = newsletterRepository.findByCategoryAndIdNotWithOffset(newsletterId, category.name(),
        size, 0);

    return newsletters.stream()
        .map(NewslettersByCategoryResponse::from)
        .collect(Collectors.toList());
  }

  private List<NewslettersByCategoryResponse> getRandomNewslettersByCategory(Long newsletterId, Category category,
                                                                             int size, int count) {
    final int maxOffset = count - size;
    final int offset = new Random().nextInt(maxOffset + 1);

    List<Newsletter> newsletters = newsletterRepository.findByCategoryAndIdNotWithOffset(newsletterId,
        category.name(), size, offset);

    return newsletters.stream()
        .map(NewslettersByCategoryResponse::from)
        .collect(Collectors.toList());
  }
}


