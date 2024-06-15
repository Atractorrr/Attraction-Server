package run.attraction.api.v1.mypage.service.dto.userDetail;

import lombok.Builder;
import run.attraction.api.v1.user.Interest;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Builder
public record UserDetailDto(
  String email,
  String nickname,
  String profileImg,
  String backgroundImg,
  Set<Interest> interest,
  String occupation,
  int userExpiration,
  LocalDate userExpirationDate,
  LocalDate createdAt

){
  public UserDetailDto {
    // 추가정보를 입력하지 않은 경우도 존재하기 때문에, 모든 필드 null 검사 X
    Objects.requireNonNull(email);
    Objects.requireNonNull(profileImg);
  }
}
