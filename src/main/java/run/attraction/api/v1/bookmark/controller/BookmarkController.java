package run.attraction.api.v1.bookmark.controller;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.archive.dto.ArticleDTO;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.bookmark.dto.BookmarkArticleRequest;
import run.attraction.api.v1.bookmark.service.BookmarkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Validated
public class BookmarkController {

  private final BookmarkService bookmarkService;

  @GetMapping("/{userEmail}/articles/bookmark")
  public ApiResponse<Page<ArticleDTO>> getUserBookmarkArticles(@PathVariable String userEmail,
                                                               @ModelAttribute BookmarkArticleRequest bookmarkArticleRequest) {
    Page<ArticleDTO> articles = bookmarkService.getUserBookmarkArticle(userEmail, bookmarkArticleRequest);

    return ApiResponse.from(HttpStatus.OK, "성공", articles);
  }

  @GetMapping("/{userEmail}/bookmark")
  public ApiResponse<Boolean> isArticleBookmarked(@PathVariable String userEmail, @RequestParam Long articleId) {
    boolean isArticle = bookmarkService.isArticleBookmarked(userEmail, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", isArticle);
  }

  @PutMapping("/{userEmail}/bookmark")
  public ApiResponse<Void> addArticleToBookmark(@PathVariable String userEmail,  @NotNull @Min(1) @RequestParam Long articleId) {
    bookmarkService.addArticle(userEmail, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }

  @DeleteMapping("/{userEmail}/bookmark")
  public ApiResponse<Void> deleteArticleToBookmark(@PathVariable String userEmail,  @NotNull @Min(1) @RequestParam Long articleId) {
    bookmarkService.deleteArticle(userEmail, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }
}
