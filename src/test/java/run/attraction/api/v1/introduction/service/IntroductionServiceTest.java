package run.attraction.api.v1.introduction.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import run.attraction.api.v1.introduction.Article;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.UploadDays;
import run.attraction.api.v1.introduction.dto.response.NewsletterResponse;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;
import run.attraction.api.v1.introduction.exception.ErrorMessages;
import run.attraction.api.v1.introduction.repository.NewsletterRepository;

class IntroductionServiceTest {
  @Mock
  private NewsletterRepository newsletterRepository;

  @InjectMocks
  private IntroductionService introductionService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getNewsletter_Success() {
    // given
    Long newsletterId = 1L;
    Newsletter newsletter = Newsletter.builder()
        .id(newsletterId)
        .name("Tech Weekly")
        .description("Weekly newsletter about the latest in tech.")
        .uploadDays(Collections.singletonList(UploadDays.FRI))
        .category(Category.IT_TECH)
        .mainLink("http://techweekly.com")
        .subscriptionLink("http://techweekly.com/subscribe")
        .thumbnail("http://techweekly.com/thumbnail.jpg")
        .build();
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.of(newsletter));

    // when
    NewsletterResponse response = introductionService.getNewsletter(newsletterId);

    // then
    assertEquals(newsletter.getName(), response.name());
  }

  @Test
  public void getNewsletter_NotFound() {
    // given
    Long newsletterId = 1L;
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.empty());

    // when & then
    assertThrows(NoSuchElementException.class, () -> {
      introductionService.getNewsletter(newsletterId);
    });
  }

  @Test
  public void getPreviousArticles_Success() {
    // given
    Article article1 = new Article(1L, "Tech Trends 2024", "http://techweekly.com/thumbnails/trends2024.jpg", "content1", "http://techweekly.com/articles/trends2024", 5);
    Article article2 = new Article(2L, "Healthy Living Tips", "http://healthinsights.com/thumbnails/healthyliving.jpg", "content1", "http://healthinsights.com/articles/healthyliving", 3);
    List<Article> articles = Arrays.asList(article1, article2);

    Long newsletterId = 1L;
    Newsletter newsletter = Newsletter.builder()
        .id(newsletterId)
        .name("Tech Weekly")
        .description("Weekly newsletter about the latest in tech.")
        .uploadDays(Collections.singletonList(UploadDays.FRI))
        .category(Category.IT_TECH)
        .mainLink("http://techweekly.com")
        .subscriptionLink("http://techweekly.com/subscribe")
        .thumbnail("http://techweekly.com/thumbnail.jpg")
        .articles(articles)
        .build();

    when(newsletterRepository.findById(1L)).thenReturn(Optional.of(newsletter));

    // when
    List<PreviousArticleResponse> responses = introductionService.getPreviousArticles(1L, 2);

    // then
    assertEquals(2, responses.size());
    assertEquals("Tech Trends 2024", responses.get(0).title());
    assertEquals("Healthy Living Tips", responses.get(1).title());
  }

  @Test
  public void getPreviousArticles_NotFound() {
    // given
    when(newsletterRepository.findById(1L)).thenReturn(Optional.empty());

    // when & then
    NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
      introductionService.getPreviousArticles(1L, 2);
    });

    assertEquals(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName(), exception.getMessage());
  }
}