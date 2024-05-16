package run.attraction.api.v1.introduction.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.UploadDays;
import run.attraction.api.v1.introduction.dto.response.NewsletterResponse;
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
  public void testGetNewsletter_Success() {
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
  public void testGetNewsletter_NotFound() {
    // given
    Long newsletterId = 1L;
    when(newsletterRepository.findById(newsletterId)).thenReturn(Optional.empty());

    // when & then
    assertThrows(IllegalArgumentException.class, () -> {
      introductionService.getNewsletter(newsletterId);
    });
  }
}