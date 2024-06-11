package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.Objects;

public record UpdateProfileImgRequestDto(
    String fileImgSrc
) {
  public UpdateProfileImgRequestDto {
    Objects.requireNonNull(fileImgSrc);
  }
}
