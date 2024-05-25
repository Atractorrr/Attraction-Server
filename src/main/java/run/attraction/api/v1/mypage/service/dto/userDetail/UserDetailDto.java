package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.Objects;
import java.util.Set;
import run.attraction.api.v1.user.Interest;

public record UserDetailDto(
  String email,
  String name,
  String profileImg,
  String backgroundImg,
  Set<Interest> categories
){
  public UserDetailDto {
    Objects.requireNonNull(email);
    Objects.requireNonNull(name);
    Objects.requireNonNull(profileImg);
    Objects.requireNonNull(categories);
  }
}
