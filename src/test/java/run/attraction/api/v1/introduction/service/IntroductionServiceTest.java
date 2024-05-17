package run.attraction.api.v1.introduction.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import run.attraction.api.v1.introduction.Article;
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

  @InjectMocks
  private IntroductionService introductionService;

  private Long newsletterId;
  private int size;
  private Category category;
  private Newsletter newsletter;
  private List<Newsletter> newsletters;
  private List<Article> articles;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    newsletterId = 1L;
    size = 2;
    category = Category.IT_TECH;

    Article article1 = new Article(1L, "Tech Trends 2024", "http://techweekly.com/thumbnails/trends2024.jpg", "content1", "http://techweekly.com/articles/trends2024", 5);
    Article article2 = new Article(2L, "Healthy Living Tips", "http://healthinsights.com/thumbnails/healthyliving.jpg", "content1", "http://healthinsights.com/articles/healthyliving", 3);
    List<Article> articles = Arrays.asList(article1, article2);

    newsletter = Newsletter.builder()
        .id(newsletterId)
        .name("Test Newsletter")
        .description("Description")
        .category(category)
        .mainLink("http://example.com")
        .subscriptionLink("http://example.com/subscribe")
        .thumbnail("http://example.com/thumbnail.jpg")
        .articles(articles)
        .build();
    newsletters = List.of(
        Newsletter.builder()
            .id(2L)
            .name("Test Newsletter 2")
            .description("Description")
            .category(category)
            .mainLink("http://example.com")
            .subscriptionLink("http://example.com/subscribe")
            .thumbnail("http://example.com/thumbnail.jpg")
            .build(),
        Newsletter.builder()
            .id(3L)
            .name("Test Newsletter 3")
            .description("Description")
            .category(category)
            .mainLink("http://example.com")
            .subscriptionLink("http://example.com/subscribe")
            .thumbnail("http://example.com/thumbnail.jpg")
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
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.of(newsletter));

    // When
    List<PreviousArticleResponse> result = introductionService.getPreviousArticles(newsletterId, size);

    // Then
    assertNotNull(result);
    assertEquals(size, result.size());
    verify(newsletterRepository, times(1)).findById(newsletterId);
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
    when(newsletterRepository.countByCategoryAndIdNot(category, newsletterId)).thenReturn(2);
    when(newsletterRepository.findByCategoryAndIdNotWithOffset(category.name(), newsletterId, size, 0)).thenReturn(newsletters);

    // When
    List<NewslettersByCategoryResponse> result = introductionService.getRelatedNewslettersByCategory(newsletterId, size);

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    verify(newsletterRepository, times(1)).findById(newsletterId);
    verify(newsletterRepository, times(1)).countByCategoryAndIdNot(category, newsletterId);
    verify(newsletterRepository, times(1)).findByCategoryAndIdNotWithOffset(category.name(), newsletterId, size, 0);
  }

  @Test
  void getRelatedNewslettersByCategory_whenCountGreaterThanSize() {
    // Given
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.of(newsletter));
    when(newsletterRepository.countByCategoryAndIdNot(category, newsletterId)).thenReturn(10);
    when(newsletterRepository.findByCategoryAndIdNotWithOffset(eq(category.name()), eq(newsletterId), eq(size), anyInt())).thenReturn(newsletters);

    // When
    List<NewslettersByCategoryResponse> result = introductionService.getRelatedNewslettersByCategory(newsletterId, size);

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    verify(newsletterRepository, times(1)).findById(newsletterId);
    verify(newsletterRepository, times(1)).countByCategoryAndIdNot(category, newsletterId);
    verify(newsletterRepository, times(1)).findByCategoryAndIdNotWithOffset(eq(category.name()), eq(newsletterId), eq(size), anyInt());
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
    verify(newsletterRepository, times(0)).countByCategoryAndIdNot(any(Category.class), anyLong());
    verify(newsletterRepository, times(0)).findByCategoryAndIdNotWithOffset(anyString(), anyLong(), anyInt(), anyInt());
  }
}