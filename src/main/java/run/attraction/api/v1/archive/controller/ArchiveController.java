package run.attraction.api.v1.archive.controller;


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
import run.attraction.api.v1.archive.dto.NewsletterEmail;
import run.attraction.api.v1.archive.dto.request.UserArticlesRequest;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.archive.service.ArchiveService;
import run.attraction.api.v1.auth.token.dto.UserGmailToken;
import run.attraction.api.v1.auth.token.service.GoogleTokenService;
import run.attraction.api.v1.gmail.GmailClient;
import run.attraction.api.v1.introduction.Newsletter;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Validated
public class ArchiveController {

  private final ArchiveService archiveService;
  private final GoogleTokenService tokenService;
  private final GmailClient gmailClient;

  @GetMapping("/{userEmail}/articles")
  public ApiResponse<Page<ArticleDTO>> getUserArticles(@PathVariable String userEmail, @ModelAttribute UserArticlesRequest request) {

    Page<ArticleDTO> articles = archiveService.findArticlesByUserId(userEmail, request);

    return ApiResponse.from(HttpStatus.OK, "성공", articles);
  }

  @GetMapping("/{userEmail}/article/{articleId}")
  public ApiResponse<ArticleDTO> getUserArticle(@PathVariable String userEmail, @PathVariable @NotNull @Min(1) Long articleId) {
    ArticleDTO article = archiveService.findArticleByArticleId(userEmail, articleId);

    return ApiResponse.from(HttpStatus.OK, "성공", article);
  }

  @PutMapping("/{userEmail}/article/{articleId}")
  public ApiResponse<Void> saveUserArticleProgress(@PathVariable String userEmail, @PathVariable @NotNull @Min(1) Long articleId, @RequestParam @NotNull int percentage) {
    archiveService.saveUserArticleProgress(userEmail, articleId, percentage);

    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }

  @PutMapping("/{userEmail}/subscribe/{newsletterId}")
  public ApiResponse<Void> addNewsletter(@PathVariable String userEmail, @PathVariable @NotNull @Min(1) Long newsletterId) {
    final NewsletterEmail newsletterEmail = archiveService.addNewsletter(userEmail, newsletterId);
    final UserGmailToken userToken = tokenService.findUserToken(userEmail);

    gmailClient.applyLabelAndFilterForNewsletterEmail(
        newsletterEmail.email(),
        userToken.token()
    );
    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }

  @GetMapping("/{userEmail}/categories")
  public ApiResponse<List<?>> getUserSubscribedNewsletterCategories(@PathVariable String userEmail) {
    List<?> userSubscribedNewsletterCategories = archiveService.getUserSubscribedNewsletterCategories(userEmail);

    return ApiResponse.from(HttpStatus.OK, "성공", userSubscribedNewsletterCategories);
  }

  // DEPRECATED
  @GetMapping("/{userEmail}/subscribe")
  public ApiResponse<List<Newsletter>> getSubscribedNewslettersByUser(@PathVariable String userEmail) {
    List<Newsletter> subscribeNewsletters = archiveService.getSubscribedNewslettersByUser(userEmail);

    return ApiResponse.from(HttpStatus.OK, "성공", subscribeNewsletters);
  }
}
