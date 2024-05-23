package run.attraction.api.v1.introduction.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import run.attraction.api.v1.archive.AdminArticle;
import run.attraction.api.v1.archive.Article;
import run.attraction.api.v1.archive.repository.AdminArticleRepository;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.dto.response.NewsletterResponse;
import run.attraction.api.v1.introduction.dto.response.NewslettersByCategoryResponse;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;
import run.attraction.api.v1.introduction.exception.ErrorMessages;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;

class IntroductionServiceTest {

  @Mock
  private NewsletterRepository newsletterRepository;
  @Mock
  private AdminArticleRepository adminArticleRepository;

  @InjectMocks
  private IntroductionService introductionService;

  private Long newsletterId;
  private int size;
  private Category category;
  private Newsletter newsletter;
  private List<Newsletter> newsletters;
  private List<Article> articles;
  private List<AdminArticle> adminArticles;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    newsletterId = 1L;
    size = 2;
    category = Category.IT_TECH;

    Article article1 = Article.builder()
        .id(1L)
        .newsletterEmail("newsletter@techweekly.com")
        .userEmail("user@techweekly.com")
        .title("Tech Trends 2024")
        .thumbnailUrl("http://techweekly.com/thumbnails/trends2024.jpg")
        .contentUrl("http://techweekly.com/articles/trends2024")
        .readingTime(5)
        .contentSummary("Overview of tech trends in 2024")
        .isDeleted(false)
        .receivedAt(LocalDate.of(2024, 6, 7))
        .createAt(LocalDate.now())
        .build();

    Article article2 = Article.builder()
        .id(2L)
        .newsletterEmail("newsletter@healthinsights.com")
        .userEmail("user@healthinsights.com")
        .title("Healthy Living Tips")
        .thumbnailUrl("http://healthinsights.com/thumbnails/healthyliving.jpg")
        .contentUrl("http://healthinsights.com/articles/healthyliving")
        .readingTime(3)
        .contentSummary("Tips for a healthier lifestyle")
        .isDeleted(false)
        .receivedAt(LocalDate.of(2024, 4, 1))
        .createAt(LocalDate.now())
        .build();

    articles = Arrays.asList(article1, article2);

    AdminArticle adminArticle1 = AdminArticle.builder()
        .id(1L)
        .newsletterEmail("newsletter@techweekly.com")
        .userEmail("user@techweekly.com")
        .title("Tech Trends 2024")
        .thumbnailUrl("http://techweekly.com/thumbnails/trends2024.jpg")
        .contentUrl("http://techweekly.com/articles/trends2024")
        .readingTime(5)
        .contentSummary("Overview of tech trends in 2024")
        .isDeleted(false)
        .receivedAt(LocalDate.of(2024, 6, 7))
        .createAt(LocalDate.now())
        .build();

    AdminArticle adminArticle2 = AdminArticle.builder()
        .id(2L)
        .newsletterEmail("newsletter@healthinsights.com")
        .userEmail("user@healthinsights.com")
        .title("Healthy Living Tips")
        .thumbnailUrl("http://healthinsights.com/thumbnails/healthyliving.jpg")
        .contentUrl("http://healthinsights.com/articles/healthyliving")
        .readingTime(3)
        .contentSummary("Tips for a healthier lifestyle")
        .isDeleted(false)
        .receivedAt(LocalDate.of(2024, 4, 1))
        .createAt(LocalDate.now())
        .build();

    adminArticles = Arrays.asList(adminArticle1, adminArticle2);

    newsletter = Newsletter.builder()
        .id(newsletterId)
        .name("Test Newsletter")
        .newsletterEmail("newsletter@healthinsights.com")
        .description("Description")
        .category(category)
        .mainLink("http://example.com")
        .subscribeLink("http://example.com/subscribe")
        .thumbnailUrl("http://example.com/thumbnail.jpg")
        .build();

