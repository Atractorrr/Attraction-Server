package run.attraction.api.v1.bookmark.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "북마크", description = "BookmarkController")
public class BookmarkController {

  private final BookmarkService bookmarkService;

  @GetMapping("/{userEmail}/articles/bookmark")
  @Operation(summary = "유저가 북마크한 모든 아티클 가져오기", description = "필터 적용가능")
  public ApiResponse<Page<ArticleDTO>> getUserBookmarkArticles(@PathVariable String userEmail,
                                                               @ModelAttribute BookmarkArticleRequest bookmarkArticleRequest) {
    Page<ArticleDTO> articles = bookmarkService.getUserBookmarkArticle(userEmail, bookmarkArticleRequest);

    return ApiResponse.from(HttpStatus.OK, "성공", articles);
  }

  @GetMapping("/{userEmail}/bookmark")
  @Operation(summary = "특정 유저가 해당 아티클에 대해 구독했는지 여부", description = "유저가 해당 아티클을 북마크 했는지 안 했는지 확인")
  public ApiResponse<Boolean> isArticleBookmarked(@PathVariable String userEmail, @RequestParam Long articleId) {
    boolean isArticle = bookmarkService.isArticleBookmarked(userEmail, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", isArticle);
  }

  @PutMapping("/{userEmail}/bookmark")
  @Operation(summary = "북마크에 유저가 선택한 아티클 추가", description = "북마크에 아티클 추가")
  public ApiResponse<Void> addArticleToBookmark(@PathVariable String userEmail,  @NotNull @Min(1) @RequestParam Long articleId) {
    bookmarkService.addArticle(userEmail, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }

  @DeleteMapping("/{userEmail}/bookmark")
  @Operation(summary = "북마크에서 유저가 선택한 아티클 삭제", description = "북마크에서 아티클 삭제")
  public ApiResponse<Void> deleteArticleToBookmark(@PathVariable String userEmail,  @NotNull @Min(1) @RequestParam Long articleId) {
    bookmarkService.deleteArticle(userEmail, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }
}
