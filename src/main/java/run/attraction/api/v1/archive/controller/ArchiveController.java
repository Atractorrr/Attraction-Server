package run.attraction.api.v1.archive.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.request.UserArticlesRequest;
import run.attraction.api.v1.archive.service.ArchiveService;


@RestController
@RequiredArgsConstructor
public class ArchiveController {

  private final ArchiveService archiveService;

  @GetMapping("/api/v1/user/{userId}/articles")
  public ResponseEntity<ApiResponse<Page<ArticleDTO>>> getUserArticles(@PathVariable Long userId, UserArticlesRequest request) {

    request.setUserId(userId);
    Page<ArticleDTO> articles = archiveService.findArticlesByUserId(request.getUserId(), request);
    ApiResponse<Page<ArticleDTO>> response = ApiResponse.from(HttpStatus.OK, "성공", articles);

    return ResponseEntity.ok(response);
  }
}
