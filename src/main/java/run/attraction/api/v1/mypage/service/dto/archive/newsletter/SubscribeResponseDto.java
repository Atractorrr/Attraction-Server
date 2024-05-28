package run.attraction.api.v1.mypage.service.dto.archive.newsletter;

import java.util.List;
import java.util.Objects;

public record SubscribeResponseDto(
    List<MypageNewsletterDetail> subscribeList
) {
  public SubscribeResponseDto {
    Objects.requireNonNull(subscribeList);
  }
}