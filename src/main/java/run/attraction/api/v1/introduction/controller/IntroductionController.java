package run.attraction.api.v1.introduction.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.introduction.dto.response.NewsletterResponse;
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
}
