package run.attraction.api.v1.introduction.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.attraction.api.v1.archive.dto.response.ApiResponse;
import run.attraction.api.v1.introduction.service.SubscriptionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Validated
public class SubscriptionController {

  private final SubscriptionService subscriptionService;

  @GetMapping("/{userEmail}/subscriptions/{newsletterId}/is-subscribed")
  public ApiResponse<Boolean> isSubScribed(@PathVariable @NotNull String userEmail, @PathVariable @NotNull @Min(1)  Long newsletterId) {
    Boolean subscribed = subscriptionService.isSubscribed(userEmail, newsletterId);

    return ApiResponse.from(HttpStatus.OK, "성공", subscribed);
  }
}