    newsletters = List.of(
        Newsletter.builder()
            .id(2L)
            .name("Test Newsletter 2")
            .newsletterEmail("newsletter@techweekly.com")
            .description("Description")
            .category(category)
            .mainLink("http://example.com")
            .subscribeLink("http://example.com/subscribe")
            .thumbnailUrl("http://example.com/thumbnail.jpg")
            .build(),
        Newsletter.builder()
            .id(3L)
            .name("Test Newsletter 3")
            .newsletterEmail("newsletter@techweekly.com2")
            .description("Description")
            .category(category)
            .mainLink("http://example.com")
            .subscribeLink("http://example.com/subscribe")
            .thumbnailUrl("http://example.com/thumbnail.jpg")
            .build()
    );
  }

  @Test
  void getNewsletter() {
    // Given
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.of(newsletter));

    // When
    NewsletterResponse result = introductionService.getNewsletter(newsletterId);

    // Then
    assertNotNull(result);
    verify(newsletterRepository, times(1)).findById(newsletterId);
  }

  @Test
  void getNewsletter_notFound() {
    // Given
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.empty());

    // When
    NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
        introductionService.getNewsletter(newsletterId));

    // Then
    assertEquals(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName(), exception.getMessage());
    verify(newsletterRepository, times(1)).findById(newsletterId);
  }

  @Test
  void getPreviousArticles() {
    // Given
    String newsletterEmail = newsletter.getNewsletterEmail();
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.of(newsletter));
    when(adminArticleRepository.findByNewsletterEmail(newsletterEmail)).thenReturn(Optional.of(adminArticles));

    // When
    List<PreviousArticleResponse> result = introductionService.getPreviousArticles(newsletterId, size);

    // Then
    assertNotNull(result);
    assertEquals(size, result.size());
    verify(newsletterRepository, times(1)).findById(newsletterId);
    verify(adminArticleRepository, times(1)).findByNewsletterEmail(newsletterEmail);
  }

  @Test
  void getPreviousArticles_notFound() {
    // Given
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.empty());

    // When
    NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
        introductionService.getPreviousArticles(newsletterId, size));

    // Then
    assertEquals(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName(), exception.getMessage());
    verify(newsletterRepository, times(1)).findById(newsletterId);
  }

  @Test
  void getRelatedNewslettersByCategory_whenCountLessThanOrEqualToSize() {
    // Given
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.of(newsletter));
    when(newsletterRepository.countByCategoryAndIdNot(newsletterId, category)).thenReturn(2);
    when(newsletterRepository.findByCategoryAndIdNotWithOffset(newsletterId, category.name(),  size, 0)).thenReturn(newsletters);

    // When
    List<NewslettersByCategoryResponse> result = introductionService.getRelatedNewslettersByCategory(newsletterId, size);

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    verify(newsletterRepository, times(1)).findById(newsletterId);
    verify(newsletterRepository, times(1)).countByCategoryAndIdNot(newsletterId, category);
    verify(newsletterRepository, times(1)).findByCategoryAndIdNotWithOffset(newsletterId, category.name(), size, 0);
  }

  @Test
  void getRelatedNewslettersByCategory_whenCountGreaterThanSize() {
    // Given
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.of(newsletter));
    when(newsletterRepository.countByCategoryAndIdNot(newsletterId, category)).thenReturn(10);
    when(newsletterRepository.findByCategoryAndIdNotWithOffset(eq(newsletterId), eq(category.name()), eq(size), anyInt())).thenReturn(newsletters);

    // When
    List<NewslettersByCategoryResponse> result = introductionService.getRelatedNewslettersByCategory(newsletterId, size);

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    verify(newsletterRepository, times(1)).findById(newsletterId);
    verify(newsletterRepository, times(1)).countByCategoryAndIdNot(newsletterId, category);
    verify(newsletterRepository, times(1)).findByCategoryAndIdNotWithOffset(eq(newsletterId), eq(category.name()), eq(size), anyInt());
  }

  @Test
  void getRelatedNewslettersByCategory_whenNewsletterNotFound() {
    // Given
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.empty());

    // When
    NoSuchElementException exception = assertThrows(NoSuchElementException.class, () ->
        introductionService.getRelatedNewslettersByCategory(newsletterId, size));

    // Then
    assertEquals(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName(), exception.getMessage());
    verify(newsletterRepository, times(1)).findById(newsletterId);
  }
}