package run.attraction.api.v1.introduction.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.introduction.dto.response.ApiResponse;
import run.attraction.api.v1.introduction.dto.response.NewsletterResponse;
import run.attraction.api.v1.introduction.dto.response.NewslettersByCategoryResponse;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;
import run.attraction.api.v1.introduction.service.IntroductionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/newsletters")
@Validated
public class IntroductionController {

  private final IntroductionService introductionService;

  @GetMapping("/{newsletterId}")
  public ApiResponse<NewsletterResponse> getNewsletter(
      @PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId) {
    NewsletterResponse newsletter = introductionService.getNewsletter(newsletterId);

    return ApiResponse.from(HttpStatus.OK, "성공", newsletter);
  }

  @GetMapping("/{newsletterId}/articles/prev/{articleId}")
  public ApiResponse<PreviousArticleResponse> getPreviousArticle(@PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId,
                                                    @PathVariable("articleId") @NotNull @Min(1) Long articleId) {
    PreviousArticleResponse previousArticle = introductionService.getPreviousArticle(newsletterId, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", previousArticle);
  }

  @GetMapping("/{newsletterId}/articles/prev")
  public ApiResponse<List<PreviousArticleResponse>> getPreviousArticles(
      @PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId, @RequestParam @Min(1) int size) {
    List<PreviousArticleResponse> previousArticles = introductionService.getPreviousArticles(newsletterId, size);

    return ApiResponse.from(HttpStatus.OK, "성공", previousArticles);
  }

  @GetMapping("/{newsletterId}/related")
  public ApiResponse<List<NewslettersByCategoryResponse>> getRelated(
      @PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId, @RequestParam @Min(1) int size) {
    List<NewslettersByCategoryResponse> newsletters = introductionService.getRelatedNewslettersByCategory(newsletterId,
        size);

    return ApiResponse.from(HttpStatus.OK, "성공", newsletters);
  }
}
