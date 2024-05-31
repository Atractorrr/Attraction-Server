package run.attraction.api.v1.mypage.service.dto.userDetail;

import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateUserDetailDto{
  private final String email;
  private final Optional<String> nickname;
  private final Optional<Integer> userExpiration;
  private final Optional<List<String>> interest;
  private final Optional<String> occupation;

  @Builder
  public UpdateUserDetailDto(String email,
                             String nickname,
                             Integer userExpiration,
                             List<String> interest,
                             String occupation) {
    this.email = email;
    this.nickname = Optional.ofNullable(nickname);
    this.userExpiration =  Optional.ofNullable(userExpiration);
    this.interest =  Optional.ofNullable(interest);
    this.occupation =  Optional.ofNullable(occupation);
  }
}
