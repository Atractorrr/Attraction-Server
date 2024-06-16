package run.attraction.api.v1.home.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.home.service.HomeService;
import run.attraction.api.v1.home.service.dto.article.ArticleDetailDto;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesResponseDto;
import run.attraction.api.v1.home.service.dto.categories.CategoriesResponseDto;
import run.attraction.api.v1.home.service.dto.newsletter.NewsletterDetailDto;
import run.attraction.api.v1.home.service.dto.newsletter.NewslettersResponseDto;
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
    log.info("홈페이지 카테고리 반환 API 시작");
    log.info("email= {}", email);
    final List<String> categories = service.getCategoriesByEmail(email);
    log.info("홈페이지 카테고리 반환 API 완료");
    return ResponseEntity.ok(new CategoriesResponseDto(categories));
  }

  @GetMapping("/newsletters/recommend")
  public ResponseEntity<NewslettersResponseDto> getNewsletterByCategory(@Valid @RequestParam String category,
                                                   @Valid @RequestParam int size) {
    log.info("홈페이지 뉴스레터 반환 API 시작");
    log.info("category= {}", category);
    log.info("size= {}", size);
    final List<NewsletterDetailDto> newsletterDetails = service.getNewsletter(category, size);
    log.info("홈페이지 뉴스레터 반환 API 완료");
    return ResponseEntity.ok(new NewslettersResponseDto(newsletterDetails));
  }

  @GetMapping("/user/{email}/articles/received")
  public ResponseEntity<ReceivedArticlesResponseDto> getReceivedArticles(@Valid @PathVariable String email) {
    log.info("홈페이지 받은 아티클 API 시작");
    log.info("email= {}", email);
    final List<ArticleDetailDto> articles = service.getReceivedArticlesByEmail(email);
    log.info("홈페이지 받은 아티클 API 완료");
    return ResponseEntity.ok(new ReceivedArticlesResponseDto(articles));
  }

  @GetMapping("/search/article")
  public ApiResponse<Page<ArticleDTO>> getArticleBySearch(@RequestParam("q") String q,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size)
  {
    // 검색에 없을때, 자음 혹은 모음만 있을때 예외처리
    log.info("아티클 통합검색 시작");
    log.info("검색어= {}, page= {}, size= {}", q, page, size);
    Page<ArticleDTO> searchResult = service.getArticleSearchResult(q, page, size);
    log.info("아티클 통합검색 완료");
    return ApiResponse.from(HttpStatus.OK, "성공,", searchResult);
  }

  @GetMapping("/search/newsletter")
  public ApiResponse<Page<NewslettersByCategoryResponse>> getNewsletterBySearch(@RequestParam("q") String q,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size)
  {
    // 검색에 없을때, 자음 혹은 모음만 있을때 예외처리
    log.info("뉴스레터 통합검색 시작");
    log.info("검색어= {}, page= {}, size= {}", q, page, size);
    Page<NewslettersByCategoryResponse> searchResult = service.getNewsletterSearchResult(q, page, size);
    log.info("뉴스레터 통합검색 완료");
    return ApiResponse.from(HttpStatus.OK, "성공,", searchResult);
  }
}
