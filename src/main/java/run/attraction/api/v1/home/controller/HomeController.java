package run.attraction.api.v1.home.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.home.service.HomeService;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesDto;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesResponseDto;
import run.attraction.api.v1.home.service.dto.categories.CategoriesResponseDto;
import run.attraction.api.v1.home.service.dto.newsletter.NewsletterDetailDto;
import run.attraction.api.v1.home.service.dto.newsletter.NewslettersResponseDto;
import run.attraction.api.v1.home.service.dto.search.ArticleSearchDto;
import run.attraction.api.v1.introduction.dto.response.NewslettersByCategoryResponse;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class HomeController {

  private final HomeService service;

  @GetMapping("/newsletters/categories")
  public ResponseEntity<CategoriesResponseDto> getCategories(@RequestParam String email) {
    final List<String> categories = service.getCategoriesByEmail(email);
    return ResponseEntity.ok(new CategoriesResponseDto(categories));
  }

  @GetMapping("/newsletters/recommend")
  public ResponseEntity<NewslettersResponseDto> getNewsletterByCategory(@Valid @RequestParam String category,
                                                   @Valid @RequestParam int size) {
    final List<NewsletterDetailDto> newsletterDetails = service.getNewsletter(category, size);
    return ResponseEntity.ok(new NewslettersResponseDto(newsletterDetails));
  }

  @GetMapping("/user/{email}/articles/received")
  public ApiResponse<ReceivedArticlesResponseDto> getReceivedArticles(@Valid @PathVariable String email,
                                                                         @RequestParam(value = "size", defaultValue = "10") int size) {
    final List<ReceivedArticlesDto> articles = service.getReceivedArticlesByEmail(email, size);

    return ApiResponse.from(HttpStatus.OK, "성공,", new ReceivedArticlesResponseDto(articles));
  }

  @GetMapping("/search/article")
  public ApiResponse<Page<ArticleSearchDto>> getArticleBySearch(@RequestParam("q") String q,
                                                                @RequestParam("page") int page,
                                                                @RequestParam("size") int size)
  {
    // 검색에 없을때, 자음 혹은 모음만 있을때 예외처리
    Page<ArticleSearchDto> searchResult = service.getArticleSearchResult(q, page, size);
    return ApiResponse.from(HttpStatus.OK, "성공,", searchResult);
  }

  @GetMapping("/search/newsletter")
  public ApiResponse<Page<NewslettersByCategoryResponse>> getNewsletterBySearch(@RequestParam("q") String q,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size)
  {
    // 검색에 없을때, 자음 혹은 모음만 있을때 예외처리
    Page<NewslettersByCategoryResponse> searchResult = service.getNewsletterSearchResult(q, page, size);
    return ApiResponse.from(HttpStatus.OK, "성공,", searchResult);
  }
}
