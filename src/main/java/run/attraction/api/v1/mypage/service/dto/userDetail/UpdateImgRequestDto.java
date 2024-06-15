package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.Objects;

public record UpdateImgRequestDto(
    String fileImgSrc
) {
  public UpdateImgRequestDto {
    Objects.requireNonNull(fileImgSrc);
  }
}
