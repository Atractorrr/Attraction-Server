package run.attraction.api.v1.introduction.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.archive.repository.ArticleRepository;
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
  private final ArticleRepository articleRepository;

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

    return articleRepository.findPreviousArticlesByUserEmail(newsletter.getEmail(), size);
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

  @Transactional(readOnly = true)
  public PreviousArticleResponse getPreviousArticle(Long newsletterId, Long articleId) {
    Newsletter newsletter = newsletterRepository.findById(newsletterId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));
    Article article = articleRepository.findById(articleId)
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.NOT_EXIST_ARTICLE.getViewName()));

    return PreviousArticleResponse.from(article, newsletter);
  }
}


