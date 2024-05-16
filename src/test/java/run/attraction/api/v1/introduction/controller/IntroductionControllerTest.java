package run.attraction.api.v1.introduction.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import run.attraction.api.v1.introduction.Article;
import run.attraction.api.v1.introduction.Category;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.UploadDays;
import run.attraction.api.v1.introduction.dto.response.NewsletterResponse;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;
import run.attraction.api.v1.introduction.exception.ErrorMessages;
import run.attraction.api.v1.introduction.service.IntroductionService;

@WebMvcTest(IntroductionController.class)
class IntroductionControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IntroductionService introductionService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getNewsletter_ValidId_ShouldReturnNewsletter() throws Exception {
    Long newsletterId = 1L;
    Newsletter newsletter = Newsletter.builder()
        .name("Technology Trends")
        .description("Latest updates on technology")
        .uploadDays(Collections.singletonList(UploadDays.WED))
        .category(Category.TREND_LIFE)
        .mainLink("http://www.technewsletter.com")
        .subscriptionLink("http://www.technewsletter.com/subscribe")
        .thumbnail("http://www.technewsletter.com/thumbnail.jpg")
        .build();
    NewsletterResponse mockResponse = new NewsletterResponse(newsletter);
    given(introductionService.getNewsletter(newsletterId)).willReturn(mockResponse);

    mockMvc.perform(get("/api/v1/newsletters/{newsletterId}", newsletterId)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Technology Trends"))
        .andExpect(jsonPath("$.description").value("Latest updates on technology"))
        .andExpect(jsonPath("$.uploadDays").value("WED"))
        .andExpect(jsonPath("$.category").value("TREND_LIFE"))
        .andExpect(jsonPath("$.mainLink").value("http://www.technewsletter.com"))
        .andExpect(jsonPath("$.subscribeLink").value("http://www.technewsletter.com/subscribe"))
        .andExpect(jsonPath("$.thumbnail").value("http://www.technewsletter.com/thumbnail.jpg"));
  }

  @Test
  public void getNewsletter_InvalidId_ShouldReturnBadRequest() throws Exception {
    mockMvc.perform(get("/api/v1/newsletters/{newsletterId}", -1L)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("must be greater than or equal to 1"));

    mockMvc.perform(get("/api/v1/newsletters/{newsletterId}", "invalid")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(ErrorMessages.INVALID_TYPE.getViewName()));
  }


  @Test
  public void getNewsletter_NonExistingId_ShouldReturnNotFound() throws Exception {
    Long newsletterId = 99L;
    doThrow(new NoSuchElementException(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName())).when(introductionService).getNewsletter(newsletterId);

    mockMvc.perform(get("/api/v1/newsletters/{newsletterId}", newsletterId)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(ErrorMessages.NOT_EXIST_NEWSLETTER.getViewName()));
  }

  @Test
  public void getNewsletter_InternalServerError_ShouldReturnInternalServerError() throws Exception {
    Long newsletterId = 1L;
    doThrow(new RuntimeException(ErrorMessages.SEVER_ERROR.getViewName())).when(introductionService).getNewsletter(newsletterId);

    mockMvc.perform(get("/api/v1/newsletters/{newsletterId}", newsletterId)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError())
        .andExpect(jsonPath("$.message").value(ErrorMessages.SEVER_ERROR.getViewName()));
  }

  @Test
  public void testGetArticles() throws Exception {
    // given
    Article article1 = new Article(1L, "Tech Trends 2024", "http://techweekly.com/thumbnails/trends2024.jpg", "content1", "http://techweekly.com/articles/trends2024", 5);
    Article article2 = new Article(2L, "Healthy Living Tips", "http://healthinsights.com/thumbnails/healthyliving.jpg", "content2", "http://healthinsights.com/articles/healthyliving", 3);

    List<PreviousArticleResponse> articles = Arrays.asList(
        new PreviousArticleResponse(article1),
        new PreviousArticleResponse(article2)
    );

    Mockito.when(introductionService.getPreviousArticles(1L, 2))
        .thenReturn(articles);

    // when & then
    mockMvc.perform(get("/api/v1/newsletters/1/articles/prev")
            .param("size", "2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.length()").value(2))
        .andExpect(jsonPath("$.data[0].id").value(1L))
        .andExpect(jsonPath("$.data[0].title").value("Tech Trends 2024"))
        .andExpect(jsonPath("$.data[0].thumbnail").value("http://techweekly.com/thumbnails/trends2024.jpg"))
        .andExpect(jsonPath("$.data[0].content").value("content1"))
        .andExpect(jsonPath("$.data[0].contentUrl").value("http://techweekly.com/articles/trends2024"))
        .andExpect(jsonPath("$.data[0].readingTime").value(5))
        .andExpect(jsonPath("$.data[1].id").value(2L))
        .andExpect(jsonPath("$.data[1].title").value("Healthy Living Tips"))
        .andExpect(jsonPath("$.data[1].thumbnail").value("http://healthinsights.com/thumbnails/healthyliving.jpg"))
        .andExpect(jsonPath("$.data[1].contentUrl").value("http://healthinsights.com/articles/healthyliving"))
        .andExpect(jsonPath("$.data[1].content").value("content2"))
        .andExpect(jsonPath("$.data[1].readingTime").value(3));
  }

  @Test
  public void getArticles_MissingSizeParameter() throws Exception {
    // given
    // when & then
    mockMvc.perform(get("/api/v1/newsletters/1/articles/prev"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(ErrorMessages.REQUEST_PARAMETER_MISSING.getViewName() + "size"));
  }

  @Test
  public void getArticles_InvalidNewsletterId() throws Exception {
    // given
    // when & then
    mockMvc.perform(get("/api/v1/newsletters/-1/articles/prev")
            .param("size", "2"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getArticles_InvalidSizeParameter() throws Exception {
    // given
    // when & then
    mockMvc.perform(get("/api/v1/newsletters/1/articles/prev")
            .param("size", "0"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getArticles_NonNumericSizeParameter() throws Exception {
    // given
    // when & then
    mockMvc.perform(get("/api/v1/newsletters/1/articles/prev")
            .param("size", "abc"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(ErrorMessages.INVALID_TYPE.getViewName()));
  }

  @Test
  public void getArticles_FloatSizeParameter() throws Exception {
    // given
    // when & then
    mockMvc.perform(get("/api/v1/newsletters/1/articles/prev")
            .param("size", "1.5"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(ErrorMessages.INVALID_TYPE.getViewName()));
  }
}