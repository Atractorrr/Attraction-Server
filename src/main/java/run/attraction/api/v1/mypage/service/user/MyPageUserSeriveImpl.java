package run.attraction.api.v1.mypage.service.user;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import run.attraction.api.v1.mypage.service.dto.UserDetaiilDto;
import run.attraction.api.v1.user.User;
import run.attraction.api.v1.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class MyPageUserSeriveImpl implements MyPageUserService {
  private final UserRepository userRepository;

  public UserDetaiilDto getUserDetails(String email) {
    User user = userRepository.findById(email).orElseThrow(() -> new NoSuchElementException("존재하지 않은 유저입니다."));
    final UserDetaiilDto userDetaiilDto = getUserDetaiilDto(user);
    return userDetaiilDto;
  }

  private static UserDetaiilDto getUserDetaiilDto(User user) {
    return new UserDetaiilDto(
        user.getEmail(),
        user.getNickName(),
        user.getProfileImg(),
        user.getBackgroundImg(),
        user.getInterests());
  }
}
