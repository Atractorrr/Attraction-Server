package run.attraction.api.v1.introduction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "뉴스레터 소개", description = "IntroductionController")
public class IntroductionController {

  private final IntroductionService introductionService;

  @GetMapping("/{newsletterId}")
  @Operation(summary = "특정 뉴스레터 데이터 가져오기", description = "소개 페이지에 필요한 뉴스레터 데이터 return")
  public ApiResponse<NewsletterResponse> getNewsletter(
      @PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId) {
    NewsletterResponse newsletter = introductionService.getNewsletter(newsletterId);

    return ApiResponse.from(HttpStatus.OK, "성공", newsletter);
  }

  @GetMapping("/{newsletterId}/articles/prev/{articleId}")
  @Operation(summary = "특정 뉴스레터의 특정 이전 아티클 가져오기", description = "특정 뉴스레터가의  특정 아티클 가져온다.")
  public ApiResponse<PreviousArticleResponse> getPreviousArticle(@PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId,
                                                    @PathVariable("articleId") @NotNull @Min(1) Long articleId) {
    PreviousArticleResponse previousArticle = introductionService.getPreviousArticle(newsletterId, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", previousArticle);
  }

  @GetMapping("/{newsletterId}/articles/prev")
  @Operation(summary = "특정 뉴스레터의 이전 아티클들 가져오기", description = "특정 뉴스레터가 가지고 있는 이전 아티클을 size에 맞게 가져온다.")
  public ApiResponse<List<PreviousArticleResponse>> getPreviousArticles(
      @PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId, @RequestParam @Min(1) int size) {
    List<PreviousArticleResponse> previousArticles = introductionService.getPreviousArticles(newsletterId, size);

    return ApiResponse.from(HttpStatus.OK, "성공", previousArticles);
  }

  @GetMapping("/{newsletterId}/related")
  @Operation(summary = "특정 뉴스레터와 관련된 뉴스레터 가져오기", description = "아직은 카테고리 기준으로 가져오는 중 / size에 맞게 가져온다.")
  public ApiResponse<List<NewslettersByCategoryResponse>> getRelated(
      @PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId, @RequestParam @Min(1) int size) {
    List<NewslettersByCategoryResponse> newsletters = introductionService.getRelatedNewslettersByCategory(newsletterId,
        size);

    return ApiResponse.from(HttpStatus.OK, "성공", newsletters);
  }
}
