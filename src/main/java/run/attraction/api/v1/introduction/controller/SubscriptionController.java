package run.attraction.api.v1.introduction.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class SubscriptionController {

  private final SubscriptionService subscriptionService;
  private final GmailService gmailService;

  @GetMapping("/{userEmail}/subscriptions/{newsletterId}/is-subscribed")
  public ApiResponse<Boolean> isSubScribed(@PathVariable @NotNull String userEmail, @PathVariable @NotNull @Min(1)  Long newsletterId) {
    Boolean subscribed = subscriptionService.isSubscribed(userEmail, newsletterId);

    return ApiResponse.from(HttpStatus.OK, "성공", subscribed);
  }

  @PutMapping("/{userEmail}/subscribe/{newsletterId}")
  public ApiResponse<Void> addNewsletter(@PathVariable String userEmail, @PathVariable @NotNull @Min(1) Long newsletterId) {
    Newsletter newsletter = subscriptionService.getNewsletter(newsletterId);

    subscriptionService.subscribeNewsletter(userEmail, newsletter);
    subscriptionService.sendKafkaMessageForSubscription(userEmail, newsletter);
    gmailService.applyLabelAndFilter(new UserGmailDto(userEmail, newsletter.getEmail()));

    return ApiResponse.from(HttpStatus.OK, "성공", null);
  }
}
