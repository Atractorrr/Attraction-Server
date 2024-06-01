package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import run.attraction.api.v1.user.Interest;

@Builder
public record UserDetailDto(
  String email,
  String name,
  String profileImg,
  String backgroundImg,
  Set<Interest> interest,
  String occupation,
  int userExpiration
){
  public UserDetailDto {
    Objects.requireNonNull(email);
    Objects.requireNonNull(profileImg);
  }
}
