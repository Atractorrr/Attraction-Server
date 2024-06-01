package run.attraction.api.v1.home.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.home.service.dto.article.ArticleDetailDto;
import run.attraction.api.v1.home.service.dto.article.ReceivedArticlesResponseDto;
import run.attraction.api.v1.home.service.dto.categories.CategoriesResponseDto;
import run.attraction.api.v1.home.service.HomeService;
import run.attraction.api.v1.home.service.dto.newsletter.NewsletterDetailDto;
import run.attraction.api.v1.home.service.dto.newsletter.NewslettersResponseDto;

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
                                                   @Valid @RequestParam int size ) {
    log.info("홈페이지 뉴스레터 반환 API 시작");
    log.info("category= {}", category);
    log.info("size= {}", size);
    final List<NewsletterDetailDto> newsletterDetails = service.getNewsletter(category, size);
    log.info("홈페이지 뉴스레터 반환 API 완료");
    return ResponseEntity.ok(new NewslettersResponseDto(newsletterDetails));
  }

  @GetMapping("/user/{email}/articles/received")
  public ResponseEntity<?> getReceivedArticles(@Valid @PathVariable String email) {
    log.info("홈페이지 받은 아티클 API 시작");
    log.info("email= {}", email);
    final List<ArticleDetailDto> articles = service.getReceivedArticlesByEmail(email);
    log.info("홈페이지 받은 아티클 API 완료");
    return ResponseEntity.ok(new ReceivedArticlesResponseDto(articles));
  }

}
