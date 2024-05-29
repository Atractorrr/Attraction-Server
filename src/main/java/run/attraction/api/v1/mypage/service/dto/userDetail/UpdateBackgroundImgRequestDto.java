package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.Objects;

public record UpdateBackgroundImgRequestDto(
    String backgroundImg
) {
  public UpdateBackgroundImgRequestDto {
    Objects.requireNonNull(backgroundImg);
  }
}
