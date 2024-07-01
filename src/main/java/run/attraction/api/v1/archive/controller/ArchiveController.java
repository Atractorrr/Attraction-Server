package run.attraction.api.v1.archive.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.request.UserArticlesRequest;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.archive.service.ArchiveService;
import run.attraction.api.v1.introduction.Newsletter;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Validated
@Tag(name = "아티클 보관함", description = "ArchiveController")
public class ArchiveController {

  private final ArchiveService archiveService;

  @GetMapping("/{userEmail}/articles")
  @Operation(summary = "유저에 해당되는 모든 아티클 가져오기", description = "필터 적용가능 (페이지, 사이즈, 정렬, 카테고리, 읽은 아티클 제외, 검색")
  public ApiResponse<Page<ArticleDTO>> getUserArticles(@PathVariable String userEmail, @ModelAttribute UserArticlesRequest request) {

    Page<ArticleDTO> articles = archiveService.findArticlesByUserId(userEmail, request);

    return ApiResponse.from(HttpStatus.OK, "성공", articles);
  }

  @GetMapping("/{userEmail}/article/{articleId}")
  @Operation(summary = "특정 아티클 가져오기", description = "유저가 해당 아티클 url로 바로 접근할 때 대응")
  public ApiResponse<ArticleDTO> getUserArticle(@PathVariable String userEmail, @PathVariable @NotNull @Min(1) Long articleId) {
    ArticleDTO article = archiveService.findArticleByArticleId(userEmail, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", article);
  }

  @PutMapping("/{userEmail}/article/{articleId}")
  @Operation(summary = "아티클 읽은 퍼센테이지 저장", description = "아티클을 얼마나 읽었는지를 저장")
  public ApiResponse<Void> saveUserArticleProgress(@PathVariable String userEmail, @PathVariable @NotNull @Min(1) Long articleId, @RequestParam @NotNull int readPercentage) {
    archiveService.saveUserArticleProgress(userEmail, articleId, readPercentage);

    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }

  @GetMapping("/{userEmail}/categories")
  @Operation(summary = "유저가 구독한 카테고리 전체 가져오기", description = "유저가 구독한 모든 카테고리를 가져온다")
  public ApiResponse<List<?>> getUserSubscribedNewsletterCategories(@PathVariable String userEmail) {
    List<?> userSubscribedNewsletterCategories = archiveService.getUserSubscribedNewsletterCategories(userEmail);

    return ApiResponse.from(HttpStatus.OK, "성공", userSubscribedNewsletterCategories);
  }

  // DEPRECATED
  @GetMapping("/{userEmail}/subscribe")
  @Operation(summary = "삭제 예정 사용 금지", description = "삭제 예정")
  public ApiResponse<List<Newsletter>> getSubscribedNewslettersByUser(@PathVariable String userEmail) {
    List<Newsletter> subscribeNewsletters = archiveService.getSubscribedNewslettersByUser(userEmail);

    return ApiResponse.from(HttpStatus.OK, "성공", subscribeNewsletters);
  }
}
