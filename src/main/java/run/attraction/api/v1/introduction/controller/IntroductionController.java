package run.attraction.api.v1.introduction.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.introduction.dto.response.NewsletterResponse;
import run.attraction.api.v1.introduction.dto.response.PreviousArticleResponse;
import run.attraction.api.v1.introduction.service.IntroductionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/newsletters")
@Validated
public class IntroductionController {

  private final IntroductionService introductionService;

  @GetMapping("/{newsletterId}")
  public ResponseEntity<?>  getNewsletter(@PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId) {
      NewsletterResponse newsletter = introductionService.getNewsletter(newsletterId);

      return ResponseEntity.ok(newsletter);
  }

  @GetMapping("/{newsletterId}/articles/prev")
  public ResponseEntity<Map<String, Object>> getArticles(@PathVariable("newsletterId") @NotNull @Min(1) Long newsletterId, @RequestParam @Min(1) int size) {
    List<PreviousArticleResponse> previousArticles = introductionService.getPreviousArticles(newsletterId, size);

    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.OK.value());
    response.put("data", previousArticles);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
