package run.attraction.api.v1.introduction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.gmail.dto.UserGmailDto;
import run.attraction.api.v1.gmail.service.GmailService;
import run.attraction.api.v1.introduction.Newsletter;
import run.attraction.api.v1.introduction.service.SubscriptionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Validated
@Tag(name = "구독", description = "SubscriptionController")
public class SubscriptionController {

  private final SubscriptionService subscriptionService;
  private final GmailService gmailService;

  @GetMapping("/{userEmail}/subscriptions/{newsletterId}/is-subscribed")
  @Operation(summary = "유저가 해당 뉴스레터를 구독 했는지 여부", description = "구독했으면 true 구독하지 않았으면 false")
  public ApiResponse<Boolean> isSubScribed(@PathVariable @NotNull String userEmail, @PathVariable @NotNull @Min(1)  Long newsletterId) {
    Boolean subscribed = subscriptionService.isSubscribed(userEmail, newsletterId);

    return ApiResponse.from(HttpStatus.OK, "성공", subscribed);
  }

  @PutMapping("/{userEmail}/subscribe/{newsletterId}")
  @Operation(summary = "뉴스레터 구독하기", description = "특정 뉴스레터를 구독할 수 있다")
  public ApiResponse<Void> addNewsletter(@PathVariable String userEmail, @PathVariable @NotNull @Min(1) Long newsletterId) {
    Newsletter newsletter = subscriptionService.getNewsletter(newsletterId);

    subscriptionService.subscribeNewsletter(userEmail, newsletter);
    subscriptionService.sendKafkaMessageForSubscription(userEmail, newsletter);
    gmailService.applyLabelAndFilter(new UserGmailDto(userEmail, newsletter.getEmail()));

    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }

  @GetMapping("/{userEmail}/categories")
  @Operation(summary = "유저가 구독한 카테고리 전체 가져오기", description = "유저가 구독한 모든 카테고리를 가져온다")
  public ApiResponse<List<?>> getUserSubscribedNewsletterCategories(@PathVariable String userEmail) {
    List<?> userSubscribedNewsletterCategories = subscriptionService.getUserSubscribedNewsletterCategories(userEmail);

    return ApiResponse.from(HttpStatus.OK, "성공", userSubscribedNewsletterCategories);
  }
}
