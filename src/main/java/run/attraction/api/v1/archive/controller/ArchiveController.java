package run.attraction.api.v1.archive.controller;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.request.UserArticlesRequest;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.archive.service.ArchiveService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Validated
public class ArchiveController {

  private final ArchiveService archiveService;

  @GetMapping("/{userEmail}/articles")
  public ApiResponse<Page<ArticleDTO>> getUserArticles(@PathVariable String userEmail, UserArticlesRequest request) {
    request.setUserEmail(userEmail);
    Page<ArticleDTO> articles = archiveService.findArticlesByUserId(request.getUserEmail(), request);

    return ApiResponse.from(HttpStatus.OK, "성공", articles);
  }

  @PutMapping("/{userEmail}/article/{articleId}")
  public ApiResponse<Void> updateUserArticlePercentage(@PathVariable String userEmail, @PathVariable @NotNull @Min(1) Long articleId, @RequestParam @NotNull int percentage) {
    archiveService.updateUserArticlePercentage(userEmail, articleId, percentage);

    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }
}
