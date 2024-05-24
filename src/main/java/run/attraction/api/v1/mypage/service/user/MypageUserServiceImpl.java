package run.attraction.api.v1.mypage.service.user;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.mypage.service.dto.UserDetailDto;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class MypageUserServiceImpl implements MypageUserService {
  private final UserRepository userRepository;

  public UserDetailDto getUserDetails(String email) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저입니다."));
    final UserDetailDto userDetailDto = getUserDetailDto(user);
    return userDetailDto;
  }

  private static UserDetailDto getUserDetailDto(User user) {
    return new UserDetailDto(
        user.getEmail(),
        user.getNickName(),
        user.getProfileImg(),
        user.getBackgroundImg(),
        user.getInterests());
  }
}
